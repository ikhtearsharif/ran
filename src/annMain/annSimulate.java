
package annMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Scanner;
//import java.lang.*;

public class annSimulate
{

	/**
	 * address of 25 input file need to be given to run the program
	 */
	public static void main(String[] args)
	{
		
		String [] filename = {"C:\\ann_data\\point1.txt",
				"C:\\ann_data\\point2.txt",
				};
		
		
		
		ArrayList<annInput> inp = new ArrayList<annInput>();
		ArrayList<annOutput> op = new ArrayList<annOutput>();
		
		
		// store all the sample from the file into an ArrayList
		for (int i=0;i<filename.length;i++)
		{
			try {
				Scanner s = new Scanner (new File(filename[i]));
				String line = null;
				while (s.hasNextLine())
				{
					line = s.nextLine();
					String [] cols = line.split("\\t");
				
					inp.add(new annInput(Double.parseDouble(cols[0]),Double.parseDouble(cols[1]),Double.parseDouble(cols[2])));

				}
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		//store desired output values into an ArrayList
		for (int i=0;i<5;i++)
			for (int j=0;j<5 ;j++)
				for (int k=0;k<500;k++)
				{
					op.add(new annOutput(i,j));
				}
		

		
		annNet network = new annNet(3,2); // now support 3 hidden layer node and 2 output layer node
	
		
		double threshold=.1; // this is the threshold value  to stop learning
		double checkErrorNewEpoch=1; // this variable will store the average mean square error after every epoch
		
		// initialize network
		/*
		 * here the first sample will pass through with a random weight into the hidden layer of the network
		 * afterward value will pass through to outpur layer
		 * calculate delta value for the output layer and hiddden layer
		 * calculate error and mean squared error
		 * */
		
		
		network.inputOfNetwork(inp.get(0)); // pass the first sample into network(hidden layer) which will go to output layer
		network.calculateDeltaOutpt( op.get(0));// calculate delta for output layer 
		network.calculateDeltaHidden(); // calculate delta for hidden layer
		network.calculateError(op.get(0));// calculate error for the first sample
		network.calculateMSE(op.get(0));// calculate mean squared error for the first sample
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+1);
		network.printElement(); // this will print the current network staus including the value an weight of all hidden and output layer nodes
		/// verified till initialization
		
		

		// update weight, following 2 loop will caluculate and update the new weight for all output layer and hidden layer
		for (int i=0;i<network.outputLayer.size();i++)
			network.outputLayer.get(i).calculateNewWeight();
		for (int i=0;i<network.hiddenNode.size();i++)
			network.hiddenNode.get(i).calculateNewWeight();
		//verified
		
		//network.printElement();
		
		
		// if you need to check with only sample value , run the for loop only  and put the desired value in loop condition
		for (int i=1;i<10;i++)
			{
			        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+(i+1));
					network.newRun(inp.get(i), op.get(i));
					//System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+(i+1));
					//network.printElement();
			}
		
		
		//network.printElement();
		
		// run learning till getting new error value less than threshold
		
		// uncomment the while loop if you like to check the values in epoch, put the condition in cnt
		// uncomment while loop "while(checkErrorNewEpoch>threshold)" if you like to check with threshold value
		int cnt=1;
		//while (checkErrorNewEpoch>threshold) 
//		while (cnt<=2000)
//		{
//				for (int i=1;i<inp.size();i++)
//				{
//						network.newRun(inp.get(i), op.get(i));
//				}
//				checkErrorNewEpoch=network.getMSEValue()/inp.size();
//				//network.printElement();
//				System.out.println("Epoch No:: "+cnt+"     Average Mean Square::: "+checkErrorNewEpoch);
//				network.selfError=0;
//				network.MSE=0;
//			//	System.out.println("##############################################################################");
//				//checkErrorNewEpoch=1;
//				cnt++;
//		}
//		
		

	}

}
