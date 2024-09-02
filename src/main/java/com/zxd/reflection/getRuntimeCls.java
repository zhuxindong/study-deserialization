package com.zxd.reflection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class getRuntimeCls {
    public static void main(String[] args) throws Exception{

        // 调用runtime
        Class<?> runTimeClass = Class.forName("java.lang.Runtime");//先找到Runtime这个Class的Class类
        // getDeclared可以获取私有的
        Constructor<?> runtimeConstructor = runTimeClass.getDeclaredConstructor();
        runtimeConstructor.setAccessible(true);
        Object objRuntime = runtimeConstructor.newInstance();
        Method exec = runTimeClass.getDeclaredMethod("exec", String.class);//找到exec这个method
        exec.setAccessible(true);
        Object objResult = exec.invoke(objRuntime,"ipconfig");//在实例中call exec()这个method

        Process ps = (Process) objResult;

        InputStream is = ps.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
        String result = null;
        while ((result = reader.readLine()) != null) {
            System.out.println(result); // 输出结果到控制台
        }

        // 等待命令执行完成
        int exitCode = ps.waitFor();
        System.out.println("Exit Code: " + exitCode);

        // 错误输出流
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
        while ((result = errorReader.readLine()) != null) {
            System.err.println("Error: " + result); // 输出错误信息
        }





    }
}
