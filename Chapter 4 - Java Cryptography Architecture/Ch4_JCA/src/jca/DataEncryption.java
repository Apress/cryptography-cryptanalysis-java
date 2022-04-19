package jca;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import javax.crypto.Cipher;

public class DataEncryption {
   public static void main(String args[]) throws Exception{
      
	  //create an instance for signature
      Signature mySignature = Signature.getInstance("SHA256withRSA");
      System.out.println("\n The signature instance -> " + mySignature);
      
      //crere an instance as generator for a pair key
      KeyPairGenerator key_pair_gen = KeyPairGenerator.getInstance("RSA");
      System.out.println("\n The key pair generator -> " + key_pair_gen);
      
      //specify the size
      key_pair_gen.initialize(2048);
      System.out.println("\n The key pair generator size -> " + key_pair_gen);
      
      //create the key pair instance
      KeyPair pair = key_pair_gen.generateKeyPair();
      System.out.println("\n The instance of the key pair -> " + pair);
	
      //specify the algorithm as instance
      Cipher algorithm = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      System.out.println("\n The algorithm -> " + algorithm);
      
      //choose the mode (encryption/decryption)
      algorithm.init(Cipher.ENCRYPT_MODE, pair.getPublic());
      System.out.println("\n The algorithm with the mode -> " + algorithm);
      
      //choose a text that will be encrypted
      byte[] input = "Learning Cryptography with Java".getBytes();	  
      algorithm.update(input);
      System.out.println("\n The algorithm with the mode -> " + algorithm);
	  
      //encrypting the data
      byte[] encrypted_text = algorithm.doFinal();	 
      System.out.println("\n Encrypted text -> " + new String(encrypted_text, "UTF8"));
   }
}
