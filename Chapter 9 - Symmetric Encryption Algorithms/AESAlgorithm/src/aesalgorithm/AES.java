package aesalgorithm;

import java.util.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.*;  


public class AES 
{
	private static byte[] initialization_vector = {54, 34, 7, 3, 23, 78, 31, 68, 32, 40, 96, 43, 23, 54, 23, 76};
	private static String aes_secretKey = "cryptoApress";  
	private static String aes_salt = "apress";  
	
	 public static String Encrypt(String plain_message)   
	 {  
		try   
		{      
			IvParameterSpec initializationVectorSpecs = new IvParameterSpec(initialization_vector);        
  
			//the container for the secret key  
			SecretKeyFactory secretKeyContainer = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
  
			//specification parameters (secret key, salt value, iterations, key length)
			KeySpec specificationsParameters = new PBEKeySpec(aes_secretKey.toCharArray(), aes_salt.getBytes(), 65536, 256);  
			
			//generate the secret key based on the parameters set above
			SecretKey temporarySecretKey = secretKeyContainer.generateSecret(specificationsParameters);  
			
			//align the secret key with the AES algorithm
			SecretKeySpec crypto_key = new SecretKeySpec(temporarySecretKey.getEncoded(), "AES");  
			
			//set the algorithm (e.g., AES) and its mode together with its padding type
			Cipher aesAlgorithm = Cipher.getInstance("AES/CBC/PKCS5Padding");  
			aesAlgorithm.init(Cipher.ENCRYPT_MODE, crypto_key, initializationVectorSpecs);  
			  
			//get the encrypted version
			return Base64.getEncoder().encodeToString(aesAlgorithm.doFinal(plain_message.getBytes(StandardCharsets.UTF_8)));  
		}   
		catch (Exception e)   
		{  
		      System.out.println("During the encryption process, the following error(s) occured: " + e.toString());  
		}  
		return null;  
    }  
	 
	public static String Decrypt(String encrypted_message)   
	{  
	    try   
	    {  
	    	IvParameterSpec initializationVectorSpecs = new IvParameterSpec(initialization_vector);  
	    	
	    	//the container for the secret key      	
	    	SecretKeyFactory secretKeyContainer = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
	    	
	    	//specification parameters (secret key, salt value, iterations, key length) 
	    	KeySpec specificationsParameters = new PBEKeySpec(aes_secretKey.toCharArray(), aes_salt.getBytes(), 65536, 256);  
	    	
	    	//generate the secret key based on the parameters set above
	    	SecretKey temporarySecretKey = secretKeyContainer.generateSecret(specificationsParameters);  
	    	
	    	//align the secret key with the AES algorithm
	    	SecretKeySpec secretKey = new SecretKeySpec(temporarySecretKey.getEncoded(), "AES");  
	    	
	    	//set the algorithm (e.g., AES) and its mode together with its padding type
	    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
	    	cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVectorSpecs);  
	    	
	    	//get the decrypted value  
	    	return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted_message)));  
	    }   
	    catch (Exception e)   
	    {  
	    	System.out.println("Error occured during decryption: " + e.toString());  
	    }  
	    return null;  
	}  
	
	
	public static void main(String[] args) 
	{	
		//set the message that we want to encrypt
        String originalval = "Welcome to Apress. Enjoy learning cryptography";
        
        //perform the encryption         
        String encryptedval = Encrypt(originalval);  
        
        //perform the decryption  
        String decryptedval = Decrypt(encryptedval);  
        
        //show some messages
        System.out.println("Plaintext used for encryption and decryption -> " + originalval);  
        System.out.println("The encryption is -> " + encryptedval);  
        System.out.println("The decryption is -> " + decryptedval);  	}  
		   
}
