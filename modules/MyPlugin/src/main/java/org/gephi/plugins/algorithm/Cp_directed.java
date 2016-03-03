package org.gephi.plugins.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Cp_directed {

    public  int N;
    private  double[][] A;
    private  double a;
    private  double b;
    private  double aa;//input alpha from
    private  double bb;//input beta from
    private  double ai;//input alpha increment
    private  double bi;//input beta increment
    private  double oa;//input one-parameter alpha from
    private  double oai;//input one-parameter alpha increment
    private  int step_input;//input step
    private  int steps;
    private  double[] CS;
    private  double[] CS_out;
    private  double[] CS_in;
    private  double NF;
    
    private  double T;
    private  double stopT;
    private  double cool;
    private  int maxRej;
    private  int maxTries;
    private  int maxSuc;
    
    private  int graphtype; //1->directed 2->undirected
    private  int selectedalgo;//1->greedy 2->SA
    private  int selectedtrans;//1->sharp 2->smooth 3->one-parameter
    
    private  ArrayList<RValue> RValues = new ArrayList<>();
    private  String[] countrynames;
    
    public Cp_directed(){
    }
    
    public void run(double a, double ai, double b, double bi, double oa, double oai, Integer step, double T
                    , double stopT, double cool, int maxRej, int maxTries, int maxSuc
                    , int graphtype, int algo, int trans) 
    {
        System.out.println("a=" + a + ", ai=" + ai + ", b=" + b + ", bi=" + bi + ", oa=" + oa
                     + ", oai=" + oai + ", step=" + step + ", T=" + T 
                     + ", stopT=" + stopT + ", cool=" + cool + ", maxRej=" + maxRej 
                     + ", maxTries=" + maxTries + ", maxSuc=" + maxSuc 
                     + ", graphtype=" + graphtype + ", algo=" + algo + ", trans=" + trans);
        N = 0;
        this.graphtype = graphtype;
        this.selectedalgo = algo;
        this.selectedtrans = trans;
        
        double[][] B = new double[250][250];
        countrynames = new String[250];

        try {
            if(graphtype == 1)//directed 
            {
                ReadCSV_d csvreader = new ReadCSV_d();
                csvreader.readfile("table-b4-ad.csv");
                csvreader.readfile("table-b4-af.csv");
                csvreader.readfile("table-b4-al.csv");
                /*csvreader.readfile("table-b4-am.csv");
                csvreader.readfile("table-b4-ao.csv");
                csvreader.readfile("table-b4-ar.csv");
                csvreader.readfile("table-b4-at.csv");
                csvreader.readfile("table-b4-au.csv");
                csvreader.readfile("table-b4-aw.csv");
                csvreader.readfile("table-b4-az.csv");
                csvreader.readfile("table-b4-ba.csv");
                csvreader.readfile("table-b4-bb.csv");
                csvreader.readfile("table-b4-be.csv");
                csvreader.readfile("table-b4-bf.csv");
                csvreader.readfile("table-b4-bg.csv");
                csvreader.readfile("table-b4-bh.csv");
                csvreader.readfile("table-b4-bi.csv");
                csvreader.readfile("table-b4-bj.csv");
                csvreader.readfile("table-b4-bm.csv");
                csvreader.readfile("table-b4-bn.csv");
                csvreader.readfile("table-b4-bo.csv");
                csvreader.readfile("table-b4-bq.csv");
                csvreader.readfile("table-b4-br.csv");
                csvreader.readfile("table-b4-bs.csv");
                csvreader.readfile("table-b4-bt.csv");
                csvreader.readfile("table-b4-bw.csv");
                csvreader.readfile("table-b4-by.csv");
                csvreader.readfile("table-b4-bz.csv");
                csvreader.readfile("table-b4-ca.csv");
                csvreader.readfile("table-b4-cd.csv");
                csvreader.readfile("table-b4-ci.csv");
                csvreader.readfile("table-b4-cl.csv");
                csvreader.readfile("table-b4-cm.csv");
                csvreader.readfile("table-b4-cn.csv");
                csvreader.readfile("table-b4-co.csv");
                csvreader.readfile("table-b4-cr.csv");
                csvreader.readfile("table-b4-cu.csv");
                csvreader.readfile("table-b4-cv.csv");
                csvreader.readfile("table-b4-cw.csv");
                csvreader.readfile("table-b4-cy.csv");
                csvreader.readfile("table-b4-cz.csv");
                csvreader.readfile("table-b4-de.csv");
                csvreader.readfile("table-b4-dk.csv");
                csvreader.readfile("table-b4-do.csv");
                csvreader.readfile("table-b4-dz.csv");
                csvreader.readfile("table-b4-ec.csv");
                csvreader.readfile("table-b4-ee.csv");
                csvreader.readfile("table-b4-eg.csv");
                csvreader.readfile("table-b4-es.csv");
                csvreader.readfile("table-b4-et.csv");
                csvreader.readfile("table-b4-fi.csv");
                csvreader.readfile("table-b4-fr.csv");
                csvreader.readfile("table-b4-ga.csv");
                csvreader.readfile("table-b4-gb.csv");
                csvreader.readfile("table-b4-ge.csv");
                csvreader.readfile("table-b4-gg.csv");
                csvreader.readfile("table-b4-gh.csv");
                csvreader.readfile("table-b4-gi.csv");
                csvreader.readfile("table-b4-gr.csv");
                csvreader.readfile("table-b4-gt.csv");
                csvreader.readfile("table-b4-hk.csv");
                csvreader.readfile("table-b4-hn.csv");
                csvreader.readfile("table-b4-hr.csv");
                csvreader.readfile("table-b4-hu.csv");
                csvreader.readfile("table-b4-id.csv");
                csvreader.readfile("table-b4-ie.csv");
                csvreader.readfile("table-b4-il.csv");
                csvreader.readfile("table-b4-im.csv");
                csvreader.readfile("table-b4-in.csv");
                csvreader.readfile("table-b4-iq.csv");
                csvreader.readfile("table-b4-is.csv");
                csvreader.readfile("table-b4-it.csv");
                csvreader.readfile("table-b4-je.csv");
                csvreader.readfile("table-b4-jm.csv");
                csvreader.readfile("table-b4-jo.csv");
                csvreader.readfile("table-b4-jp.csv");
                csvreader.readfile("table-b4-ke.csv");
                csvreader.readfile("table-b4-kh.csv");
                csvreader.readfile("table-b4-kr.csv");
                csvreader.readfile("table-b4-kw.csv");
                csvreader.readfile("table-b4-ky.csv");
                csvreader.readfile("table-b4-kz.csv");
                csvreader.readfile("table-b4-lb.csv");
                csvreader.readfile("table-b4-li.csv");
                csvreader.readfile("table-b4-lk.csv");
                csvreader.readfile("table-b4-lr.csv");
                csvreader.readfile("table-b4-lu.csv");
                csvreader.readfile("table-b4-lv.csv");
                csvreader.readfile("table-b4-ly.csv");
                csvreader.readfile("table-b4-ma.csv");
                csvreader.readfile("table-b4-mh.csv");
                csvreader.readfile("table-b4-mn.csv");
                csvreader.readfile("table-b4-mo.csv");
                csvreader.readfile("table-b4-mt.csv");
                csvreader.readfile("table-b4-mu.csv");
                csvreader.readfile("table-b4-mx.csv");
                csvreader.readfile("table-b4-my.csv");
                csvreader.readfile("table-b4-mz.csv");
                csvreader.readfile("table-b4-ng.csv");
                csvreader.readfile("table-b4-ni.csv");
                csvreader.readfile("table-b4-nl.csv");
                csvreader.readfile("table-b4-no.csv");
                csvreader.readfile("table-b4-nz.csv");
                csvreader.readfile("table-b4-om.csv");
                csvreader.readfile("table-b4-pa.csv");
                csvreader.readfile("table-b4-pe.csv");
                csvreader.readfile("table-b4-ph.csv");
                csvreader.readfile("table-b4-pk.csv");
                csvreader.readfile("table-b4-pl.csv");
                csvreader.readfile("table-b4-pt.csv");
                csvreader.readfile("table-b4-qa.csv");
                csvreader.readfile("table-b4-ro.csv");
                csvreader.readfile("table-b4-rs.csv");
                csvreader.readfile("table-b4-ru.csv");
                csvreader.readfile("table-b4-sa.csv");
                csvreader.readfile("table-b4-se.csv");
                csvreader.readfile("table-b4-sg.csv");
                csvreader.readfile("table-b4-si.csv");
                csvreader.readfile("table-b4-sk.csv");
                csvreader.readfile("table-b4-sn.csv");
                csvreader.readfile("table-b4-sv.csv");
                csvreader.readfile("table-b4-th.csv");
                csvreader.readfile("table-b4-tr.csv");
                csvreader.readfile("table-b4-tw.csv");
                csvreader.readfile("table-b4-ua.csv");
                csvreader.readfile("table-b4-us.csv");
                csvreader.readfile("table-b4-uy.csv");
                csvreader.readfile("table-b4-uz.csv");
                csvreader.readfile("table-b4-ve.csv");
                csvreader.readfile("table-b4-vn.csv");
                csvreader.readfile("table-b4-ws.csv");
                csvreader.readfile("table-b4-za.csv");
                csvreader.readfile("table-b4-zm.csv");
                */
                N = csvreader.getN();
                B = csvreader.getA();
                //checkContent(B);
                for(int i=0;i<N;i++)
                {
                    countrynames[i] = csvreader.getName(i);
                }

            }

            else//undirected
            {
                ReadCSV csvreader = new ReadCSV();
                csvreader.readfile("table-b4-ad.csv");
                csvreader.readfile("table-b4-af.csv");
                /*csvreader.readfile("table-b4-al.csv");
                csvreader.readfile("table-b4-am.csv");
                csvreader.readfile("table-b4-ao.csv");
                csvreader.readfile("table-b4-ar.csv");
                csvreader.readfile("table-b4-at.csv");
                csvreader.readfile("table-b4-au.csv");
                csvreader.readfile("table-b4-aw.csv");
                csvreader.readfile("table-b4-az.csv");
                csvreader.readfile("table-b4-ba.csv");
                csvreader.readfile("table-b4-bb.csv");
                csvreader.readfile("table-b4-be.csv");
                csvreader.readfile("table-b4-bf.csv");
                csvreader.readfile("table-b4-bg.csv");
                csvreader.readfile("table-b4-bh.csv");
                csvreader.readfile("table-b4-bi.csv");
                csvreader.readfile("table-b4-bj.csv");
                csvreader.readfile("table-b4-bm.csv");
                csvreader.readfile("table-b4-bn.csv");
                csvreader.readfile("table-b4-bo.csv");
                csvreader.readfile("table-b4-bq.csv");
                csvreader.readfile("table-b4-br.csv");
                csvreader.readfile("table-b4-bs.csv");
                csvreader.readfile("table-b4-bt.csv");
                csvreader.readfile("table-b4-bw.csv");
                csvreader.readfile("table-b4-by.csv");
                csvreader.readfile("table-b4-bz.csv");
                csvreader.readfile("table-b4-ca.csv");
                csvreader.readfile("table-b4-cd.csv");
                csvreader.readfile("table-b4-ci.csv");
                csvreader.readfile("table-b4-cl.csv");
                csvreader.readfile("table-b4-cm.csv");
                csvreader.readfile("table-b4-cn.csv");
                csvreader.readfile("table-b4-co.csv");
                csvreader.readfile("table-b4-cr.csv");
                csvreader.readfile("table-b4-cu.csv");
                csvreader.readfile("table-b4-cv.csv");
                csvreader.readfile("table-b4-cw.csv");
                csvreader.readfile("table-b4-cy.csv");
                csvreader.readfile("table-b4-cz.csv");
                csvreader.readfile("table-b4-de.csv");
                csvreader.readfile("table-b4-dk.csv");
                csvreader.readfile("table-b4-do.csv");
                csvreader.readfile("table-b4-dz.csv");
                csvreader.readfile("table-b4-ec.csv");
                csvreader.readfile("table-b4-ee.csv");
                csvreader.readfile("table-b4-eg.csv");
                csvreader.readfile("table-b4-es.csv");
                csvreader.readfile("table-b4-et.csv");
                csvreader.readfile("table-b4-fi.csv");
                csvreader.readfile("table-b4-fr.csv");
                csvreader.readfile("table-b4-ga.csv");
                csvreader.readfile("table-b4-gb.csv");
                csvreader.readfile("table-b4-ge.csv");
                csvreader.readfile("table-b4-gg.csv");
                csvreader.readfile("table-b4-gh.csv");
                csvreader.readfile("table-b4-gi.csv");
                csvreader.readfile("table-b4-gr.csv");
                csvreader.readfile("table-b4-gt.csv");
                csvreader.readfile("table-b4-hk.csv");
                csvreader.readfile("table-b4-hn.csv");
                csvreader.readfile("table-b4-hr.csv");
                csvreader.readfile("table-b4-hu.csv");
                csvreader.readfile("table-b4-id.csv");
                csvreader.readfile("table-b4-ie.csv");
                csvreader.readfile("table-b4-il.csv");
                csvreader.readfile("table-b4-im.csv");
                csvreader.readfile("table-b4-in.csv");
                csvreader.readfile("table-b4-iq.csv");
                csvreader.readfile("table-b4-is.csv");
                csvreader.readfile("table-b4-it.csv");
                csvreader.readfile("table-b4-je.csv");
                csvreader.readfile("table-b4-jm.csv");
                csvreader.readfile("table-b4-jo.csv");
                csvreader.readfile("table-b4-jp.csv");
                csvreader.readfile("table-b4-ke.csv");
                csvreader.readfile("table-b4-kh.csv");
                csvreader.readfile("table-b4-kr.csv");
                csvreader.readfile("table-b4-kw.csv");
                csvreader.readfile("table-b4-ky.csv");
                csvreader.readfile("table-b4-kz.csv");
                csvreader.readfile("table-b4-lb.csv");
                csvreader.readfile("table-b4-li.csv");
                csvreader.readfile("table-b4-lk.csv");
                csvreader.readfile("table-b4-lr.csv");
                csvreader.readfile("table-b4-lu.csv");
                csvreader.readfile("table-b4-lv.csv");
                csvreader.readfile("table-b4-ly.csv");
                csvreader.readfile("table-b4-ma.csv");
                csvreader.readfile("table-b4-mh.csv");
                csvreader.readfile("table-b4-mn.csv");
                csvreader.readfile("table-b4-mo.csv");
                csvreader.readfile("table-b4-mt.csv");
                csvreader.readfile("table-b4-mu.csv");
                csvreader.readfile("table-b4-mx.csv");
                csvreader.readfile("table-b4-my.csv");
                csvreader.readfile("table-b4-mz.csv");
                csvreader.readfile("table-b4-ng.csv");
                csvreader.readfile("table-b4-ni.csv");
                csvreader.readfile("table-b4-nl.csv");
                csvreader.readfile("table-b4-no.csv");
                csvreader.readfile("table-b4-nz.csv");
                csvreader.readfile("table-b4-om.csv");
                csvreader.readfile("table-b4-pa.csv");
                csvreader.readfile("table-b4-pe.csv");
                csvreader.readfile("table-b4-ph.csv");
                csvreader.readfile("table-b4-pk.csv");
                csvreader.readfile("table-b4-pl.csv");
                csvreader.readfile("table-b4-pt.csv");
                csvreader.readfile("table-b4-qa.csv");
                csvreader.readfile("table-b4-ro.csv");
                csvreader.readfile("table-b4-rs.csv");
                csvreader.readfile("table-b4-ru.csv");
                csvreader.readfile("table-b4-sa.csv");
                csvreader.readfile("table-b4-se.csv");
                csvreader.readfile("table-b4-sg.csv");
                csvreader.readfile("table-b4-si.csv");
                csvreader.readfile("table-b4-sk.csv");
                csvreader.readfile("table-b4-sn.csv");
                csvreader.readfile("table-b4-sv.csv");
                csvreader.readfile("table-b4-th.csv");
                csvreader.readfile("table-b4-tr.csv");
                csvreader.readfile("table-b4-tw.csv");
                csvreader.readfile("table-b4-ua.csv");
                csvreader.readfile("table-b4-us.csv");
                csvreader.readfile("table-b4-uy.csv");
                csvreader.readfile("table-b4-uz.csv");
                csvreader.readfile("table-b4-ve.csv");
                csvreader.readfile("table-b4-vn.csv");
                csvreader.readfile("table-b4-ws.csv");
                csvreader.readfile("table-b4-za.csv");
                csvreader.readfile("table-b4-zm.csv");
                */
                N = csvreader.getN();
                B = csvreader.getA();
                for(int i=0;i<N;i++)
                {
                    countrynames[i] = csvreader.getName(i);
                }
            }//else
        } catch (FileNotFoundException fe){
            System.out.println("Cannot file required files: " + fe.toString());
            return;
        } catch (IOException e) {
            System.out.println("In run function:" + e.toString());
        } catch (Exception e) {
            System.out.println("Read file error: " + e.toString());
            return;
        }

        System.out.println("!!N:"+ N);
        
        if(selectedalgo == 1)
        {
            step_input = 100;
            steps = step_input;
        }
        else
        {
            this.T = T;
            this.stopT = step;
            this.cool = cool;
            this.maxRej = maxRej;
            this.maxTries = maxTries;
            this.maxSuc = maxSuc;
        }
        
        if(selectedtrans == 3)
        {
            this.oa = oa;
            this.oai = oai;
        }
        else
        {
            aa = a;
            this.ai = ai;
            bb = b;
            this.bi = bi;            
        }
        //System.out.println("!!"+aa+ai+bb+bi+steps);
        A = new double[N][N];
        
        
        for(int i =0 ;i<N;i++)
        {
            for(int j = 0;j<N;j++)
                A[i][j] = B[i][j];
        }
        
        //checkContent(A);
        
        this.a = aa;
        this.b = bb;

        double[] Cstar = new double[N];
        CS_out = new double[N];
        CS_in = new double[N];
        CS = new double[N];
        
        for(int j=0;j<N;j++)
        {
            CS_out[j] = 0;
            CS_in[j] = 0;
            CS[j] =0;
        }
        
        while (a <= 1)
        {
            
            while(b<=1)
            {
                for(int i=0; i<N; i++)
                {
                    if(selectedtrans == 1)
                        Cstar[i] = sharpTransition(i,a,b,N);
                    else
                        Cstar[i] = smoothTransition(i,a,b,N);
                    //System.out.println("CStar " + i + " :"+ Cstar[i]);
                }
                if(selectedalgo==1)
                {
                    if(graphtype ==1)
                        greedyAlgo_d(A,a,b,Cstar,N,steps);
                    else
                        greedyAlgo(A,a,b,Cstar,N,steps);
                }
                else
                {
                    if(graphtype == 1)
                        simulatedAlgo_d(A,a,b,Cstar,N,T,stopT,cool,maxRej,maxTries,maxSuc);
                    else
                        simulatedAlgo(A,a,b,Cstar,N,T,stopT,cool,maxRej,maxTries,maxSuc);
                }
                b += bi;
                //System.out.println("'a' changed to " + a);
                //System.out.println("'b' changed to " + b);
                
                //System.out.println();
                
                
            }
            b = bb;
            a += ai;
            
        }//while
        
        //for(int q=0;q<pnum*steps;q++)
        //{
        //  for(int w=0;w<5;w++)
        //    System.out.print(Cstarps.get(q)[w]+"   ");
        //System.out.print("\n");
        
        //}
        Collections.sort(RValues);
        RValue BestR = RValues.get(0);
        
        //System.out.println("BestR:" + BestR.getR() + " when a=" + BestR.getA() + " and b=" + BestR.getB());
        
        if(graphtype == 1)
            calculateCS_d();
        else
            calculateCS();
        
        if(graphtype == 1)
            WriteGEXF_d.praser(A,CS_out,CS_in,N,countrynames);
        else
            WriteGEXF.praser(A,CS,N,countrynames);
        
    }//main
    
    private  double getRValue_d(double[] source, double[] target)
    {
        double R = 0;
        for (int j = 0; j < N; j++)
        {
            for (int i = 0; i < N; i++)
            {
                R += source[i] * target[j] * A[i][j];
            }//for
        }//for
        
        return R;
    }//getRValue
    
    private  double getRValue(double[] currentCstar)
    {
        double R = 0;
        for (int j = 0; j < N; j++)
        {
            for (int i = 0; i < N; i++)
            {
                R += currentCstar[i] * currentCstar[j] * A[i][j];
            }//for
        }//for
        
        return R;
    }//getRValue
    
    private  double sharpTransition(int i, double a, double b, int N)
    {
        if(i <= b*N)
            return i*(1-a)/(2*b*N);
        else
            return (i-b*N)*(1-a)/(2*(N-b*N)) + (1+a)/2;
    }//sharpTransition
    
    private  double smoothTransition(int i, double a, double b, int N)
    {
        return (1 / ( 1 + Math.exp(-(i - N * b)* Math.tan(Math.PI * a /2))));
    }//smoothTransition
    
private  double[] randomSwap(double[] currentC, double[] newC, int N)
    {
        newC = (double[])currentC.clone();
        
        int index1 = (int)(Math.random() * N);
        int index2 = (int)(Math.random() * N);
        
        while(index1 == index2)
        {
            index1 = (int)(Math.random() * N);
            index2 = (int)(Math.random() * N);
        }
        
        double temp;
        temp = newC[index1];
        newC[index1] = newC[index2];
        newC[index2] = temp;
        
        return newC;

    }//randomSwap
    private  void greedyAlgo(double[][] A, double a, double b, double[] Cstar, int N, int steps)
    {
        double[] currentC = new double[N];
        currentC = (double[])Cstar.clone();
        
        double[] newC = new double[N];
        
        
        double currentR = getRValue(currentC);
        double newR = 0;
        
        for(int i = 1;i<= steps;i++)
        {
            newC = randomSwap(currentC,newC,N);
            newR = getRValue(newC);
                
            if(newR > currentR)
            {
                currentC = (double[])newC.clone();
                currentR = newR;
            }//if
        }//for
        RValue finalRValue = new RValue(a, b, currentR);
        //System.out.println(finalRValue);
        RValues.add(finalRValue);
        
        for(int j=0;j<N;j++)
        {
            CS[j] = CS[j] + currentC[j] * currentR;
            
            //System.out.println("CS[" + j + "]= " + CS[j] + " a,b: " + finalRValue.getA()+finalRValue.getB());
        }
        
    }//greedyAlgo
    
    private  void simulatedAlgo(double[][] A, double a, double b, double[] Cstar, int N, double T,double stopT,double cool, int maxRej, int maxTries, int maxSuccess)
    {
        double[] currentC = new double[N];
        currentC = (double[])Cstar.clone();
        
        double[] bestC = new double[N];
        bestC = (double[])currentC.clone();
        
        //for(int i=0;i<N;i++)
          //  System.out.println("originalC: "+currentC[i]);
        double[] newC = new double[N];
        
        
        double currentR = getRValue(currentC);
        double bestR = currentR;
        double newR = 0;
        
        int noRej = 0,noTries = 0,noSuccess = 0;
        boolean finished = false;
        
        while(finished == false)
        {
            noTries++;
            
            if(noTries >= maxTries || noSuccess >=maxSuccess)
            {
                if(T < stopT || noRej >= maxRej)
                {
                    finished = true;
                    break;
                }//if
                else{
                    T = T * cool;
                    noTries = 1;
                    noSuccess = 1;
                }//else
            }//if
            
            newC = randomSwap(currentC,newC,N);
            newR = getRValue(newC);
            
            if(newR > currentR)
            {
                currentC =(double[])newC.clone();
                currentR = newR;
                
                if(newR > bestR)
                {
                    bestC = (double[])newC.clone();
                    bestR = newR;
                }
                noSuccess++;
                noRej = 0;
            }//if
            
            else
            {
                if(Math.random() < Math.exp((newR - currentR) /T))
                {
                    currentC = (double[])newC.clone();
                    currentR = newR;
                    noSuccess++;
                    noRej = 0;
                }//if
                else
                    noRej++;
            }//else
        }//while
        
        RValue finalRValue = new RValue(a, b, bestR);
        //System.out.println(finalRValue);
        RValues.add(finalRValue);
        
        for(int j=0;j<N;j++)
        {
            CS[j] = CS[j] + bestC[j] * bestR;
            //System.out.println("CS[" + j + "]= " + CS[j] + " a,b: " + finalRValue.getA()+finalRValue.getB());
        }
        
    }//simulatedAlgo

   
    private  void calculateCS()
    {
        double maxCS = CS[0];
        for(int i=1; i<N; i++)
        {
            if(CS[i] > maxCS)
                maxCS = CS[i];
        }
        int c=0,d=0,e=0,f=0,g=0,h=0,o=0,p=0,q=0,r=0;
        for(int j=0;j<N;j++)
        {
            CS[j] = CS[j]/maxCS;
            if(CS[j]>0 && CS[j]<=0.1)
                c++;
            else if(CS[j]>0.1 && CS[j]<=0.2)
                d++;
            else if(CS[j]>0.2 && CS[j]<=0.3)
                e++;
            else if(CS[j]>0.3 && CS[j]<=0.4)
                f++;
            else if(CS[j]>0.4 && CS[j]<=0.5)
                g++;
            else if(CS[j]>0.5 && CS[j]<=0.6)
                h++;
            else if(CS[j]>0.6 && CS[j]<=0.7)
                o++;
            else if(CS[j]>0.7 && CS[j]<=0.8)
                p++;
            else if(CS[j]>0.8 && CS[j]<=0.9)
                q++;
            else if(CS[j]>0.9 && CS[j]<=1)
                r++;
            System.out.println(countrynames[j]+": "+CS[j]);
            //System.out.println(countrynames[j]);
        }
        
        //for(int j=0;j<N;j++)
        //{
            //System.out.println(countrynames[j]+" "+CS[j]);
            //System.out.println(CS[j]);
        //}
        //System.out.println("!!"+c+" "+d+" "+e+ " "+f+" " +g+" "+ h+" "+o+" "+p+" "+q+" "+ r);
    }
    
    private  void greedyAlgo_d(double[][] A, double a, double b, double[] Cstar, int N, int steps)
    {
        double[] currentSC = new double[N];
        currentSC = (double[])Cstar.clone();
        
        double[] currentTC = new double[N];
        currentTC = (double[])Cstar.clone();
        
        double[] sourceC = new double[N];
        double[] targetC = new double[N];
        
        
        double currentR = getRValue_d(currentSC,currentTC);
        double newR = 0;
        
        for(int i = 1;i<= steps;i++)
        {
            sourceC = randomSwap(currentSC,sourceC,N);
            
            for(int j=1;j<= steps;j++)
            {
                targetC = randomSwap(currentTC,targetC,N);
                newR = getRValue_d(sourceC,targetC);
                
            }
                
            if(newR > currentR)
            {
                currentSC = (double[])sourceC.clone();
                currentTC = (double[])targetC.clone();
                currentR = newR;
            }//if
        }//for
        RValue finalRValue = new RValue(a, b, currentR);
        //System.out.println(finalRValue);
        RValues.add(finalRValue);
        
        for(int j=0;j<N;j++)
        {
            CS_out[j] = CS_out[j] + currentSC[j] * currentR;
            CS_in[j] = CS_in[j] + currentTC[j] * currentR;
            
            //System.out.println("CS[" + j + "]= " + CS[j] + " a,b: " + finalRValue.getA()+finalRValue.getB());
        }
        
    }//greedyAlgo
    
    
    private  void simulatedAlgo_d(double[][] A, double a, double b, double[] Cstar, int N, double T,double stopT,double cool, int maxRej, int maxTries, int maxSuccess)
    {
        double[] currentC_s = new double[N];
        currentC_s = (double[])Cstar.clone();
        
        double[] currentC_t = new double[N];
        currentC_t = (double[])Cstar.clone();
        
        double[] bestC_s = new double[N];
        bestC_s = (double[])currentC_s.clone();
        
        double[] bestC_t = new double[N];
        bestC_t = (double[])currentC_t.clone();
        
        //for(int i=0;i<N;i++)
          //  System.out.println("originalC: "+currentC[i]);
        double[] sourceC = new double[N];
        double[] targetC = new double[N];
        
        
        double currentR = getRValue_d(currentC_s,currentC_t);
        double bestR = currentR;
        double newR = 0;
        
        int noRej = 0,noTries = 0,noSuccess = 0;
        boolean finished = false;
        
        while(finished == false)
        {
            noTries++;
            
            if(noTries >= maxTries || noSuccess >=maxSuccess)
            {
                if(T < stopT || noRej >= maxRej)
                {
                    finished = true;
                    break;
                }//if
                else{
                    T = T * cool;
                    noTries = 1;
                    noSuccess = 1;
                }//else
            }//if
            
            sourceC = randomSwap(currentC_s,sourceC,N);
            targetC = randomSwap(currentC_t,targetC,N);
            
            newR = getRValue_d(sourceC, targetC);
            
            if(newR > currentR)
            {
                currentC_s =(double[])sourceC.clone();
                currentC_t =(double[])targetC.clone();
                currentR = newR;
                
                if(newR > bestR)
                {
                    bestC_s = (double[])sourceC.clone();
                    bestC_t = (double[])targetC.clone();
                    bestR = newR;
                }
                noSuccess++;
                noRej = 0;
            }//if
            
            else
            {
                if(Math.random() < Math.exp((newR - currentR) /T))
                {
                    currentC_s = (double[])sourceC.clone();
                    currentC_t =(double[])targetC.clone();
                    currentR = newR;
                    noSuccess++;
                    noRej = 0;
                }//if
                else
                    noRej++;
            }//else
        }//while
        
        RValue finalRValue = new RValue(a, b, bestR);
        //System.out.println(finalRValue);
        RValues.add(finalRValue);
        
        for(int j=0;j<N;j++)
        {
            CS_out[j] = CS_out[j] + bestC_s[j] * bestR;
            CS_in[j] = CS_in[j] + bestC_t[j] * bestR;
            //System.out.println("CS[" + j + "]= " + CS[j] + " a,b: " + finalRValue.getA()+finalRValue.getB());
        }
        
    }//simulatedAlgo
    

   
    private  void calculateCS_d()
    {
        double maxCS_out = CS_out[0];
        double maxCS_in = CS_in[0];
        
        for(int i=1; i<N; i++)
        {
            if(CS_out[i] > maxCS_out)
                maxCS_out = CS_out[i];
            
            if(CS_in[i] > maxCS_in)
                maxCS_in = CS_in[i];
        }
        
        //int c=0,d=0,e=0,f=0,g=0,h=0,o=0,p=0,q=0,r=0;
        for(int j=0;j<N;j++)
        {
            CS_out[j] = CS_out[j]/maxCS_out;
            CS_in[j] = CS_in[j]/maxCS_in;
            /*
            if(CS[j]>0 && CS[j]<=0.1)
                c++;
            else if(CS[j]>0.1 && CS[j]<=0.2)
                d++;
            else if(CS[j]>0.2 && CS[j]<=0.3)
                e++;
            else if(CS[j]>0.3 && CS[j]<=0.4)
                f++;
            else if(CS[j]>0.4 && CS[j]<=0.5)
                g++;
            else if(CS[j]>0.5 && CS[j]<=0.6)
                h++;
            else if(CS[j]>0.6 && CS[j]<=0.7)
                o++;
            else if(CS[j]>0.7 && CS[j]<=0.8)
                p++;
            else if(CS[j]>0.8 && CS[j]<=0.9)
                q++;
            else if(CS[j]>0.9 && CS[j]<=1)
                r++;
*/
            System.out.println(countrynames[j]+": "+"Out-corness: "+CS_out[j]+"  In-corness: "+CS_in[j]);
            //System.out.println(countrynames[j]);
        }
        
        //for(int j=0;j<N;j++)
        //{
            //System.out.println(countrynames[j]+" "+CS[j]);
           // System.out.println(CS[j]);
        //}
        //System.out.println("!!"+c+" "+d+" "+e+ " "+f+" " +g+" "+ h+" "+o+" "+p+" "+q+" "+ r);
    }
    
    private  void checkContent(double[][] values)
    {
        for (int j = 0; j < N; j++)
        {
            for (int i = 0; i < N; i++)
            {
                System.out.print(values[i][j]+" ");
            }//for
            System.out.println();
        }//for
    }//checkContent
}//class
