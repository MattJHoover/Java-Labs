/**
 * @author: Matthew Hoover
 * @name: PrimeSieve.java
 * @date: 02.04.14
 */
import java.util.Scanner;
import java.util.ArrayList;
public class PrimeSieve
{

	private static Scanner sc;
	/**
	 * The main method
	 * @param args Command line arguments are unused
	 */
	public static void main(String[] args)
	{
		sc = new Scanner(System.in);
		System.out.println("Sieve of Eratosthenes Program\nEnter maximum N value: ");
		int maxn = sc.nextInt();
		System.out.println("Primes less than or equal to " + maxn + ":\n" + findPrimes(maxn));

	}
	/**
	 * Find and return the prime numbers between 2 and N inclusive.
	 * @param N the maximum value (inclusive to check)
	 * @return primelist a list of the prime numbers
	 */
	public static ArrayList<Integer> findPrimes(int N)
	{
		ArrayList<Integer> intlist = new ArrayList<Integer>();
		ArrayList<Integer> primelist = new ArrayList<Integer>();
		int p = 2;
		for (int i = 2; i <= N; i++)
		{
			intlist.add(i);
		}

		while (intlist.size() > 0)
		{
			p = intlist.get(0);
			primelist.add(p);
			for (int i = 1; i < N; i++)
			{
				intlist.remove(new Integer(p*i));

			}
		}
		return primelist;
	}

}