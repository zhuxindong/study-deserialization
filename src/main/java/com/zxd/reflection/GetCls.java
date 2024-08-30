package com.zxd.reflection;

public class GetCls {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Person("zxd",18);

        // 1.通过getClass()获取java.lang.Class
        Class<?> cls1 = person.getClass();
        System.out.println(cls1.getName());

        // 2.通过Class.forName
        Class<?> cls2 = Class.forName("com.zxd.reflection.Person");
        System.out.println(cls2.getName());

        System.out.println(cls1 == cls2);

        // 3.通过 .class
        Class<?> cls3 = Person.class;
        System.out.println(cls3.getName());



    }
}
