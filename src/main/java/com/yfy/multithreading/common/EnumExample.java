package com.yfy.multithreading.common;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author youfyu
 * @date 2020/07/08
 */
public class EnumExample {
    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    private ExecutorService taskExe = new ThreadPoolExecutor(3, 4, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for (int i = 0; i < list.size(); i++) {
            taskExe.execute(System.out.println(getOb(i)));
        }
//        list.stream().map().collect(Collectors.toList());
    }

    static Integer getOb(Integer a) {
        return a+1;
    }


}
