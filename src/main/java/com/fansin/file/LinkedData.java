package com.fansin.file;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈关联数据〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public abstract class LinkedData {

    /**
     * 内部关联数据
     */
    private String inwardKey;
    /**
     * 外部关联数据
     */
    private String foreignKey;


}