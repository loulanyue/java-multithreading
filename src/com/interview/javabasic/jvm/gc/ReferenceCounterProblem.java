package com.interview.javabasic.jvm.gc;

public class ReferenceCounterProblem {
    public static void main(String[] args) {
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();

        object1.childNode = object2;
        object2.childNode = object1;

    }

}
