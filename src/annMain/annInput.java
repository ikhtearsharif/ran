
package annMain;

//import java.util.Random;


/*
 * This class represents any input sample
 * */
public class annInput
{
	
	
	double a,b,c;

	public annInput(double p,double q,double r)
	{
		this.a=p;
		this.b=q;
		this.c=r;		
	}
	
	public double sumValues()
	{
		
		return this.a+this.b+this.c;
	}
	
	
	/*
	 * return an array containing all 3 values of any sample
	 * */
	public double [] getContent()
	{
		double [] gc = new double [3];
		gc[0]= this.a;
		gc[1]= this.b;
		gc[2]= this.c;
		
		return gc;
	}
	
	public double getA()
	{
		return this.a;
	}
	
	public double getB()
	{
		return this.b;
	}
	
	public double getC()
	{
		return this.c;
	}
	
	public void setA( double p)
	{
		this.a=p;
	}
	
	public void setB( double q)
	{
		this.b=q;
	}
	
	public void setC( double r)
	{
		this.c=r;
	}
}
