package com.zxd.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GetInfoFromCls {
    public static void main(String[] args) throws Exception {


        Class<?> cls = Class.forName("com.zxd.reflection.Person");

        // 调用无参构造函数生成实例
//        Object objPerson = cls.newInstance();

        // 调用有参构造函数生成实例
        Object objPerson = cls.getConstructor(String.class,int.class).newInstance("zxd",18);

        // 调用方法
        Method sayHelloMethod = cls.getMethod("sayHello");
        sayHelloMethod.setAccessible(true);
        sayHelloMethod.invoke(objPerson);

        Method getNameMethod = cls.getMethod("getName");
        getNameMethod.setAccessible(true);
        String name = (String) getNameMethod.invoke(objPerson);
        System.out.println(name);

        // 调用有参数的方法
        Method saySomethingMethod = cls.getMethod("saySomething", String.class);
        saySomethingMethod.setAccessible(true);
        saySomethingMethod.invoke(objPerson, "good");









    }
}
