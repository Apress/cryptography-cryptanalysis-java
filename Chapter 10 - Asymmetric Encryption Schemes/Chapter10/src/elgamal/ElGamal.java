package elgamal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {
	
	private BigInteger prime_q, val_x, val_g, val_h;
	private byte[] c1, c2;
	private BigInteger one = BigInteger.ONE;
	private BigInteger two = new BigInteger("2");
	private int maximumLength = 1024;
	private SecureRandom random;
	
	public void KeyGeneration() {
		random = new SecureRandom();
		
		prime_q = BigInteger.probablePrime(maximumLength, random);		
		do {
			val_x = BigInteger.probablePrime(maximumLength, random);
		}
		while (val_x.compareTo(prime_q.subtract(one)) >= 0);		
		
		BigInteger p2 = prime_q.subtract(one);
	    p2 = p2.divide(two);
	    
	    // take a generator g of the group
	    val_g = new BigInteger(maximumLength, random);
	    val_g = val_g.mod(prime_q);
	    while(val_g.modPow(p2,prime_q).compareTo(prime_q.subtract(one)) != 0)
	    {
	    	val_g = new BigInteger(maximumLength, random);
	    	val_g = val_g.mod(prime_q);
	    }
		
	    val_h = val_g.modPow(val_x, prime_q);		
	} 
	
	
    public void Encryption(byte[] plainMessage)
    {
    	BigInteger y, s;
    	do {
			y = new BigInteger(maximumLength, random);
		}
		while (y.compareTo(val_h) >= 0);
    	
    	s = val_h.modPow(y, prime_q);
    	c1 = (val_g.modPow(y, prime_q)).toByteArray();
    	c2 = (new BigInteger(plainMessage).multiply(s)).toByteArray();
    	
    	System.out.println("Encrypted message [bytes]: " + byteToString(c1) + byteToString(c2));
        System.out.println("Encrypted message [text]: " + new String(c1) + new String(c2));   	
    	
    } 
    
    public void Decryption()
    {
    	BigInteger s = new BigInteger(c1).modPow(val_x, prime_q);
    	BigInteger invS = s.modInverse(prime_q);
    	BigInteger m = invS.multiply(new BigInteger(c2)).mod(prime_q);
    	
    	System.out.println("\nDecrypted message [bytes]: " + byteToString(m.toByteArray()) + byteToString(m.toByteArray()));
        System.out.println("Decrypted message [text]: " + new String(m.toByteArray()));      	       
    }   
 

	public static void main(String[] args) throws IOException {
		ElGamal elGamal = new ElGamal();
		elGamal.KeyGeneration();        
        
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        String plainMessage;
        System.out.print("Type the plain message: ");
        plainMessage = d.readLine();
        
        System.out.println("\nEncrypting the message... ");
        elGamal.Encryption(plainMessage.getBytes());
        elGamal.Decryption();       
	}
	
	private static String byteToString(byte[] byteArray)
    {
        String recoveredStrig = "";
        for (byte byteVal : byteArray)
        {
        	recoveredStrig += Byte.toString(byteVal);
        }
        return recoveredStrig;
    } 
}
