/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.plugins.algorithm;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Cecilia
 */
public class WriteCSVNode_d {
    public void praser(double[] CS_out,double[] CS_in, String[] countries,int N) throws IOException
    {
        try
        {
            FileWriter writer = new FileWriter("/Users/Cecilia/Desktop/Directed_Node.csv");
            writer.append("id");
            writer.append(",");
            writer.append("label");
            writer.append(",");
            writer.append("Out_Coreness");
            writer.append(",");
            writer.append("In_Coreness");
            writer.append("\n");
            
            for(int i=0;i<N;i++)
            {
                writer.append(Integer.toString(i));
                writer.append(",");
                writer.append(countries[i]);
                writer.append(",");
                writer.append(Double.toString(CS_out[i]));
                writer.append(",");
                writer.append(Double.toString(CS_in[i]));
                writer.append("\n");
            }
            System.out.println("CSV successful");
            writer.flush();
            writer.close();
        } catch (Exception e) {

            System.out.println("Error in CsvFileWriter !!!");

            e.printStackTrace();

        } 
    }

    
}
