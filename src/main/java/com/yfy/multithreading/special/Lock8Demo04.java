package com.yfy.multithreading.special;

import java.util.concurrent.TimeUnit;


/**
 * 8 lock
 * 1 标准访问，请问先打印邮件还是短信
 * 2 暂停4秒钟在邮件方法，请问先打印邮件还是短信
 * 3 新增普通sayHello方法，请问先打印邮件还是hello
 * 4 两部手机，请问先打印邮件还是短信
 * 5 两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6 两个静态同步方法，2部手机，请问先打印邮件还是短信
 * 7 1个静态同步方法，1个普通同步方法,同一部手机，请问先打印邮件还是短信
 * 8 1个静态同步方法，1个普通同步方法,2部手机，请问先打印邮件还是短信
 */
public class Lock8Demo04 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                //phone.sendSMS();
                //phone.sayHello();
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }


    static class Phone {
        public synchronized void sendEmail() throws Exception {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("*****sendEmail");
        }

        public synchronized void sendSMS() throws Exception {
            System.out.println("*****sendSMS");
        }

        public void sayHello() throws Exception {
            System.out.println("*****sayHello");
        }
    }
}







