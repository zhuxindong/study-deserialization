package com.zxd.commoncollection;

import org.apache.commons.collections.functors.InvokerTransformer;

public class UseInvokerTransformer {

    public static void main(String[] args) {

        InvokerTransformer invokerTransformer = new InvokerTransformer("exec",
                new Class[]{String.class},
                new String[]{"calc"});
        invokerTransformer.transform(Runtime.getRuntime());

    }

}
