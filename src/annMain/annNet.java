/**c IS*/

package annMain;

import java.util.ArrayList;

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
	
	public void calculateDeltaOutpt( annOutput op)
	{
		double [] opDesired = op.getElement(); 
		for (int i=0;i<outputLayer.size();i++)
		{
			//outputLayer.get(i).calculateFsValue();
			//this.outputLayer.get(i).delta= this.outputLayer()*(opDesired[i]- outputLayer.get(i).getSelfValue());
			this.outputLayer.get(i).delta=this.outputLayer.get(i).selfValue*(1-this.outputLayer.get(i).selfValue)*
					(opDesired[i]-this.outputLayer.get(i).selfValue);
		}
	}
	
	public void calculateDeltaHidden()
	{
		double sumDelOut=0;
		for (int i=0;i<this.hiddenNode.size();i++)
		{
			
			//this.hiddenNode.get(i).calculateSelfValue();
			for (int j=0;j<this.outputLayer.size();j++)
			{
				sumDelOut+= this.outputLayer.get(j).delta*this.outputLayer.get(j).weightArray.get(i).doubleValue();
			}
			this.hiddenNode.get(i).delta=this.hiddenNode.get(i).selfValue*(1-this.hiddenNode.get(i).selfValue)*sumDelOut; 
			sumDelOut=0;
		}
	}
	
	public double calculateHj()
	{
		double hj=0;
		for (int i=0;i<this.hiddenNode.size();i++)
		{
			hj+=this.hiddenNode.get(i).getSelfValue();
		}
		return hj;
	}
	
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
	
	public double [] geDeltaArray()
	{
		double [] deltaValueArray = new double [this.outputLayer.size()];
		for (int i=0;i<this.outputLayer.size();i++)
		{
			deltaValueArray[i]=this.outputLayer.get(i).delta;
		}
		
		return deltaValueArray;
	}
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
	
	public void calculateError(annOutput op)
	{
		double [] opDesired = op.getElement(); 
		for (int i=0;i<this.outputLayer.size();i++)
		{
			//outputLayer.get(i).calculateFsValue();
			this.selfError+=Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue());
		}
	}
	
	public void calculateMSE(annOutput op)
	{
		double [] opDesired = op.getElement(); 
		double temp=0;
		for (int i=0;i<this.outputLayer.size();i++)
		{
			//outputLayer.get(i).calculateFsValue();
			temp+=Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue())*Math.abs(opDesired[i]- this.outputLayer.get(i).getSelfValue());
		}
		this.MSE+=temp/2;
	}
	
	public double getErrorValue()
	{
		return this.selfError;
	}
	
	public double getMSEValue()
	{
		return this.MSE;
	}
	 
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
	public void learning(annInput inp, annOutput op)
	{
		//double delta=0;
		
//		this.inputOfNetwork(inp);
//		this.calculateDeltaOutpt( op);
//		this.calculateDeltaHidden();
		//double threshold=.000005;
		
//		while (checDelta(threshold))
//		{
//			System.out.println("OUTPUT LAYER");
//			for (int i=0;i<outputLayer.size();i++)
//			{
//				System.out.println(outputLayer.get(i).selfValue);
//				for (int j=0;j<outputLayer.get(i).inArray.size();j++)
//					System.out.println("INOUT VALUE::::"+outputLayer.get(i).inArray.get(j).doubleValue());
//				for (int j=0;j<outputLayer.get(i).weightArray.size();j++)
//					System.out.println("Weight  ::::"+outputLayer.get(i).weightArray.get(j).doubleValue());
//				System.out.println("delta:  "+outputLayer.get(i).delta);
//				System.out.println("fs:  "+outputLayer.get(i).fsValue);
//			}
//			this.runNetwork();
//			this.calculateDeltaOutpt( op);
//			this.calculateDeltaHidden();
//		}
		
		
		
		
		////printing steps
		
		System.out.println("input::: "+inp.getA()+".."+inp.getB()+".."+inp.getC());
		
		System.out.println("HIDDENT LAYER");
		
		for (int i=0;i<hiddenNode.size();i++)
		{
			System.out.println(hiddenNode.get(i).selfValue);
			for (int j=0;j<hiddenNode.get(i).inArray.size();j++)
				System.out.println("INOUT VALUE::::"+hiddenNode.get(i).inArray.get(j).doubleValue());
			for (int j=0;j<hiddenNode.get(i).weightArray.size();j++)
				System.out.println("Weight  ::::"+hiddenNode.get(i).weightArray.get(j).doubleValue());
		}
		
		
		System.out.println("OUTPUT LAYER");
		for (int i=0;i<outputLayer.size();i++)
		{
			System.out.println(outputLayer.get(i).selfValue);
			for (int j=0;j<outputLayer.get(i).inArray.size();j++)
				System.out.println("INOUT VALUE::::"+outputLayer.get(i).inArray.get(j).doubleValue());
			for (int j=0;j<outputLayer.get(i).weightArray.size();j++)
				System.out.println("Weight  ::::"+outputLayer.get(i).weightArray.get(j).doubleValue());
			System.out.println("delta"+outputLayer.get(i).delta);
			System.out.println("fs"+outputLayer.get(i).fsValue);
		}
		
		System.out.println("########################################################################################");
		
//		hiddenNode.clear();
//		outputLayer.clear();
	
	}
	public double [] netGetContent (ArrayList<annNode> arrAnn)
	{
		double [] layerValue = new double [arrAnn.size()];
		for (int i=0;i<arrAnn.size();i++)
			layerValue[i]= arrAnn.get(i).getSelfValue();
		return layerValue;
	}
	
	
	
}
