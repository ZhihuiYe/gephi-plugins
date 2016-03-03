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
import com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
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
import org.openide.util.Utilities;
import org.w3c.dom.DOMException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WriteGEXF_d
{
    public WriteGEXF_d(){}
    
    public void praser(double[][] A, double[] CS_o, double[] CS_i, int N, String[] countries)
    {
        System.out.println("####Start writing GEXF file. (driected)");
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
            graphEle.setAttribute("defaultedgetype", "directed");
            
            Element nodesEle = doc.createElement("nodes");
            Element edgesEle = doc.createElement("edges");
            
            for(int i=0;i<N;i++)
            {
                Element newNodeEle = doc.createElement("node");
                newNodeEle.setAttribute("id", i + "");
                newNodeEle.setAttribute("label", countries[i]);
                newNodeEle.setAttribute("out_corness", CS_o[i] + "");
                newNodeEle.setAttribute("in_corness", CS_i[i] + "");
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
            TransformerFactory transformerF = TransformerFactory.newInstance();
            //Transformer transformer = transformerF.newTransformer();
            //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
            DOMSource source = new DOMSource(doc);
            System.out.println("###########");
            System.out.println(source.toString());
            System.out.println("###########");
            //File newGEXFfile = Utilities.toFile(new URI("file:/Users/Cecilia/Desktop/output.gexf"));
            /*
            if (newGEXFfile == null)
                System.out.println("Cannot create a new GEXF file.");
            else 
                System.out.println("new GEXF file created");
            if (newGEXFfile.exists())
                System.out.println("NEW GEXF file does exists");
            System.out.println("New file name:" + newGEXFfile.getName());
            System.out.println("Path: " + newGEXFfile.getPath());
            System.out.println("Usable space: " + newGEXFfile.getUsableSpace());
            System.out.println("To URL:" + Utilities.toURI(newGEXFfile).toString());
            
            StreamResult console = new StreamResult(newGEXFfile);
            System.out.println("StreamResult created.");
            */
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
            System.out.println("OUTPUT: " + output);
            FileWriter fileWriter = new FileWriter(new File("/Users/Cecilia/Desktop/output.gexf"));
            fileWriter.write(output);
            fileWriter.flush();
            fileWriter.close();
            
            //transformer.transform(source, console);
            
            System.out.println("\n#####XML DOM Created Successfully:/Users/Cecilia/Desktop/output.gexf");
        }catch (ParserConfigurationException e){
            System.out.println("#####ParseConfigurationException: " + e.toString());
        }catch(IllegalArgumentException e){
            System.out.println("#####IllegalArgumentException: " + e.getMessage());
        }catch(TransformerException e) {
            System.out.printf("####Trace: ");
            e.printStackTrace();
            System.out.println("#####TransformerException: " + e.toString() );
        }catch(Exception e){
            System.out.println("#####WriteGEXF Error!!!!....");
            e.printStackTrace();
        }
    }//praser
}//WriteGEXF
