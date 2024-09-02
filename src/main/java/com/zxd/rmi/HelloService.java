package com.zxd.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {

    String saySomething(String something) throws RemoteException;

}
