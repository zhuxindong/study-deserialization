package com.zxd.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetRuntimeByClass {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 究极套娃

        Class clazz = Runtime.class;
        Method getRuntimeMethod = clazz.getDeclaredMethod("getRuntime");
        Runtime runtime = (Runtime) getRuntimeMethod.invoke(null);
        System.out.println(runtime);



    }

}
