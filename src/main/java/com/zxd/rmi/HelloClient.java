package com.zxd.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {

    public static void main(String[] args) {

        //在远程对象注册表registry中寻找指定name的对象，并返回reference
        try {
            //获取register
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
            for(String i : registry.list()){
                System.out.println("注册的服务："+i);
            }
            HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:1099/helloService");//返回的就是stub
            System.out.println(helloService.saySomething("world"));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
