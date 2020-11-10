package com.eskimax.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 *   run和start的区别
 *     1. 当遇到run的时候，之前的线程暂时中断，执行run，run结束后再次启动之前的线程。
 *     2. 当遇到start的时候，两条线程分别进行
 */


public class T01_WhatIsThread {
  private static class T1 extends Thread {
    @Override
    public void run() {
      for(int i = 0; i < 5; i++) {
        try {
          TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("T1");
      }
    }
  }
  public static void main(String[] args) {
    //new T1().run();
    new T1().start();
    for (int i = 0; i < 5; i++) {
      try {
        TimeUnit.MICROSECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Main");
    }

  }
}
