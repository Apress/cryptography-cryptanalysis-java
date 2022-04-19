import java.util.Scanner;

public class BlumInteger {
	public static boolean checkBlumInteger(int value)
    {
		//define an array with elements from 0 to value-1
		//suppose each number in this interval is prime
        int primesArray[] = new int[value + 1];
        for (int i = 0; i < value; i++)
        	primesArray[i] = 1;
 
        //update the array with the primality of number within in the interval
        for (int i = 2; i * i <= value; i++) {
             if (primesArray[i] == 1) {
            	 //if a value is prime, then certainly its multiples are no primes
            	 //update the primality accordingly
                for (int j = i * 2; j <= value; j += i)
                	primesArray[j] = 0;
            }
        }
 
        //check whether the value is a product of two prime numbers
        //on the same time check whether the value has a form of 4x+3
        for (int aux = 2; aux <= value; aux++) {
            if (primesArray[aux] == 1) {                
                if ((value % aux == 0) && ((aux - 3) % 4) == 0) {
                    int x = value / aux;
                    return (x != aux && primesArray[x] == 1  && (x - 3) % 4 == 0);
                }
            }
        }
        return false;
    }
	
    public static void main(String[] args)
    {        
        int vals[] = {177, 125};
        System.out.println("Checking Blum integer...");
        for(int i=0;i<vals.length;i++)
        {
        if (checkBlumInteger(vals[i]) == true)
            System.out.println(vals[i] + " - True");
        else
            System.out.println(vals[i] + " - False");	  
        }
    }
}
