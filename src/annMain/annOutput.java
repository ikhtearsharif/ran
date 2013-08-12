/**c IS*/
package annMain;

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
	
	public double [] getElement()
	{
		double [] elementArray = new double [2];
		elementArray [0] = this.x;
		elementArray [1] = this.y;
		return elementArray;
	}
}
