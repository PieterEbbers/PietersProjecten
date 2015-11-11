package euler;

public class Problem9 {

	static long answer;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		
		for(int a = 1; a < 1000; a++)
		{
			for(int b = a + 1; b < 1000; b++)
			{
				int cSquared = (a*a) + (b*b);
				
				if(a + b + Math.sqrt(cSquared) == 1000)
				{
					answer = (long) (a * b * Math.sqrt(cSquared));
				}
			}
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + answer);
	}
}
