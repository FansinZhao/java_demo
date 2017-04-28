package com.fansin.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zhaofeng on 17-4-4.
 */
public class Others {

    public static void main(String[] args) throws InterruptedException {
        //信号量
        semaphore();
        //cutdownlaucher
        countdwonlatch();
        //cyc
        cycBarrier();
        //blockedquenue
        blockedquenue();
    }

    public static class Soldier implements Runnable{
        private CyclicBarrier cyc;
        private String name;

        public Soldier(CyclicBarrier cyc, String name) {
            this.cyc = cyc;
            this.name = name;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                //一起完成集合
                cyc.await();//当所有线程都准备好了,调用SolderRun中的run(),来通知最后的结果
                doWork();
                //统一完成任务
                cyc.await();//当所有线程都准备好了,调用SolderRun中的run(),来通知最后的结果

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(new Random().nextInt(3)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"完成工作任务!");
        }
    }

    public static class SolderRun implements Runnable{

        private boolean flag;
        int N;

        public SolderRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            if (flag){
                System.out.println("第"+N+"个士兵任务完成");
            }else {
                System.out.println("第"+N+"个士兵集合完成");
                flag = true;
            }
        }
    }

    private static void cycBarrier() {

        int N = 10;
        boolean flag = false;//用来标记任务是集合还是完成任务
        Thread[] solders = new Thread[10];
        CyclicBarrier barrier = new CyclicBarrier(N,new SolderRun(flag,N));
        System.out.println("集合队伍!");
        for (int i = 0; i < 10; i++) {
            System.out.println("士兵"+i+"报道!");
            solders[i] = new Thread(new Soldier(barrier,"士兵-"+i));
            solders[i].start();
            if (i == 5){
//                solders[0].interrupt();//会报异常!
            }
        }





    }

    static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
    public static void blockedquenue(){

        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(10)*1000);
                        queue.add(Thread.currentThread().getName());
                        System.out.println("添加:"+Thread.currentThread().getName() +" "+ queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(10)*1000);
                        System.out.println("获取:"+queue.take()+" "+ queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        service.shutdown();



    }

    static CountDownLatch latch = new CountDownLatch(10);
    public static void countdwonlatch() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((new Random().nextInt(10))*1000);
                        System.out.println(Thread.currentThread().getId() + "发射火箭准备完成....");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        latch.await();
        System.out.println("火箭发射成功!");
        service.shutdown();

    }


    static final  Semaphore semaphore = new Semaphore(5);

    public static void semaphore(){
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {


            service.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" done!");//五个一组
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        service.shutdown();


    }

}

