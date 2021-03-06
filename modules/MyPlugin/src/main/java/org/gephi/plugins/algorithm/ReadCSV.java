package org.gephi.plugins.algorithm;


import java.util.*;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadCSV{
    public  ReadCSV(){}
    
    private static int N;
    private static double[][] A = new double[250][250];
    ArrayList<String> countries= new ArrayList<>();
    
    public void readfile(String filename,int year) throws FileNotFoundException, IOException
    {
        try {
            CsvReader records = new CsvReader(filename);
            
            for (int i = 0; i < 3; i++)
                records.readRecord();
            
            //read the source country
            String headSource = records.get(0);
            String[] parts = headSource.split(": ");
            String source = parts[1];
            
            int index1=0, index2=0;
            
            if(!countries.contains(source))
            {
                countries.add(source);
                index1 = N;
                N++;
            }//if
            else 
                index1 = countries.indexOf(source);
            
            for (int i = 0; i < 3; i++)
                records.readRecord();
            
            //read the column names
            records.readHeaders();
            
            while (records.readRecord())
            {
                Double amount;
                try{
                    if(year == 0)
                        amount = Double.parseDouble(records.get(records.getColumnCount()-1));
                    else
                        amount = Double.parseDouble(records.get(records.getColumnCount()-(4+(year-1)*4)));
                }catch(IOException | NumberFormatException e){
                    amount = 0.0;
                }//catch
                
                if(records.get("Balance sheet position").equals("I:International claims")
                   && records.get("Remaining maturity").equals("A:All maturities")
                   && records.get("Counterparty sector").equals("A:All sectors")
                   && !records.get("Reporting country").equals("5A:All reporting countries")
                   && amount != 0
                   )
                {
                    String headTarget = records.get("Reporting country");
                    String[] parts2 = headTarget.split(":");
                    String target = parts2[1];
                    
                    
                    if(!countries.contains(target))
                    {
                        countries.add(target);
                        index2 = N;
                        if(A[index1][index2] == 0)
                            A[index1][index2] = amount;
                        else
                            A[index1][index2] = A[index1][index2] + amount;
                        
                        A[index2][index1] = A[index1][index2];
                        N++;
                    }//if
                    
                    else
                    {
                        index2 = countries.indexOf(target);
                        if(A[index1][index2] == 0)
                            A[index1][index2] = amount;
                        else
                            A[index1][index2] = A[index1][index2] + amount;
                        
                        A[index2][index1] = A[index1][index2];
                    }//else
                }//if
            }//while
            records.close();
        }catch (FileNotFoundException e) {
            System.out.println(e.toString());
            throw e;
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        }//catch
    }//readfile
    public int getN()
    {
        return N;
    }//getN
    
    public double[][] getA()
    {
        return A;
    }//getA
    
    public String getName(int index)
    {
        return countries.get(index);
    }
}//ReadCSV