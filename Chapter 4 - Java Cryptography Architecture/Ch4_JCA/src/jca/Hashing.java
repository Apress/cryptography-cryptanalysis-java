package jca;

import java.security.MessageDigest;
import java.util.Scanner;

public class Hashing {
   public static void main(String args[]) throws Exception
   {
      //add some text that we will use it for encryption
      String message = "Welcome to Appress and enjoy learning cryptography";
      System.out.println("\n The text for which we will provide the digest (hash) is -> " + message);
	  
      //declare an instance for which we will provide the hash digest using SHA-256
      MessageDigest messsage_digest = MessageDigest.getInstance("SHA-256");

      //get the bytes representation by providing data for updating the digest
      messsage_digest.update(message.getBytes());
      
      //obtain the hash of the message
      byte[] digest = messsage_digest.digest();      
      System.out.println(digest);  
     
      //provide conversion from byte to hex string format
      StringBuffer hex_representation = new StringBuffer();
      
      for (int i = 0;i<digest.length;i++) 
      {
    	  hex_representation.append(Integer.toHexString(0xFF & digest[i]));
      }
      System.out.println("The hex representation : " + hex_representation.toString());     
   }
}