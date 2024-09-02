package com.zxd.serialization;


import java.io.*;
import java.nio.charset.Charset;

public class Student implements Serializable {

    static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public Student() {
        System.out.println("无参构造函数");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("有参构造函数");
    }

    // 重写readObject方法
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        Process p =Runtime.getRuntime().exec("ipconfig");//执行ipconfig命令
        BufferedInputStream bfIn = new BufferedInputStream(p.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(bfIn, Charset.forName("GBK")));
        String s=null;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

    }

    public void sayHello(){
        System.out.println("hello world");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
