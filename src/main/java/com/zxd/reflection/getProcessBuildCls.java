package com.zxd.reflection;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class getProcessBuildCls {

    public static void main(String[] args) throws Exception {

        Class<?> processBuildCls = Class.forName("java.lang.ProcessBuilder");

        // 反射获取不带参数的实例
        Object objProcessBuildNoArg = processBuildCls.getDeclaredConstructor(String[].class).newInstance((Object) new String[]{"ipconfig"});

        Method startMethod = processBuildCls.getDeclaredMethod("start");

        Process resultProcess = (Process) startMethod.invoke(objProcessBuildNoArg);

        InputStream is = resultProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
        String result = null;
        while ((result = reader.readLine()) != null) {
            System.out.println(result); // 输出结果到控制台
        }





    }

}
