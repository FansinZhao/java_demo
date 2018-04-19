package com.fansin.file.demo;

import com.fansin.file.AbstractMessageProcessor;
import com.fansin.file.LinkedDataTask;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
public class BaofuMessageProcessor extends AbstractMessageProcessor {

    public BaofuMessageProcessor(LinkedDataTask task) {
        super(task);
    }

    public static void main(String[] args) {
//        new BaofuMessageProcessor(new LinkedDataTask(new BaofuLinkedDataReceiver())).read("D:/smy-dev/IdeaProjects/Github/java_demo/src/main/resources/largefile.txt");
        new BaofuMessageProcessor(new LinkedDataTask(new BaofuLinkedDataReceiver())).read("D:/smy-dev/IdeaProjects/Github/java_demo/src/main/resources/largezip.zip");
//        new BaofuMessageProcessor(new LinkedDataTask(new BaofuLinkedDataReceiver())).read("D:/smy-dev/IdeaProjects/Github/java_demo/src/main/resources/largezip");
    }
}