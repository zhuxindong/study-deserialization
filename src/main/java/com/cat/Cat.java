package com.cat;

import java.io.Serializable;

public abstract class Cat implements Serializable {
    public abstract String getName();

    public abstract void setName(String paramString);

    public abstract int getAttack();

    public abstract int getDefense();
}
