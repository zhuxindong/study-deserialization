package com.zxd.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetRuntimeByClass {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 究极套娃

        Class clazz = Runtime.class;
        Method getRuntimeMethod = clazz.getDeclaredMethod("getRuntime");
        Runtime runtime = (Runtime) getRuntimeMethod.invoke(null);
        Method exec = clazz.getDeclaredMethod("exec",String[].class,String[].class);
        exec.invoke(runtime,new String[]{"calc"},null);

        System.out.println(runtime);



    }

}
