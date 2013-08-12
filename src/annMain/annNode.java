/**c IS*/
package annMain;

import java.util.ArrayList;
import java.util.Random;

public class annNode
{
	double selfValue;
	ArrayList<Double> weightArray = new ArrayList<Double>();
	ArrayList<Double> inArray = new ArrayList<Double>();
	double delta;
	double fsValue;
	
	public  annNode(int noOfinput, double [] inpVal)
	{
		
		Random rand = new Random();
		for (int i=0;i<noOfinput;i++)
		{
			weightArray.add(rand.nextDouble());
			
		}
		
		for (int i=0;i<inpVal.length;i++)
		{
			inArray.add(inpVal[i]);
		}
//		 for (int i=0;i<inpVal.length;i++)
//			 delta.add((double) 0);
		this.delta=0;
		this.fsValue=0;
		
	}
	
	public void calculateSelfValue()
	{
		double sum= 0;
		
		for(int i=0;i<this.inArray.size();i++)
		{
			sum+=this.inArray.get(i).doubleValue()*(this.weightArray.get(i).doubleValue());
		}
		
		this.selfValue= 1/(1+(Math.exp(-1*sum)));
				
	}
	
	public void calculateNewWeight()
	{
		double row=1.2;
		for (int i=0;i<this.weightArray.size();i++)
		{
			
			this.weightArray.set(i, (this.weightArray.get(i).doubleValue()+row*this.delta*this.selfValue));
		}
	}
	
	public double sumWeight()
	{
		double sum= 0;
		
		for(int i=0;i<this.weightArray.size();i++)
		{
			sum+=this.weightArray.get(i).doubleValue();
		}
		
		return sum;
				
	}
	
	public void calculateFsValue()
	{
		
		this.calculateSelfValue();
		this.fsValue=this.selfValue*(1-this.selfValue);
	}
	
	public double inpOfNode()
	{
		double sum= 0;
		
		for(int i=0;i<this.inArray.size();i++)
		{
			sum+=this.inArray.get(i).doubleValue();
		}
		
		return sum;
				
	}
	
	public void populateInArray(double [] inp)
	{
		for (int i=0;i<inp.length;i++)
		{
			this.inArray.set(i, inp[i]);
		}
	}
	
	
	
//	public annNode(float a, float b)
//	{
//		this.selfValue=a;
//		
//	}
//	
	public double getSelfValue()
	{
		
		return this.selfValue;
	}
//	
//	public float getselfWeight()
//	{
//		return this.selfWeight;
//	}
//
//	public void setSelfValue(float a)
//	{
//				this.selfValue=a;
//	}
//	
//	public void setSelfWeight(float b)
//	{
//				this.selfWeight=b;
//	}
}
