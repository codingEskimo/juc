package com.eskimax.juc.thread;

/**
 *   启动线程的三种方法
 *     1. extrend Thread 不推荐，一般extend留给更需要的时候
 *     2. implement Runnable
 *     3. Lambda expression
 */
public class T02_HowToCreateThread {
  static class MyThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hello MyThread!");
    }
  }

  static class MyRun implements Runnable {
    @Override
    public void run() {
      System.out.println("Hellow MyRun!");
    }
  }

  public static void main(String[] args) {
    new MyThread().start();
    new Thread(new MyRun()).start();
    new Thread(() -> {
      System.out.println("Hello Lambda!");
    }).start();
  }
}
