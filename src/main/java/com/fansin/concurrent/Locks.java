package com.fansin.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by zhaofeng on 17-4-4.
 */
public class Locks {


    public static void main(String[] args) throws InterruptedException {
        //重入锁
        reentrylock();

        //java8 新加锁
        stamplock();

        //可重入读写锁
        reentrywriteread();

    }

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static void reentrywriteread(){
        Integer i = 1;
        readWriteLock.writeLock().lock();
        System.out.println(i++);
        readWriteLock.writeLock().unlock();
        readWriteLock.readLock().lock();
        System.out.println(">>>"+i);
        readWriteLock.readLock().unlock();


    }

    static StampedLock stampedLock = new StampedLock();
    static double x=0,y=0;
    public static void stamplock(){


        //d写锁
        long stamp = stampedLock.writeLock();
        System.out.println("写锁:"+stamp);
        x +=10;
        y +=20;
//        stamp++;//报错IllegalMonitorStateException ,说明锁是有效果的
        stampedLock.unlockWrite(stamp);

        //读
        stamp = stampedLock.readLock();
        System.out.println("读锁:"+stamp);
        System.out.println(x+" "+y);
        stampedLock.unlockRead(stamp);

        //
        System.out.println(distanceFromOrigin());
        //
        moveIfAtOrigin(1,2);
    }

    //乐观锁
   public static double distanceFromOrigin() { // A read-only method
      long stamp = stampedLock.tryOptimisticRead();//乐观锁,还没有获取读锁
      double currentX = x, currentY = y;
      if (!stampedLock.validate(stamp)) {//是否有读锁,没有,则获取读悲观锁
         stamp = stampedLock.readLock();//
         try {
           currentX = x;
           currentY = y;
         } finally {
             stampedLock.unlockRead(stamp);
         }
      }
      return Math.sqrt(currentX * currentX + currentY * currentY);
    }
//悲观锁
          public static void moveIfAtOrigin(double newX, double newY) { // upgrade
      // Could instead start with optimistic, not read mode
      long stamp = stampedLock.readLock();
      try {
        while (x == 0.0 && y == 0.0) {
          long ws = stampedLock.tryConvertToWriteLock(stamp);//是否有写锁,如果有则获取,没有则释放读锁.在获取写锁
          if (ws != 0L) {
            stamp = ws;
            x = newX;
            y = newY;
            break;
          }
          else {
              stampedLock.unlockRead(stamp);
            stamp = stampedLock.writeLock();
          }
        }
      } finally {
          stampedLock.unlock(stamp);
      }
    }


    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void reentrylock() throws InterruptedException {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                lock.lock();//可重入
                try {
                    condition.await();
                    System.out.println("线程运行中....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        lock.lock();
        //没有这个就会一直等待
        condition.signal();
        lock.unlock();

    }

}
