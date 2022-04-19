package jca;

import java.security.*;
import java.util.Scanner;

public class GeneratingSignature 
{
   public static void main(String args[]) throws Exception {
      //the message that will be signed  
      String data_message = "Welcome to Apress and enjoy learning cryptography";
      System.out.println("The message that will be sign is -> " + data_message);
      
      //Step 1 - Declare an instance of KeyPairGenerator class
      KeyPairGenerator generatorKeyPair = KeyPairGenerator.getInstance("DSA");
      
      //Step 2 - Providing the proper initialization for the instance
      generatorKeyPair.initialize(2048);
      
      //Step 3 - Obtaining the key pair using the generateKeyPair() method
      KeyPair cryptographyKeyPair = generatorKeyPair.generateKeyPair();
      
      //Step 4.	Extract the private key from the pair using the getPrivate() method
      PrivateKey private_key = cryptographyKeyPair.getPrivate();
      
      //Step 5.	Declare an instance as signature object
      Signature signatureForMessage = Signature.getInstance("SHA256withDSA");
      
      //Step 6. Initialize the signature object
      signatureForMessage.initSign(private_key);
      byte[] theBytesRepresentation = "msg".getBytes();
      
      //Step 7. Provide the data for the signature object
      signatureForMessage.update(theBytesRepresentation);
      
      //Step 8. Compute the signature
      byte[] signature = signatureForMessage.sign();
      
      //Showing the result as output
      System.out.println("Obtained digital signature for the provided text ->  "+new String(signature, "UTF8"));
   }
}
