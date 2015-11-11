package euler;

public class Problem2 
{
	
	private static int firstNum = 0; 
	private static int secondNum = 1;
	 
	private static int a = firstNum;
	private static int b = secondNum;
	
	private static int c;
	private static int totalSum;
	private static int endNum = 4000000;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		while(a + b < endNum )
		{
			c = a + b;
			if(c % 2 == 0)
			{
				totalSum += c;
			}
			
			a = b;
			b = c;
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println(totalSum);
	}
}
