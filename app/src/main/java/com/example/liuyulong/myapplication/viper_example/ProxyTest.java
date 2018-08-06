package com.example.liuyulong.myapplication.viper_example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    private MyProxy.IHello iHello;


    public MyProxy.IHello getProxy() {
        iHello = (MyProxy.IHello) Proxy.newProxyInstance(MyProxy.class.getClassLoader(),
                new Class[]{MyProxy.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        return method.invoke(new MyProxy(), objects);
                    }
                });
        return iHello;
    }
}
