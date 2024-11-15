package com.cat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class ExpMaou {
    public static void main(String[] args) throws Exception {

        Maou maou = new Maou("aaa");

        ByteArrayOutputStream data =new ByteArrayOutputStream();
        ObjectOutput oos =new ObjectOutputStream(data);
        oos.writeObject(maou);
        oos.flush();
        oos.close();
        System.out.println(Base64.getEncoder().encodeToString(data.toByteArray()));
    }
}
