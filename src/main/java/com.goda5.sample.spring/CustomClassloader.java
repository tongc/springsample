package com.goda5.sample.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by tong on 29/08/2016.
 */
public class CustomClassloader {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {
        URLClassLoader clsLoader = URLClassLoader.newInstance(new URL[]{CustomClassloader.class.getResource("spring-sample-0.1.0.jar")});
        Class cls = clsLoader.loadClass("com.goda5.sample.spring.ClassLoadingMainClass");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[2];

        System.out.println("CustomClassloader classloader " + CustomClassloader.class.getClassLoader());
        System.out.println("CustomClassloader parent classloader " + CustomClassloader.class.getClassLoader().getParent());
        System.out.println("CustomClassloader parent of parent classloader " + CustomClassloader.class.getClassLoader().getParent().getParent());
        System.out.println("URLClassLoader classloader " + clsLoader);
        method.invoke(null, (Object) params);
    }
}
