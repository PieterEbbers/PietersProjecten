package euler;

import java.util.ArrayList;
import java.util.List;

public class Problem12 {

	static int number = 0;
	static boolean isFound;
	static int adder = 1;
	final static int divisorsNeeded = 500;
	static List<Integer> divisors = new ArrayList<Integer>();

	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		
		while(!isFound)
		{
			number += adder;
			for(int i = 1; i <= Math.sqrt(number); i++)
			{
				if(number % i == 0)
				{
					divisors.add(i);
					if(i!=number)
					{
						divisors.add(number/i);
					}
				}
			}
			
			
			if(divisors.size() < divisorsNeeded)
			{
				divisors.clear();
			}
			
			if(divisors.size() >= divisorsNeeded)
			{
				isFound = true;
			}
			adder++;
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println("The answer is: " + number);
	}
}
