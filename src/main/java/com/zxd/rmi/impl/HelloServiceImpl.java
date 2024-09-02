package com.zxd.rmi.impl;

import com.zxd.rmi.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        //如果父类的无参构造函数抛出了异常，则子类的无参构造函数不能省略不写，并且必须抛出父类的异常或父类异常的父类
        super();
    }

    public String saySomething(String something) throws RemoteException {
        return "from remote server: hello " + something;
    }
}
