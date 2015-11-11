package euler;

public class Problem1
{
	
	public static int totalSum = 0;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		for(int i = 3; i < 1000; i += 3)
		{
			if(i % 5 != 0)
			{
				totalSum += i;
			}
		}
		for(int i = 5; i < 1000; i += 5)
		{
			totalSum += i;
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println(totalSum);
	}
}
