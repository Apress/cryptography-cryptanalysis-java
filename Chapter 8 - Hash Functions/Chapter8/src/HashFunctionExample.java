import java.security.MessageDigest;
import java.util.Scanner;

public class HashFunctionExample {
   public static void main(String args[]) throws Exception
   { 
	  System.out.println("\n Type the text: ");
	  
	  Scanner scn = new Scanner(System.in);
      String input = scn.nextLine();
      scn.close();
      
      System.out.println("\n The input text: " + input);	  
      
      MessageDigest output_sha = MessageDigest.getInstance("SHA-512");
      
      output_sha.update(input.getBytes());
      
      
      byte[] digest = output_sha.digest();      
      System.out.println(digest);  
     
      
      StringBuffer hex_digest = new StringBuffer();
      
      for (int i = 0;i<digest.length;i++) 
      {
    	  hex_digest.append(Integer.toHexString(0xFF & digest[i]));
      }
      System.out.println("The hex representation : " + hex_digest.toString());     
   }
}

