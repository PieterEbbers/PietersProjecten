package euler;

public class Problem14 {
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		long number;
		long terms = 0;
		
		for(long startNum = 2; startNum < 1000000; startNum++)
		{
			number = startNum;
			while(number != 1)
			{
				terms++;
				if(number % 2 == 0)
				{
					number = number / 2;	
				} 
				else
				{
					number = (number * 3) + 1;
				}
			}
		}

		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");
		
	}
}
