package com.goda5.sample.spring;

/**
 * Created by tong on 29/08/2016.
 */
public class ClassLoadingMainClass {
    public static void main(String[] args) {
        System.out.println("ClassLoadingMainClass classloader " + ClassLoadingMainClass.class.getClassLoader());
        System.out.println("ClassLoadingMainClass parent classloader " + ClassLoadingMainClass.class.getClassLoader().getParent());
        System.out.println("ClassLoadingMainClass parent of parent classloader " + ClassLoadingMainClass.class.getClassLoader().getParent().getParent());
    }
}
