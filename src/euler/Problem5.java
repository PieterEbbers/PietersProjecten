package euler;

public class Problem5 
{

	static boolean isFound = false;
	static int number = 20;
	static boolean isPrime;
	static long x;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		while(!isFound)
		{
			number+=20;
			isFound = true;
			for(int i = 11; i <= 20; i++)
			{
				if(number % i != 0)
				{
					isFound = false;
					break;
				}
			}
		}

		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + number);
	}
}


