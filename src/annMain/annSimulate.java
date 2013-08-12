/**c IS*/
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

		//population desired output values
		for (int i=0;i<5;i++)
			for (int j=0;j<5 ;j++)
				for (int k=0;k<500;k++)
				{
					op.add(new annOutput(i,j));
				}
		
//		System.out.println(inp.get(0).getA()+".."+inp.get(0).getB()+".."+inp.get(0).getC());
//		System.out.println(op.get(0).getX()+".."+op.get(0).getY());
		
		annNet network = new annNet(3,2); // now support 3 hidden layer node and 2 output layer node
	//	double threshold=.1;
		double checkErrorNewEpoch=1;
		
		// initialize network
		network.inputOfNetwork(inp.get(0));
		network.calculateDeltaOutpt( op.get(0));
		network.calculateDeltaHidden();
		network.calculateError(op.get(0));
		network.calculateMSE(op.get(0));
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+1);
		network.printElement();
		/// verified till initialization
		
		
		//System.out.println("after weight update");
		//System.out.println("---------------------------------");
		// update weight
		for (int i=0;i<network.outputLayer.size();i++)
			network.outputLayer.get(i).calculateNewWeight();
		for (int i=0;i<network.hiddenNode.size();i++)
			network.hiddenNode.get(i).calculateNewWeight();
		//verified
		
		//network.printElement();
		
		for (int i=1;i<10;i++)
			{
			        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+(i+1));
					network.newRun(inp.get(i), op.get(i));
					//System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Sample No :::: "+(i+1));
					//network.printElement();
			}
		
		
		//network.printElement();
		
		// run learning till getting new error value less than threshold
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
		
		//System.out.println(op.size());
		
//		for (int i=0;i<inp.size();i++)
//		{
//			System.out.println(inp.get(i).getA()+".."+inp.get(i).getB()+".."+inp.get(i).getC());
//		}
//		
//		for (int i=0;i<op.size();i++)
//		{
//			System.out.println(op.get(i).getX()+".."+op.get(i).getY());
//		}
	}

}
