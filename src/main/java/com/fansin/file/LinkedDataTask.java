package com.fansin.file;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 〈一句话功能简述〉<br>
 * 〈处理关联数据task〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
@Slf4j
@Data
public class LinkedDataTask extends RecursiveTask<Long> {

    private String name;

    /**
     * 数据处理类
     */
    private LinkedDataReceiver receiver;

    private List<String> linkedDataList;


    public LinkedDataTask(LinkedDataReceiver receiver){
        this.name = getClass().getSimpleName();
        this.receiver = receiver;
    }

    /**
     * 添加任务
     * @param linkedDataList
     */
    public void addTaskDetail(List<String> linkedDataList){
        this.linkedDataList = linkedDataList;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        try {
            long start = System.currentTimeMillis();
            List<LinkedData> linkedData = receiver.preProcess(linkedDataList);
            if (CollectionUtil.isEmpty(linkedData)){
                //空
                return -1L;
            }
            Long result = receiver.exec(linkedData);
            long end = System.currentTimeMillis();
            if (log.isDebugEnabled()){
                log.info("任务名称{} 消耗时间：{}",name,end-start);
            }
            return  result;
        }catch (Exception e){

            return -1L;
        }
    }

    /**
     * 克隆接口
     *
     */
    LinkedDataTask newInstance() {
        return new LinkedDataTask(getReceiver().newInstance());
    }

}