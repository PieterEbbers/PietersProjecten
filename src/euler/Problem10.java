package euler;

public class Problem10 {

	static boolean isPrime;
	static long answer = 2;
	static int numbersToCheck = 2000000;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		
		for(int i = 3; i <= numbersToCheck; i+=2)
		{
			isPrime = true;
			for(int j = 3; j <= Math.sqrt(i); j+=2)
			{
				if(i % j == 0)
				{
					isPrime = false;
					break;
				}
			}
			if(isPrime)
			{
				answer += i;
			}
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + answer);
	}
}
