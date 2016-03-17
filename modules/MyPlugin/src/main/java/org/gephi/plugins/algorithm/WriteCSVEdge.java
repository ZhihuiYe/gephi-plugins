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
public class WriteCSVEdge {
    public void praser(double[][] A, int N) throws IOException
    {
        try
        {
            FileWriter writer = new FileWriter("/Users/Cecilia/Desktop/Un_Edge.csv");
            writer.append("Source");
            writer.append(",");
            writer.append("Target");
            writer.append(",");
            writer.append("Type");
            writer.append(",");
            writer.append("id");
            writer.append(",");
            writer.append("Weight");
            writer.append("\n");
            
            int count=0;
            
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {
                    if(A[i][j] != 0)
                    {
                        writer.append(Integer.toString(i));
                        writer.append(",");
                        writer.append(Integer.toString(j));
                        writer.append(",");
                        writer.append("Undirected");
                        writer.append(",");
                        writer.append(Integer.toString(count));
                        writer.append(",");
                        writer.append(Double.toString(A[i][j]));
                        writer.append("\n");
                        count++;
                    }//if
                }
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
