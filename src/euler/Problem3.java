 package euler;

public class Problem3
{
	private static long x = 600851475143L; 
	private static boolean isPrime;
	
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		System.out.print("Ontbinding in priemfactoren van het getal " + x +  " geeft: ");
		for (long z = 2; z <= Math.sqrt(x); z++) {
			if(z % 2 == 0 && z != 2)
			{
				z++;
			}
			if(x % z == 0)
			{
				isPrime = true;
				for(long a = 2; a <= Math.sqrt(z); a++)
				{
					if(z%2 != 0 && a%2 == 0)
					{
						a++;
					}
					if(z%a == 0)
					{
						isPrime = false;
						break;
					}
				}
				
				if(isPrime)
				{
					x = x / z;
					
					System.out.print(z + " ");
					if(x != 1)
					{
						System.out.print("* ");
					}
					
			        z = 1;
				}
			}
		}
		
		System.out.println(x);
		double programRunTime = System.currentTimeMillis() - startTime;
		System.out.println("It took: " + programRunTime / 1000 + " seconds to find the answer.");

	}
}
