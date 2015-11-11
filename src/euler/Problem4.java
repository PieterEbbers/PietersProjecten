package euler;

import java.util.ArrayList;
import java.util.List;

public class Problem4 {

	private static int number;
	private static int reversedNumber;
	
	private static List<Integer> palindromeNumbers = new ArrayList<Integer>();
	private static int largestPalindromeNumber;
	
	private static String numString;
	private static String reverseNumString;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		for(int i = 100; i < 1000; i++)
		{
			for(int j = 100; j < 1000; j++)
			{
				if(i % 10 == 0)
				{
					i++;
				}
				if(j % 10 == 0)
				{
					j++;
				}
				number = i * j;
				numString = Integer.toString(number);
				reverseNumString = new StringBuffer(numString).reverse().toString();
				reversedNumber = Integer.parseInt(reverseNumString);
				
				if(number == reversedNumber)
				{
					palindromeNumbers.add(number);
				}
			}
		}
		
		
		for(int i = 0 ;i < palindromeNumbers.size(); i++)
		{
			if(palindromeNumbers.get(i) > largestPalindromeNumber) 
			{
				largestPalindromeNumber = palindromeNumbers.get(i);
			}
		}
		
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
		System.out.println(largestPalindromeNumber);
	}
}
