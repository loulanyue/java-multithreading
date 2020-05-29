package com.yfy.multithreading.basic.jvm.gc;

public class NormalObject {
    public String name;

    public NormalObject(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() {
        System.out.println("Finalizing obj " + name);
    }

}
