package com.fansin.file;

import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈一句话功能简述〉<br>
 * 〈报文解析工具栏〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractMessageProcessor {

    private static final String MODE_READ = "r";
    private static final String MODE_WRITE = "rw";
    private static final int WAITING_DAYS = 1;

    /**
     * 为每个task分配任务数
     */
    private static Integer batchNo = 1000;

    private AtomicLong count = new AtomicLong(0);

    private AtomicLong useTime = new AtomicLong(0L);

    private LinkedDataTask task;

    /**
     * 使用一个，太多占用资源
     */
    private ForkJoinPool pool;

    public AbstractMessageProcessor(LinkedDataTask task) {
        this.task = task;
    }


    /**
     * 从zip读取文件
     *
     * @param filePath
     */
    public void readZip(String filePath) {
    //限于RandomAccessFile只能从文件获取流，先解压文件
        readZip(new File(filePath));
    }

    /**
     *
     * @param file
     */
    public void readZip(File file) {
        log.info("识别文件类型为zip....{}",file.getAbsolutePath());
        File  files = ZipUtil.unzip(file);
        readDir(files);
    }

    /**
     * 读取目录
     * @param files
     */
    private void readDir(File files){
        log.info("识别文件类型为 目录....{}",files.getAbsolutePath());
        if(files.isDirectory()){
            //递归目录
            File[] list = files.listFiles();
            for (File file : list) {
                readDir(file);
            }
        }else {
            //读取文件
            read(files.getAbsolutePath());
        }
    }

    /**
     *
     * @param filePath
     */
    public void readDir(String filePath){
        readDir(new File(filePath));
    }

    /**
     * 读取文件
     *
     * @param filePath
     */
    public void read(String filePath) {
        if (filePath.toLowerCase().endsWith(".txt")){
            readFile(filePath);
        }else if(filePath.toLowerCase().endsWith(".zip")){
            readZip(filePath);
        }else if(new File(filePath).isDirectory()){
            readDir(filePath);
        }else{
            log.error("文件类型不支持{}",filePath);
        }
    }

    /**
     *
     * @param filePath
     */
    private void readFile(String filePath) {
        log.info("识别文件类型为 文件....{}",filePath);
        read0(filePath, MODE_READ);
    }

    /**
     *
     * @param filePath
     * @param mode
     */
    private void read1(String filePath, String mode) {
        try (BufferedRandomAccessFile file = new BufferedRandomAccessFile(filePath, mode)) {
            String line;
            List<String> batchList = new LinkedList<>();
            while ((line = file.readLine()) != null) {
                //数据校验
                if (StringUtils.isEmpty(line) || line.startsWith("#") || line.startsWith("/")) {
                    continue;
                }
                //数据格式化
                line = StringUtils.trim(line);
                if (log.isDebugEnabled()) {
//                    log.debug("line = " + line);
                }
                if (count.get() % batchNo == 0 && count.get() > 0) {
                    task.addTaskDetail(batchList);
                    pool.execute(task);
                    //初始化
                    batchList = new LinkedList<>();
                    task = task.newInstance();
                }
                batchList.add(line);
                count.incrementAndGet();
            }
        } catch (IOException e) {
            log.error(" 报文解析错误！", e);
        }

    }

    /**
     *增加统计代码
     *
     * @param filePath
     * @param mode
     */
    private void read0(String filePath, String mode) {
        long start = System.currentTimeMillis();
        log.info("解析任务开始...");
        init();
        read1(filePath,mode);
        destroy();
        long time = System.currentTimeMillis() - start;
        useTime.addAndGet(time);
        log.info("读取总记录数:{} 总消耗时间: {}  本次耗时: {}", count.get(), useTime.get(), time);
    }

    /**
     * 初始化ForkJoinPool
     */
    private void init(){
        //默认cup线程数
        pool = new ForkJoinPool();
    }

    /**
     * 关闭资源
     */
    private void destroy() {
        pool.shutdown();
        try {
            pool.awaitTermination(WAITING_DAYS, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}