/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.plugins.algorithm;

/**
 *
 * @author Cecilia
 */
import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WriteGEXF
{
    public WriteGEXF(){}
    public void praser(double[][] A, double[] CS, int N, String[] countries)
    {
        System.out.println("####Start writing GEXF file. undirected");
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("http://www.gexf.net/1.2draft", "gexf");
            mainRootElement.setAttribute("xmlns:viz", "http://www.gexf.net/1.2draft/viz");
            mainRootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            mainRootElement.setAttribute("xsi", "http://www.gexf.net/1.2draft");
            mainRootElement.setAttribute("version", "1.2");
            doc.appendChild(mainRootElement);
            
            Element graphEle = doc.createElement("graph");
            graphEle.setAttribute("mode", "static");
            graphEle.setAttribute("defaultedgetype", "undirected");
            
            Element nodesEle = doc.createElement("nodes");
            Element edgesEle = doc.createElement("edges");
            
            for(int i=0;i<N;i++)
            {
                Element newNodeEle = doc.createElement("node");
                newNodeEle.setAttribute("id", i + "");
                newNodeEle.setAttribute("label", countries[i]);
                newNodeEle.setAttribute("corescore", CS[i] + "");
                nodesEle.appendChild(newNodeEle);
            }
            
            for(int m=0;m<N;m++)
            {
                for(int j=0;j<N;j++)
                {
                    if(A[m][j] !=0)
                    {
                        Element newEdgeEle = doc.createElement("edge");
                        newEdgeEle.setAttribute("source", m + "");
                        newEdgeEle.setAttribute("target", j + "");
                        newEdgeEle.setAttribute("weight", A[m][j] + "");
                        edgesEle.appendChild(newEdgeEle);
                    }//if
                }//for
            }//for
            graphEle.appendChild(nodesEle);
            graphEle.appendChild(edgesEle);
            
            mainRootElement.appendChild(graphEle);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(new File("/Users/Cecilia/Desktop/output.gexf"));
            transformer.transform(source, console);
            
            System.out.println("\nXML DOM Created Successfully:/Users/Cecilia/Desktop/output.gexf");
        }catch (ParserConfigurationException | DOMException
        | IllegalArgumentException | TransformerException e)
        {
            System.out.println("#####WriteGEXF Error!!!!....");
            e.printStackTrace();
        }
    }//praser
}//WriteGEXF
