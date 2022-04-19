package jca;

import java.security.*;
import java.util.Scanner;

public class VerifyingSignature 
{
   public static void main(String args[]) throws Exception   
   {
	   //The message used for generating and verifying process of the signature
	   String message_data = "Welcome to Apress and enjoy learning cryptography";
	   System.out.println("The message used -> " + message_data);
	   
      //Step 1.	Declare an instance of KeyPairGenerator class
      KeyPairGenerator generating_key_pair = KeyPairGenerator.getInstance("DSA");
	      
      //Step 2.	Providing the proper initialization for the instance
      generating_key_pair.initialize(2048);
	      
      //Step 3.	Obtaining the key pair using the generateKeyPair() method
      KeyPair cryptographic_pair = generating_key_pair.generateKeyPair();
      
      //Step 4.	Extract the private key from the pair
      PrivateKey private_crypto_key = cryptographic_pair.getPrivate();

      //Step 5 - Declare an instance for the signature object
      Signature signature = Signature.getInstance("SHA256withDSA");

      //Step 6.	Providing the proper initialization for the signature object
      signature.initSign(private_crypto_key);
      byte[] bytes = message_data.getBytes();
      System.out.println("The representation as bytes is: " + bytes);
      
      //Step 7.	Provide data for the signature instance
      signature.update(bytes);
      
      //Step 8.	Compute the signature
      byte[] computedSignature = signature.sign();      
      
      //Step 9.	Provide verification for the signature instance
      signature.initVerify(cryptographic_pair.getPublic());
      signature.update(bytes);
      
      //Step 10. Provide verification for the data through updating the signature, and
      //Step 11. Invoke the verifying process for the signature
      boolean bool = signature.verify(computedSignature);
      
      if(bool) 
      {
         System.out.println("The signature has been checked (verified) with success");   
      } 
      else 
      {
         System.out.println("The signature has been failed");
      }
   }
}