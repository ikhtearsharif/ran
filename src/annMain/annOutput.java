
package annMain;

/*
 * This class hold the target output values 
 * */
public class annOutput
{
	
	double x;
	double y;
	
	public annOutput( double a, double b)
	{
		this.x=a;
		this.y=b;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}

	public void setX(double a)
	{
				this.x=a;
	}
	
	public void setY(double b)
	{
				this.y=b;
	}
	
	/*
	 * This function will return an array containing the values of the target output
	 * */
	public double [] getElement()
	{
		double [] elementArray = new double [2];
		elementArray [0] = this.x;
		elementArray [1] = this.y;
		return elementArray;
	}
}
