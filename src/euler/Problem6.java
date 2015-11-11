package euler;

public class Problem6 
{
	
	static int sumOfNumbers = 0;
	static int sumOfSquaredNumbers = 0;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		
		for(int i = 1; i <= 100; i++)
		{
			sumOfNumbers += i;
			sumOfSquaredNumbers += i*i;
		}
		int sumOfNumbersSquared = sumOfNumbers * sumOfNumbers;
		int difference = sumOfNumbersSquared - sumOfSquaredNumbers;
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + difference);
	}
}
