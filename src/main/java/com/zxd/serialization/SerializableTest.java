package com.zxd.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {


    public static void main(String[] args) throws Exception {
        Student student=new Student();//create a Student instance

        serializeObj(student);
        deserializeObj();
    }

    public static void serializeObj(Student student) throws Exception {//序列化
        FileOutputStream fileOutputStream = new FileOutputStream("student.bin");//创建一个文件输出流，文件名为test.cer
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);//创建一个对象输出流，对象数据流中的数据将输出到文件输出流中
        objectOutputStream.writeObject(student);//将student这个object输入到文件输出流中
        objectOutputStream.close();//关闭文件输出流和对象输出流，避免内存泄露
        fileOutputStream.close();
        //序列化完成
    }
    public static void deserializeObj() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("student.bin");//创建文件输入流，读取test.cer
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//创建对象输入流，将文件输入流里的数据输入对象输入流
        Student student =(Student)objectInputStream.readObject();//读取输入流中的对象，强制转换为Student类型，重构对象
        student.sayHello();//call方法
        //反序列化完成
    }
}
