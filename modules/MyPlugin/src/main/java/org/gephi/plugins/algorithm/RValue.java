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
public class RValue implements Comparable<RValue>
{
    private double a;
    private double b;
    private double R;

    public RValue(double a, double b, double R)
    {
        this.a = a;
        this.b = b;
        this.R = R;
    }//constructor

    public double getA()
    {
        return this.a;
    }//getA

    public double getB()
    {
        return this.b;
    }//getB

    public double getR()
    {
        return this.R;
    }//getR

    @Override
    public int compareTo(RValue r)
    {
        if( (r.getR() - this.R) > 0)
            return 1;
        else if( (r.getR() - this.R) < 0)
            return -1;
        else
            return 0;
    }//compareTo

    @Override
    public String toString()
    {
        return "R=" + this.R + " when a=" + this.a + " and b=" + this.b;
    }//toString
}//class