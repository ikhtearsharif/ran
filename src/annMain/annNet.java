/**c IS*/

package annMain;

import java.util.ArrayList;

/*
 * Represents the network 
 * */
public class annNet
{

	
	ArrayList<annNode> hiddenNode= new ArrayList<annNode>(); // hidden layer node, currently support 1 layer
	ArrayList<annNode> outputLayer = new ArrayList<annNode>(); // output layer node
	
	int noOfHiddenLayerNode; // no of node in hidden layer
	int noOfOutputLayerNode; // no of node in output layer
	double selfError;
	double MSE;

	
	public annNet(int nHiddenLayerNode, int nOutputLayerNode)
	{
		this.noOfHiddenLayerNode=nHiddenLayerNode;
		this.noOfOutputLayerNode=nOutputLayerNode;
		this.selfError=0;
		this.MSE=0;

	}
	
	
	/*
	 * first sample run 
	 * */
	public void inputOfNetwork(annInput a)
	{
			for (int i=0;i<this.noOfHiddenLayerNode;i++)
				this.hiddenNode.add(new annNode(a.getContent().length, a.getContent()));
			
			for (int i=0;i<this.hiddenNode.size(); i++)
				this.hiddenNode.get(i).calculateSelfValue();
			
			for (int i=0;i<this.noOfOutputLayerNode;i++)
				this.outputLayer.add(new annNode(this.hiddenNode.size(),this.netGetContent(hiddenNode)));
			
			for (int i=0;i<this.outputLayer.size();i++)
				this.outputLayer.get(i).calculateSelfValue();
	}
	
	/*
	 * Calculate delta for output layer nodes
	 * */
	public void calculateDeltaOutpt( annOutput op)
	{
		double [] opDesired = op.getElement(); 
		for (int i=0;i<outputLayer.size();i++)
		{
			this.outputLayer.get(i).delta=this.outputLayer.get(i).selfValue*(1-this.outputLayer.get(i).selfValue)*
					(opDesired[i]-this.outputLayer.get(i).selfValue);
		}
	}
	
	/*
	 * Calculate delta for hidden layer nodes
	 * */
	public void calculateDeltaHidden()
	{
		double sumDelOut=0;
		for (int i=0;i<this.hiddenNode.size();i++)
		{
			for (int j=0;j<this.outputLayer.size();j++)
			{
				sumDelOut+= this.outputLayer.get(j).delta*this.outputLayer.get(j).weightArray.get(i).doubleValue();
			}
			this.hiddenNode.get(i).delta=this.hiddenNode.get(i).selfValue*(1-this.hiddenNode.get(i).selfValue)*sumDelOut; 
			sumDelOut=0;
		}
	}
	
	/*
	 * Caluculate hj
	 * */
	public double calculateHj()
	{
		double hj=0;
		for (int i=0;i<this.hiddenNode.size();i++)
		{
			hj+=this.hiddenNode.get(i).getSelfValue();
		}
		return hj;
	}
	
	
	/*
	 * Check des value with threshold
	 * */
	public boolean checkDES(double [] des, double threshold)
	{
		boolean notLess=false;
		for (int i=0;i<des.length;i++)
		{
			if (des[i]>threshold)
				notLess=true;
		}
		return notLess;
	}
	
	
	/*
	 * return the delta value into an array
	 * */
	public double [] geDeltaArray()
	{
		double [] deltaValueArray = new double [this.outputLayer.size()];
		for (int i=0;i<this.outputLayer.size();i++)
		{
			deltaValueArray[i]=this.outputLayer.get(i).delta;
		}
		
		return deltaValueArray;
	}
	
	/*
	 * check delta values with threshold
	 * */
	public boolean checDelta( double threshold)
	{
		double [] deltaValueArray= this.geDeltaArray();
		boolean notLess=false;
		for (int i=0;i<deltaValueArray.length;i++)
		{
			if (Math.abs(deltaValueArray[i])>threshold)
				notLess=true;
		}
		return notLess;
	}
	
	
	/*
	 * run network with different elements
	 * */
	public void runNetwork()
	{
		
		
		for (int i=0;i<this.hiddenNode.size(); i++)
			this.hiddenNode.get(i).calculateSelfValue();
		
		for (int i=0;i<this.outputLayer.size();i++)
			this.outputLayer.get(i).calculateSelfValue();
		
		for (int i=0;i<this.hiddenNode.size();i++)
		{
			this.hiddenNode.get(i).calculateNewWeight();
		}
		for (int i=0;i<this.outputLayer.size();i++)
		{
			this.outputLayer.get(i).calculateNewWeight();
		}
	}
	
	
	/*
	 * calculate error for the any sample run
	 * */
	public void calculateError(annOutput op)
	{
		double [] opDesired = op.getElement(); 
		for (int i=0;i<this.outputLayer.size();i++)
		{	
			this.selfError+=Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue());
		}
	}
	
	/*
	 * calculate mean square error for the any  sample run
	 * */
	public void calculateMSE(annOutput op)
	{
		double [] opDesired = op.getElement(); 
		double temp=0;
		for (int i=0;i<this.outputLayer.size();i++)
		{
			temp+=Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue())*Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue());
		}
		this.MSE+=temp/2;
	}
	
	
	/*
	 * return error value
	 * */
	public double getErrorValue()
	{
		return this.selfError;
	}
	
	
	/*
	 * return Mean square values
	 * */
	public double getMSEValue()
	{
		return this.MSE;
	}
	 
	
	/*
	 * This function will print the staus of the network including values, input, associated weight of all the hidden, output and input layers 
	 * */
	public void printElement()
	 {
		 System.out.println("NETWORK SELF ERROR::  "+this.selfError+"   MSE::: "+this.MSE);
		 System.out.println("*****************Hidden layer elements ***************");
		 for (int i=0;i<this.hiddenNode.size();i++)
		 {
			 System.out.println("self value:: "+ this.hiddenNode.get(i).selfValue+"  DELTA VALUES:::"+this.hiddenNode.get(i).delta	 );
			 for (int j=0;j<this.noOfHiddenLayerNode;j++)
			 {
				 System.out.println("Weight::"+ this.hiddenNode.get(i).weightArray.get(j).doubleValue()+
						 " INPUT::"+this.hiddenNode.get(i).inArray.get(j).doubleValue() );
			 }
		 }
		 System.out.println("*****************Output layer elements ***************");
		 for (int i=0;i<this.outputLayer.size();i++)
		 {
			 System.out.println("self value:: "+i+"th node  "+ this.outputLayer.get(i).selfValue+"  DELTA VALUES:::"+this.outputLayer.get(i).delta	 );
			 for (int j=0;j<this.outputLayer.get(i).inArray.size();j++)
			 {
				 System.out.println("Weight::"+ this.outputLayer.get(i).weightArray.get(j).doubleValue()+
						 " INPUT::"+this.outputLayer.get(i).inArray.get(j).doubleValue() );
			 }
		 }
	 }
	
	
	/*
	 * This function take a sample value and target out and run in hidden and output layer 
	 * calculate all the new value of hidden and output node 
	 * calculate new weight 
	 * calculate error and mean square error
	 * */
	public void newRun(annInput inp, annOutput op)
	{
		
		for (int i=0 ;i<this.noOfHiddenLayerNode;i++)
			this.hiddenNode.get(i).populateInArray(inp.getContent());
		
				
		for (int i=0;i<this.hiddenNode.size(); i++)
			this.hiddenNode.get(i).calculateSelfValue();
		
		
		for (int i=0;i<this.noOfOutputLayerNode;i++)
			this.outputLayer.get(i).populateInArray(this.netGetContent(hiddenNode));
			
			
		for (int i=0;i<this.outputLayer.size();i++)
			this.outputLayer.get(i).calculateSelfValue();
		
		this.calculateDeltaOutpt( op);
		this.calculateDeltaHidden();
		this.calculateError(op);
		this.calculateMSE(op);
		this.printElement();
		
		for (int i=0;i<this.hiddenNode.size();i++)
				this.hiddenNode.get(i).calculateNewWeight();
	
		for (int i=0;i<this.outputLayer.size();i++)
				this.outputLayer.get(i).calculateNewWeight();
	
		
	}
	
	/*
	 * return an array containg value for any layer such as hidden or output layer
	 * */
	public double [] netGetContent (ArrayList<annNode> arrAnn)
	{
		double [] layerValue = new double [arrAnn.size()];
		for (int i=0;i<arrAnn.size();i++)
			layerValue[i]= arrAnn.get(i).getSelfValue();
		return layerValue;
	}
	
}
