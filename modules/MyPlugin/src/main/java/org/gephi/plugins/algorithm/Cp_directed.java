package org.gephi.plugins.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
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
    private int eu;
    private int asia;
    private int africa;
    private int na;
    private int sa;
    private int oc;
    private int anta;
    
    private  int graphtype; //1->directed 2->undirected
    private  int selectedalgo;//1->greedy 2->SA
    private  int selectedtrans;//1->sharp 2->smooth 3->one-parameter
    private int year;
    
    private  ArrayList<RValue> RValues = new ArrayList<>();
    private  String[] countrynames;
    
    public Cp_directed(){
    }
    
    public void run(double a, double ai, double b, double bi, double oa, double oai, Integer step, double T
                    , double stopT, double cool, int maxRej, int maxTries, int maxSuc
                    , int graphtype, int algo, int trans,int eu, int asia, int africa, int na, int sa, int oc, int anta,int year) throws IOException 
    {
        System.out.println("a=" + a + ", ai=" + ai + ", b=" + b + ", bi=" + bi + ", oa=" + oa
                     + ", oai=" + oai + ", step=" + step + ", T=" + T 
                     + ", stopT=" + stopT + ", cool=" + cool + ", maxRej=" + maxRej 
                     + ", maxTries=" + maxTries + ", maxSuc=" + maxSuc 
                     + ", graphtype=" + graphtype + ", algo=" + algo + ", trans=" + trans + ",year =" + year
                     + ", eu = "+ eu + ", asia = "+asia + "africa = "+ africa + ",north america = "+ na
                     + ",south america= "+ sa + ",oceania = "+ oc+", antarctica = "+anta );
        
        N = 0;
        this.graphtype = graphtype;
        this.selectedalgo = algo;
        this.selectedtrans = trans;
        this.year = year;
        
        double[][] B = new double[250][250];
        countrynames = new String[250];

        String basePath = "/Users/Cecilia/Desktop/Cecilia/Project/gephi-plugins/modules/MyPlugin/src/main/resources/org/gephi/plugins/csvfiles/";
        
        try {
            if(graphtype == 1)//directed 
            {
                ReadCSV_d csvreader = new ReadCSV_d();
                
                
                if(africa == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ao.csv",year);
                    csvreader.readfile(basePath +"table-b4-bf.csv",year);
                    csvreader.readfile(basePath +"table-b4-bi.csv",year);
                    csvreader.readfile(basePath +"table-b4-bj.csv",year);
                    csvreader.readfile(basePath +"table-b4-bw.csv",year);
                    csvreader.readfile(basePath +"table-b4-cv.csv",year);
                    csvreader.readfile(basePath +"table-b4-cm.csv",year);
                    csvreader.readfile(basePath +"table-b4-dz.csv",year);
                    csvreader.readfile(basePath +"table-b4-eg.csv",year);
                    csvreader.readfile(basePath +"table-b4-et.csv",year);
                    csvreader.readfile(basePath +"table-b4-ga.csv",year);
                    csvreader.readfile(basePath +"table-b4-gh.csv",year);
                    csvreader.readfile(basePath +"table-b4-ke.csv",year);
                    csvreader.readfile(basePath +"table-b4-ly.csv",year);
                    csvreader.readfile(basePath +"table-b4-ma.csv",year);
                    csvreader.readfile(basePath +"table-b4-mu.csv",year);
                    csvreader.readfile(basePath +"table-b4-mz.csv",year);
                    csvreader.readfile(basePath +"table-b4-ng.csv",year);
                    csvreader.readfile(basePath +"table-b4-sn.csv",year);
                    csvreader.readfile(basePath +"table-b4-zm.csv",year);
                    csvreader.readfile(basePath +"table-b4-cd.csv",year);
                    csvreader.readfile(basePath +"table-b4-lr.csv",year);
                    csvreader.readfile(basePath +"table-b4-za.csv",year);
                }//africancountries
                
                if(na == 1)
                {
                    csvreader.readfile(basePath +"table-b4-aw.csv",year);
                    csvreader.readfile(basePath +"table-b4-bb.csv",year);
                    csvreader.readfile(basePath +"table-b4-bm.csv",year);
                    csvreader.readfile(basePath +"table-b4-bs.csv",year);
                    csvreader.readfile(basePath +"table-b4-ca.csv",year);
                    csvreader.readfile(basePath +"table-b4-cr.csv",year);
                    csvreader.readfile(basePath +"table-b4-cu.csv",year);
                    csvreader.readfile(basePath +"table-b4-gt.csv",year);
                    csvreader.readfile(basePath +"table-b4-hn.csv",year);
                    csvreader.readfile(basePath +"table-b4-jm.csv",year);
                    csvreader.readfile(basePath +"table-b4-mx.csv",year);
                    csvreader.readfile(basePath +"table-b4-ni.csv",year);
                    csvreader.readfile(basePath +"table-b4-pa.csv",year);
                    csvreader.readfile(basePath +"table-b4-us.csv",year);
                    csvreader.readfile(basePath +"table-b4-bz.csv",year);
                    csvreader.readfile(basePath +"table-b4-cw.csv",year);
                    csvreader.readfile(basePath +"table-b4-do.csv",year);
                    csvreader.readfile(basePath +"table-b4-ge.csv",year);
                    csvreader.readfile(basePath +"table-b4-je.csv",year); 
                    csvreader.readfile(basePath +"table-b4-ky.csv",year);
                    csvreader.readfile(basePath +"table-b4-sv.csv",year);
                }//northamericacountries
                
                if(sa == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ar.csv",year);
                    csvreader.readfile(basePath +"table-b4-bo.csv",year);
                    csvreader.readfile(basePath +"table-b4-br.csv",year);
                    csvreader.readfile(basePath +"table-b4-co.csv",year);
                    csvreader.readfile(basePath +"table-b4-cl.csv",year);
                    csvreader.readfile(basePath +"table-b4-ec.csv",year);
                    csvreader.readfile(basePath +"table-b4-pe.csv",year);
                    csvreader.readfile(basePath +"table-b4-uy.csv",year);
                    csvreader.readfile(basePath +"table-b4-ci.csv",year);
                    csvreader.readfile(basePath +"table-b4-ve.csv",year);
                }//southamericacountries
                
                if(oc == 1)
                {
                    csvreader.readfile(basePath +"table-b4-au.csv",year);
                    csvreader.readfile(basePath +"table-b4-nz.csv",year);
                    csvreader.readfile(basePath +"table-b4-mh.csv",year); 
                }//oceaniacountries
                
                if(anta == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ws.csv",year);
                }//antarcticacountries
                 if(eu == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ad.csv",year);
                    csvreader.readfile(basePath +"table-b4-al.csv",year);
                    csvreader.readfile(basePath +"table-b4-at.csv",year);
                    csvreader.readfile(basePath +"table-b4-ba.csv",year);
                    csvreader.readfile(basePath +"table-b4-be.csv",year);
                    csvreader.readfile(basePath +"table-b4-bg.csv",year);
                    csvreader.readfile(basePath +"table-b4-ch.csv",year);
                    csvreader.readfile(basePath +"table-b4-cz.csv",year);
                    csvreader.readfile(basePath +"table-b4-de.csv",year);
                    csvreader.readfile(basePath +"table-b4-dk.csv",year);
                    csvreader.readfile(basePath +"table-b4-ee.csv",year);
                    csvreader.readfile(basePath +"table-b4-es.csv",year);
                    csvreader.readfile(basePath +"table-b4-fi.csv",year);
                    csvreader.readfile(basePath +"table-b4-fr.csv",year);
                    csvreader.readfile(basePath +"table-b4-gb.csv",year);
                    csvreader.readfile(basePath +"table-b4-gr.csv",year);
                    csvreader.readfile(basePath +"table-b4-hr.csv",year);
                    csvreader.readfile(basePath +"table-b4-hu.csv",year);
                    csvreader.readfile(basePath +"table-b4-ie.csv",year);
                    csvreader.readfile(basePath +"table-b4-is.csv",year);
                    csvreader.readfile(basePath +"table-b4-it.csv",year);
                    csvreader.readfile(basePath +"table-b4-lu.csv",year);
                    csvreader.readfile(basePath +"table-b4-lv.csv",year);
                    csvreader.readfile(basePath +"table-b4-mt.csv",year);
                    csvreader.readfile(basePath +"table-b4-nl.csv",year);
                    csvreader.readfile(basePath +"table-b4-no.csv",year);
                    csvreader.readfile(basePath +"table-b4-pl.csv",year);
                    csvreader.readfile(basePath +"table-b4-pt.csv",year);
                    csvreader.readfile(basePath +"table-b4-ro.csv",year);
                    csvreader.readfile(basePath +"table-b4-ru.csv",year);
                    csvreader.readfile(basePath +"table-b4-se.csv",year);
                    csvreader.readfile(basePath +"table-b4-sk.csv",year);
                    csvreader.readfile(basePath +"table-b4-ua.csv",year);
                    csvreader.readfile(basePath +"table-b4-by.csv",year);
                    csvreader.readfile(basePath +"table-b4-gg.csv",year);
                    csvreader.readfile(basePath +"table-b4-gi.csv",year);
                    csvreader.readfile(basePath +"table-b4-im.csv",year);
                    csvreader.readfile(basePath +"table-b4-li.csv",year);
                    csvreader.readfile(basePath +"table-b4-rs.csv",year);
                    csvreader.readfile(basePath +"table-b4-si.csv",year);
                    csvreader.readfile(basePath +"table-b4-tr.csv",year);
                }//eucountries
                
                if(asia == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ae.csv",year);
                    csvreader.readfile(basePath +"table-b4-af.csv",year);
                    csvreader.readfile(basePath +"table-b4-am.csv",year);
                    csvreader.readfile(basePath +"table-b4-az.csv",year);
                    csvreader.readfile(basePath +"table-b4-bd.csv",year);
                    csvreader.readfile(basePath +"table-b4-bh.csv",year);
                    csvreader.readfile(basePath +"table-b4-bn.csv",year);
                    csvreader.readfile(basePath +"table-b4-bt.csv",year);
                    csvreader.readfile(basePath +"table-b4-cn.csv",year);
                    csvreader.readfile(basePath +"table-b4-cy.csv",year);
                    csvreader.readfile(basePath +"table-b4-hk.csv",year);
                    csvreader.readfile(basePath +"table-b4-id.csv",year);
                    csvreader.readfile(basePath +"table-b4-il.csv",year);
                    csvreader.readfile(basePath +"table-b4-in.csv",year);
                    csvreader.readfile(basePath +"table-b4-iq.csv",year);
                    csvreader.readfile(basePath +"table-b4-jo.csv",year);
                    csvreader.readfile(basePath +"table-b4-jp.csv",year);
                    csvreader.readfile(basePath +"table-b4-kh.csv",year);
                    csvreader.readfile(basePath +"table-b4-kr.csv",year);
                    csvreader.readfile(basePath +"table-b4-kw.csv",year);
                    csvreader.readfile(basePath +"table-b4-kz.csv",year);
                    csvreader.readfile(basePath +"table-b4-lb.csv",year);
                    csvreader.readfile(basePath +"table-b4-mn.csv",year);
                    csvreader.readfile(basePath +"table-b4-mo.csv",year);
                    csvreader.readfile(basePath +"table-b4-my.csv",year);
                    csvreader.readfile(basePath +"table-b4-om.csv",year);
                    csvreader.readfile(basePath +"table-b4-ph.csv",year);
                    csvreader.readfile(basePath +"table-b4-pk.csv",year);
                    csvreader.readfile(basePath +"table-b4-qa.csv",year);
                    csvreader.readfile(basePath +"table-b4-sa.csv",year);
                    csvreader.readfile(basePath +"table-b4-sg.csv",year);
                    csvreader.readfile(basePath +"table-b4-th.csv",year);
                    csvreader.readfile(basePath +"table-b4-tw.csv",year);
                    csvreader.readfile(basePath +"table-b4-uz.csv",year);
                    csvreader.readfile(basePath +"table-b4-lk.csv",year);
                    csvreader.readfile(basePath +"table-b4-vn.csv",year);
                }//asiacountries
          
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
                
                if(africa == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ao.csv",year);
                    csvreader.readfile(basePath +"table-b4-bf.csv",year);
                    csvreader.readfile(basePath +"table-b4-bi.csv",year);
                    csvreader.readfile(basePath +"table-b4-bj.csv",year);
                    csvreader.readfile(basePath +"table-b4-bw.csv",year);
                    csvreader.readfile(basePath +"table-b4-cv.csv",year);
                    csvreader.readfile(basePath +"table-b4-cm.csv",year);
                    csvreader.readfile(basePath +"table-b4-dz.csv",year);
                    csvreader.readfile(basePath +"table-b4-eg.csv",year);
                    csvreader.readfile(basePath +"table-b4-et.csv",year);
                    csvreader.readfile(basePath +"table-b4-ga.csv",year);
                    csvreader.readfile(basePath +"table-b4-gh.csv",year);
                    csvreader.readfile(basePath +"table-b4-ke.csv",year);
                    csvreader.readfile(basePath +"table-b4-ly.csv",year);
                    csvreader.readfile(basePath +"table-b4-ma.csv",year);
                    csvreader.readfile(basePath +"table-b4-mu.csv",year);
                    csvreader.readfile(basePath +"table-b4-mz.csv",year);
                    csvreader.readfile(basePath +"table-b4-ng.csv",year);
                    csvreader.readfile(basePath +"table-b4-sn.csv",year);
                    csvreader.readfile(basePath +"table-b4-zm.csv",year);
                    csvreader.readfile(basePath +"table-b4-cd.csv",year);
                    csvreader.readfile(basePath +"table-b4-lr.csv",year);
                    csvreader.readfile(basePath +"table-b4-za.csv",year);
                }//africancountries
                
                if(na == 1)
                {
                    csvreader.readfile(basePath +"table-b4-aw.csv",year);
                    csvreader.readfile(basePath +"table-b4-bb.csv",year);
                    csvreader.readfile(basePath +"table-b4-bm.csv",year);
                    csvreader.readfile(basePath +"table-b4-bs.csv",year);
                    csvreader.readfile(basePath +"table-b4-ca.csv",year);
                    csvreader.readfile(basePath +"table-b4-cr.csv",year);
                    csvreader.readfile(basePath +"table-b4-cu.csv",year);
                    csvreader.readfile(basePath +"table-b4-gt.csv",year);
                    csvreader.readfile(basePath +"table-b4-hn.csv",year);
                    csvreader.readfile(basePath +"table-b4-jm.csv",year);
                    csvreader.readfile(basePath +"table-b4-mx.csv",year);
                    csvreader.readfile(basePath +"table-b4-ni.csv",year);
                    csvreader.readfile(basePath +"table-b4-pa.csv",year);
                    csvreader.readfile(basePath +"table-b4-us.csv",year);
                    csvreader.readfile(basePath +"table-b4-bz.csv",year);
                    csvreader.readfile(basePath +"table-b4-cw.csv",year);
                    csvreader.readfile(basePath +"table-b4-do.csv",year);
                    csvreader.readfile(basePath +"table-b4-ge.csv",year);
                    csvreader.readfile(basePath +"table-b4-je.csv",year); 
                    csvreader.readfile(basePath +"table-b4-ky.csv",year);
                    csvreader.readfile(basePath +"table-b4-sv.csv",year);
                }//northamericacountries
                
                if(sa == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ar.csv",year);
                    csvreader.readfile(basePath +"table-b4-bo.csv",year);
                    csvreader.readfile(basePath +"table-b4-br.csv",year);
                    csvreader.readfile(basePath +"table-b4-co.csv",year);
                    csvreader.readfile(basePath +"table-b4-cl.csv",year);
                    csvreader.readfile(basePath +"table-b4-ec.csv",year);
                    csvreader.readfile(basePath +"table-b4-pe.csv",year);
                    csvreader.readfile(basePath +"table-b4-uy.csv",year);
                    csvreader.readfile(basePath +"table-b4-ci.csv",year);
                    csvreader.readfile(basePath +"table-b4-ve.csv",year);
                }//southamericacountries
                
                if(oc == 1)
                {
                    csvreader.readfile(basePath +"table-b4-au.csv",year);
                    csvreader.readfile(basePath +"table-b4-nz.csv",year);
                    csvreader.readfile(basePath +"table-b4-mh.csv",year); 
                }//oceaniacountries
                
                if(anta == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ws.csv",year);
                }//antarcticacountries
                 if(eu == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ad.csv",year);
                    csvreader.readfile(basePath +"table-b4-al.csv",year);
                    csvreader.readfile(basePath +"table-b4-at.csv",year);
                    csvreader.readfile(basePath +"table-b4-ba.csv",year);
                    csvreader.readfile(basePath +"table-b4-be.csv",year);
                    csvreader.readfile(basePath +"table-b4-bg.csv",year);
                    csvreader.readfile(basePath +"table-b4-ch.csv",year);
                    csvreader.readfile(basePath +"table-b4-cz.csv",year);
                    csvreader.readfile(basePath +"table-b4-de.csv",year);
                    csvreader.readfile(basePath +"table-b4-dk.csv",year);
                    csvreader.readfile(basePath +"table-b4-ee.csv",year);
                    csvreader.readfile(basePath +"table-b4-es.csv",year);
                    csvreader.readfile(basePath +"table-b4-fi.csv",year);
                    csvreader.readfile(basePath +"table-b4-fr.csv",year);
                    csvreader.readfile(basePath +"table-b4-gb.csv",year);
                    csvreader.readfile(basePath +"table-b4-gr.csv",year);
                    csvreader.readfile(basePath +"table-b4-hr.csv",year);
                    csvreader.readfile(basePath +"table-b4-hu.csv",year);
                    csvreader.readfile(basePath +"table-b4-ie.csv",year);
                    csvreader.readfile(basePath +"table-b4-is.csv",year);
                    csvreader.readfile(basePath +"table-b4-it.csv",year);
                    csvreader.readfile(basePath +"table-b4-lu.csv",year);
                    csvreader.readfile(basePath +"table-b4-lv.csv",year);
                    csvreader.readfile(basePath +"table-b4-mt.csv",year);
                    csvreader.readfile(basePath +"table-b4-nl.csv",year);
                    csvreader.readfile(basePath +"table-b4-no.csv",year);
                    csvreader.readfile(basePath +"table-b4-pl.csv",year);
                    csvreader.readfile(basePath +"table-b4-pt.csv",year);
                    csvreader.readfile(basePath +"table-b4-ro.csv",year);
                    csvreader.readfile(basePath +"table-b4-ru.csv",year);
                    csvreader.readfile(basePath +"table-b4-se.csv",year);
                    csvreader.readfile(basePath +"table-b4-sk.csv",year);
                    csvreader.readfile(basePath +"table-b4-ua.csv",year);
                    csvreader.readfile(basePath +"table-b4-by.csv",year);
                    csvreader.readfile(basePath +"table-b4-gg.csv",year);
                    csvreader.readfile(basePath +"table-b4-gi.csv",year);
                    csvreader.readfile(basePath +"table-b4-im.csv",year);
                    csvreader.readfile(basePath +"table-b4-li.csv",year);
                    csvreader.readfile(basePath +"table-b4-rs.csv",year);
                    csvreader.readfile(basePath +"table-b4-si.csv",year);
                    csvreader.readfile(basePath +"table-b4-tr.csv",year);
                }//eucountries
                
                if(asia == 1)
                {
                    csvreader.readfile(basePath +"table-b4-ae.csv",year);
                    csvreader.readfile(basePath +"table-b4-af.csv",year);
                    csvreader.readfile(basePath +"table-b4-am.csv",year);
                    csvreader.readfile(basePath +"table-b4-az.csv",year);
                    csvreader.readfile(basePath +"table-b4-bd.csv",year);
                    csvreader.readfile(basePath +"table-b4-bh.csv",year);
                    csvreader.readfile(basePath +"table-b4-bn.csv",year);
                    csvreader.readfile(basePath +"table-b4-bt.csv",year);
                    csvreader.readfile(basePath +"table-b4-cn.csv",year);
                    csvreader.readfile(basePath +"table-b4-cy.csv",year);
                    csvreader.readfile(basePath +"table-b4-hk.csv",year);
                    csvreader.readfile(basePath +"table-b4-id.csv",year);
                    csvreader.readfile(basePath +"table-b4-il.csv",year);
                    csvreader.readfile(basePath +"table-b4-in.csv",year);
                    csvreader.readfile(basePath +"table-b4-iq.csv",year);
                    csvreader.readfile(basePath +"table-b4-jo.csv",year);
                    csvreader.readfile(basePath +"table-b4-jp.csv",year);
                    csvreader.readfile(basePath +"table-b4-kh.csv",year);
                    csvreader.readfile(basePath +"table-b4-kr.csv",year);
                    csvreader.readfile(basePath +"table-b4-kw.csv",year);
                    csvreader.readfile(basePath +"table-b4-kz.csv",year);
                    csvreader.readfile(basePath +"table-b4-lb.csv",year);
                    csvreader.readfile(basePath +"table-b4-mn.csv",year);
                    csvreader.readfile(basePath +"table-b4-mo.csv",year);
                    csvreader.readfile(basePath +"table-b4-my.csv",year);
                    csvreader.readfile(basePath +"table-b4-om.csv",year);
                    csvreader.readfile(basePath +"table-b4-ph.csv",year);
                    csvreader.readfile(basePath +"table-b4-pk.csv",year);
                    csvreader.readfile(basePath +"table-b4-qa.csv",year);
                    csvreader.readfile(basePath +"table-b4-sa.csv",year);
                    csvreader.readfile(basePath +"table-b4-sg.csv",year);
                    csvreader.readfile(basePath +"table-b4-th.csv",year);
                    csvreader.readfile(basePath +"table-b4-tw.csv",year);
                    csvreader.readfile(basePath +"table-b4-uz.csv",year);
                    csvreader.readfile(basePath +"table-b4-lk.csv",year);
                    csvreader.readfile(basePath +"table-b4-vn.csv",year);
                }//asiacountries
                
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

        //System.out.println("!!N:"+ N);
        
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
        {
            calculateCS_d();
        }
        else
            calculateCS();
        
        try{
        if(graphtype == 1)
        {           
            //new WriteGEXF_d().praser(A,CS_out,CS_in,N,countrynames);
            new WriteCSVNode_d().praser(CS_out,CS_in,countrynames,N);
            new WriteCSVEdge_d().praser(A,N);
        }
        else
        {
            //new WriteGEXF().praser(A,CS,N,countrynames);
            new WriteCSVNode().praser(CS,countrynames,N);
            new WriteCSVEdge().praser(A,N);
        }
        
        } catch(Exception e){
            System.out.println(e.toString());
        }
        System.out.println("End of Cp_directed.run method.");
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
