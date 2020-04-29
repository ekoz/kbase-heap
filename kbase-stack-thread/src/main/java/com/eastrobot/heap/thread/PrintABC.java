/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/14 10:23
 */
public class PrintABC {

    private int count = 0;
    private int MAX_COUNT = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    class PrintA implements Runnable {
        @Override
        public void run() {
            try{
                while (true){
                    if (count<MAX_COUNT){
                        lock.lock();
                        System.out.print("A");
                        conditionB.signal();
                        conditionA.await();
                    }else{
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class PrintB implements Runnable {
        @Override
        public void run() {
            try{
                while (true){
                    if (count<MAX_COUNT){
                        lock.lock();
                        System.out.print("B");
                        conditionC.signal();
                        conditionB.await();
                    }else{
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class PrintC implements Runnable {
        @Override
        public void run() {
            try{
                while (true){
                    if (count<MAX_COUNT){
                        lock.lock();
                        count++;
                        System.out.println("C" + count);
                        conditionA.signal();
                        conditionC.await();
                    }else{
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {

        PrintABC printABC = new PrintABC();
        Thread printA = new Thread(printABC.new PrintA());
        Thread printB = new Thread(printABC.new PrintB());
        Thread printC = new Thread(printABC.new PrintC());

        printA.start();
        Thread.sleep(10);
        printB.start();
        Thread.sleep(10);
        printC.start();
        Thread.sleep(10);

    }
}
