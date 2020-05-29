package com.yfy.multithreading.basic.reflect;

public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
        //ClassLoader cl = Robot.class.getClassLoader();
        //Class r = Class.forName("com.interview.javabasic.reflect.Robot");
        Class.forName("com.mysql.jdbc.Driver");
    }
}
