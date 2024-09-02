package com.zxd.annotation;

import sun.reflect.annotation.AnnotationType;

import java.lang.annotation.Target;
import java.util.Map;

public class getAnnotationType {

    public static void main(String[] args) {

        AnnotationType annotationType = AnnotationType.getInstance(Target.class);
        Map<String,Class<?>> memberTypes= annotationType.memberTypes();
        System.out.println(memberTypes);


    }

}
