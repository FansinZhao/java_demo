package com.fansin.file;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈关联数据接收者〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
public interface LinkedDataReceiver<V extends LinkedData> {

    String SEPERATOR = ",";

    /**
     * 将String 解析为 List
     * @return
     */
    List<V> preProcess(List<String> stringList);

    /**
     * 处理数据
     *
     * @return
     */
    Long exec(List<V> dataList );


    /**
     * 克隆
     *
     * @return
     */
    LinkedDataReceiver newInstance();
}