/*
 * powered by https://ekozhan.com
 */
package com.eastrobot.heap.thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * jdk21 虚拟线程
 *
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2026/2/11 16:07
 */
public class VirtualThreadDemo {

  public static void main(String[] args) throws IOException {
    Thread.ofVirtual().start(() -> {
      System.out.println(Thread.currentThread());
    });

    Thread.ofVirtual().name("WednesdayThread").start(() -> {
      System.out.println(Thread.currentThread());
    });

    Thread.startVirtualThread(new MyTask());

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    executorService.submit(new MyTask());
    executorService.submit(new MyTask());
    executorService.submit(new MyTask());
    executorService.submit(new MyTask());
    executorService.submit(new MyTask());

    System.in.read();
  }

  static class MyTask implements Runnable {
    @Override
    public void run() {
      try {
        System.out.println("before-" + Thread.currentThread());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after-" + Thread.currentThread());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
