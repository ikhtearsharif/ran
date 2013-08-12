/**c IS*/
package annMain;

//import java.util.Random;

public class annInput
{
	
	
	double a,b,c;
//	double [] weightA = new double [3];
//	double [] weightB = new double [3];
//	double [] weightC = new double [3];
//	
	public annInput(double p,double q,double r)
	{
		this.a=p;
		this.b=q;
		this.c=r;
		
//		Random rand = new Random();
//		
//		for (int i=0;i<3;i++)
//		{
//			
//			weightA[i]= rand.nextDouble();
//			weightB[i]= rand.nextDouble();
//			weightC[i]= rand.nextDouble();
//			
//		}
		
	}
	
	public double sumValues()
	{
		
		return this.a+this.b+this.c;
	}
	
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
