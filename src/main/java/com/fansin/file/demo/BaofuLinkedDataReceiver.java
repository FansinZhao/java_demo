package com.fansin.file.demo;

import com.fansin.file.LinkedDataReceiver;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
public class BaofuLinkedDataReceiver implements LinkedDataReceiver<BaoFuLinkedData> {

    /**
     * 处理数据业务处理
     *
     * @param dataList
     * @return
     */
    @Override
    public Long exec(List<BaoFuLinkedData> dataList) {

        /**
         * 更新数据库
         */
        for (BaoFuLinkedData baoFuLinkedData : dataList) {
            //入库
        }
        
        return null;
    }

    /**
     * 将String 解析为 List
     *
     * @param stringList
     * @return
     */
    @Override
    public List<BaoFuLinkedData> preProcess(List<String> stringList) {
        List dataList = new LinkedList();
        for (String s : stringList) {
            String[] strings = s.split(SEPERATOR);
            dataList.add(new BaoFuLinkedData(strings[0],strings[1]));
        }
        return dataList;
    }

    /**
     * 克隆
     *
     * @return
     */
    @Override
    public LinkedDataReceiver newInstance() {
        return new BaofuLinkedDataReceiver();
    }
}