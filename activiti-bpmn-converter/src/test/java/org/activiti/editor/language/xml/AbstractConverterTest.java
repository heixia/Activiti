package org.activiti.editor.language.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;

public abstract class AbstractConverterTest {

  protected BpmnModel readXMLFile() throws Exception {
    InputStream xmlStream = this.getClass().getClassLoader().getResourceAsStream(getResource());
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(xmlStream, "UTF-8");
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new BpmnXMLConverter().convertToBpmnModel(xtr);
  }

  protected BpmnModel exportAndReadXMLFile(BpmnModel bpmnModel) throws Exception {
    byte[] xml = new BpmnXMLConverter().convertToXML(bpmnModel);
    System.out.println("xml " + new String(xml, "UTF-8"));
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(xml), "UTF-8");
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new BpmnXMLConverter().convertToBpmnModel(xtr);
  }

  protected abstract String getResource();
}
