/**c IS*/
package annMain;

import java.util.ArrayList;
import java.util.Random;

/*
 * This class represents the node of the network, including hidden layer and output layer
 * */
public class annNode
{
	double selfValue; // value of the node itself
	ArrayList<Double> weightArray = new ArrayList<Double>(); // weight associated with input values
	ArrayList<Double> inArray = new ArrayList<Double>(); // input values to the nodes
	double delta; // delta value of the node
	double fsValue;
	
	/*
	 * Create node with input values and their associate weight 
	 * for the first time delta value will be zero 
	 * */
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

		this.delta=0;
		this.fsValue=0;
		
	}
	
	/*
	 * Calculate the value of the node using the formula 1/(1+e^(-s)) where s=sum(weight*input value) 
	 * */
	public void calculateSelfValue()
	{
		double sum= 0;
		
		for(int i=0;i<this.inArray.size();i++)
		{
			sum+=this.inArray.get(i).doubleValue()*(this.weightArray.get(i).doubleValue());
		}
		
		this.selfValue= 1/(1+(Math.exp(-1*sum)));
				
	}
	
	/*
	 * Calculate new weight using formula new_weight=weight+row*delta*own value where row in constant 1.2
	 * */
	public void calculateNewWeight()
	{
		double row=1.2;
		for (int i=0;i<this.weightArray.size();i++)
		{
			
			this.weightArray.set(i, (this.weightArray.get(i).doubleValue()+row*this.delta*this.selfValue));
		}
	}
	
	/*
	 * Summation of the weight associated with input
	 * */
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
	
	/*
	 * summation of the input values
	 * */
	public double inpOfNode()
	{
		double sum= 0;
		
		for(int i=0;i<this.inArray.size();i++)
		{
			sum+=this.inArray.get(i).doubleValue();
		}
		
		return sum;
	}
	
	
	/*
	 * populate new input values into the node
	 * */
	public void populateInArray(double [] inp)
	{
		for (int i=0;i<inp.length;i++)
		{
			this.inArray.set(i, inp[i]);
		}
	}
	
	/*
	 * return the value of the node
	 * */
	public double getSelfValue()
	{
		
		return this.selfValue;
	}

}
