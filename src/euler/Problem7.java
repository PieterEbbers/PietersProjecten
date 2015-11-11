package euler;

public class Problem7 
{
	static int primes = 0;
	static boolean isPrime;
	static int requestedPrime;
	static int number = 10001;
	
	public static void main(String[] args)
	{
			long startTime = System.currentTimeMillis();
			
			for(int i = 2; i < 1000000000; i++)
			{
				isPrime = true;
				for(int j = 2; j <= Math.sqrt(i);j++)
				{
					if(i % j == 0)
					{
						isPrime = false;
					}
				}
				
				if(isPrime)
				{
					primes++;
		
				}
				
				if(primes == number)
				{
					requestedPrime = i;
					break;
				}
			}
			
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + requestedPrime);
	}
}
