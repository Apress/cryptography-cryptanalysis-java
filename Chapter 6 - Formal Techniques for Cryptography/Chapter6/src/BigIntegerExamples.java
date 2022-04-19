import java.math.BigInteger;

public class BigIntegerExamples {

	public static void main(String[] args) {
		BigInteger bi1, bi2;
		
		//initialization with a string value that will be converted to BigInteger
		bi1 = new BigInteger("5697726366552");
		System.out.println(bi1);
		
		//initialization with an int value
		bi2 = BigInteger.valueOf(87362);
		System.out.println(bi2);
		
		//the operations are implemented as methods within the class
		BigInteger sum = bi1.add(bi2);
		System.out.println("Sum: " + sum);
		BigInteger pow = bi1.pow(bi2.shortValue());
		System.out.println("Power: " + pow);
		
		//it provides complex operations such as modulo then power		
		BigInteger result = bi1.modPow(bi2, new BigInteger("5"));
		System.out.println("Mod inverse: " + result);	
		
		//comparison between two BigIntegers
		if(bi1.compareTo(bi2) < 0)
			System.out.println(bi1 + " less than " + bi2);
		else 
			System.out.println(bi1 + " greater than " + bi2);
		
	}
}
