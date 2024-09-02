package com.zxd.rmi;

import com.zxd.rmi.impl.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIRegister {

    public static void main(String[] args) {
        try {
            HelloService helloService = new HelloServiceImpl();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/helloService",helloService);
            System.out.println("远程对象绑定成功");

        } catch (AlreadyBoundException e) {
            System.out.println("发生重复绑定对象异常");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("发生URL协议异常");
            e.printStackTrace();
        }catch (RemoteException e){
            System.out.println("创建远程对象异常");
            e.printStackTrace();
        }

    }

}
