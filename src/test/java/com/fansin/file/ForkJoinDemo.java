package com.fansin.file;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈ForkJoin演示〉
 *
 * @author z00461
 * @create 2018/4/19
 * @since 1.0.0
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool();
//        SubTask<String> task = new SubTask<>("1");
//        task.fork();
        ForkJoinTask<Integer> forkJoinTask = pool.submit(new SubTask<Integer>("task1"));
        Integer result = forkJoinTask.get();
        System.out.println("result = " + result);
        pool.shutdown();
        pool.awaitTermination(1,TimeUnit.DAYS);
    }

    static class SubTask<V> extends RecursiveTask<V>{

        private String name;

        public SubTask(String name){
            this.name = name;
        }

        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        protected V compute() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
            return (V)Integer.valueOf(100);
        }
    }
}