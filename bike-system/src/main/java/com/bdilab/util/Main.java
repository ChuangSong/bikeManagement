package com.bdilab.util;


import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Main {

    private static Object lock = new Object();
    private static boolean aRun=false, bRun = false,cRun=true;
    private static CountDownLatch c1 = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            while (!cRun) lock.wait();
                            System.out.println("A");
                            c1.countDown();
                            aRun = true;
                            cRun = false;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            while(!aRun) lock.wait();
                            c1.await();
                            System.out.println("B");
                            bRun = true;
                            aRun = false;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            while (!bRun) lock.wait();
                            System.out.println("C");
                            cRun=true;
                            bRun=false;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        });
        a.start();
        b.start();
        c.start();
        List<String> list = new ArrayList<>();

    }
}
