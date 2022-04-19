package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;


public class RSA {
	
	private BigInteger prime_p, prime_q, val_n, phi_n, val_e, val_d;
	private BigInteger one = BigInteger.ONE;
	private int maximumLength = 1024;
	private SecureRandom random;
	
	public void KeyGeneration() {
		random = new SecureRandom();
		prime_p = BigInteger.probablePrime(maximumLength, random);
		prime_q = BigInteger.probablePrime(maximumLength, random);
		val_n = prime_p.multiply(prime_q);
		phi_n = prime_p.subtract(one).multiply(prime_q.subtract(one));
		val_e = BigInteger.probablePrime(maximumLength, random);
		do {
			val_e = BigInteger.probablePrime(maximumLength, random);
		}
		while (phi_n.gcd(val_e).compareTo(one) < 0 && val_e.compareTo(phi_n) > 0);
		val_d = val_e.modInverse(phi_n);		
	} 
	
	
    public byte[] Encryption(byte[] plainMessage, BigInteger e, BigInteger n)
    {
    	BigInteger encryptedMessage = (new BigInteger(plainMessage)).modPow(e, n);
        return encryptedMessage.toByteArray();
    } 
    
    public byte[] Decryption(byte[] encryptedMessage, BigInteger d, BigInteger n)
    {
    	BigInteger decryptedMessage = (new BigInteger(encryptedMessage)).modPow(d, n);
        return decryptedMessage.toByteArray();
    }   
 
    public static void main (String [] arguments) throws IOException
    {
        RSA rsa = new RSA();
        rsa.KeyGeneration();        
        
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        String plainMessage;
        System.out.print("Type the plain message: ");
        plainMessage = d.readLine();
        
        System.out.println("\nEncrypting the message... ");
        byte[] encryptedMessage = rsa.Encryption(plainMessage.getBytes(), rsa.val_e, rsa.val_n);
        System.out.println("Encrypted message [bytes]: " + byteToString(encryptedMessage));
        System.out.println("Encrypted message [text]: " + new String(encryptedMessage));
        
        System.out.println("\nDecrypting the message... ");
        byte[] decryptedMessage = rsa.Decryption(encryptedMessage, rsa.val_d, rsa.val_n);
        System.out.println("Decrypted message [bytes]: " + byteToString(decryptedMessage));
        System.out.println("Decrypted message [text]: " + new String(decryptedMessage)); 
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
