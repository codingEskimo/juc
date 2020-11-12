package com.eskimax.juc.thread;

import java.util.concurrent.TimeUnit;


public class T03_Sleep_Yield_Join {
  public static void main(String[] args) {
    //testSleep();
    //testYield();
    testJoin();
  }

  /**
   * Sleep: 执行sleep命令时候，thread会暂停，cpu会执行其他thread，直到这个thread苏醒
   */
  private static void testSleep() {
    new Thread(() -> {
      for (int i = 0; i < 10; i++){
        System.out.println("A" + i);
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * yield；当thread运行到yield，会谦逊的退出一下，
   * 这个thread重新回到就绪状态，重新抢占cpu。
   * 很少再工程中使用
   */
  private static void testYield(){
    new Thread(() -> {
      for(int i = 0; i < 1000; i++) {
        System.out.println("YA" + i);
        if(i%10 == 0){
          Thread.yield();
        }
      }
    }).start();

    new Thread(() -> {
      for(int i = 0; i < 1000; i++) {
        System.out.println("-----------YB" + i);
        if(i%10 == 0){
          Thread.yield();
        }
      }
    }).start();
  }

  /**
   * join: 当一个thread遇到join命令的时候，它会暂停并跳到join命令所指的thread。
   * 一直把所指的thread上的代码执行完毕后，再次回到之前的thread继续执行。
   */
  private static void testJoin() {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++){
        System.out.println("A" + i);
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 10; i++){
        System.out.println("B" + i);
        try {
          if (i == 5) {
            t1.join();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t2.start();
    t1.start();

  }
}
