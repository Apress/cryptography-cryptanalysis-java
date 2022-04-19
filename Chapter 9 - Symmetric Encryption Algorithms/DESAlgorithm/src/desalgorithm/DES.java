package desalgorithm;

import java.io.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class DES 
{
	//instance for encryption  
	private static Cipher encOp;
	
	//instance for decryption  
	private static Cipher decOp;

	//path for the file that will be encrypted	
	private static final String textFileToEncrypt = "D:/apressFile.txt";
	
	//path for the encrypted file
	private static final String encFile = "D:/apress_enc.txt";
	
	//path for decryptied file
	private static final String decFile = "D:/apress_dec.txt";
	
	//vector for initialization	
	private static final byte[] iv = { 25, 38, 15, 43, 59, 92, 66, 73 };
	
	public static void main(String[] args) 
	{	
		try   
		{  
			//setting up the key  
			SecretKey secret_key = KeyGenerator.getInstance("DES").generateKey();  
			AlgorithmParameterSpec parameters_specs = new IvParameterSpec(iv);  
			
			
			//specify the encryption mode
			encOp = Cipher.getInstance("DES/CBC/PKCS5Padding");  
			encOp.init(Cipher.ENCRYPT_MODE, secret_key, parameters_specs);  
			
			//specify the decryption mode
			decOp = Cipher.getInstance("DES/CBC/PKCS5Padding");  
			decOp.init(Cipher.DECRYPT_MODE, secret_key, parameters_specs);  
			
			//encrypt the file  
			File forEncryption = new File(textFileToEncrypt);			
			if(!forEncryption.exists())
				throw new FileNotFoundException("The file does not exist. Please create it and write the text you wish to encrypt.");
			ComputeEncOperation(new FileInputStream(textFileToEncrypt), new FileOutputStream(encFile));  
			
			//decrypt the file 
			ComputeDecOperation(new FileInputStream(encFile), new FileOutputStream(decFile));  
			
			//show a message for letting the user know the situation  
			System.out.println("The files for encryption and decryption results have created successfully.");  
		}   
		
		//catch any exception encountered  
		catch (Exception e)   
		{  
			//prints the message (if any) related to exceptions  
			e.printStackTrace();  
		}  
	}  
	
	//write bytes content to the files   
	private static void writeToFileTheBytes(InputStream input, OutputStream output) throws IOException   
	{  
		byte[] writeBuffer = new byte[512];  
		int readBytes = 0;  
		while ((readBytes = input.read(writeBuffer)) >= 0)   
		{  
			output.write(writeBuffer, 0, readBytes);  
		}  
		//closing the output stream  
		output.close();  
		//closing the input stream  
		input.close();  
	}
	
	//encryption operation
	private static void ComputeEncOperation(InputStream inFile, OutputStream ouFile) throws IOException   
	{  
		ouFile = new CipherOutputStream(ouFile, encOp);  
		
		//write the bytes obtained from the encryption to the file   
		writeToFileTheBytes(inFile, ouFile);  
	}   
	
	//decryption operation
	private static void ComputeDecOperation(InputStream inFile, OutputStream ouFile) throws IOException   
	{  
		inFile = new CipherInputStream(inFile, decOp);  
		
		//write the bytes obtained from the decryption to the file     
		writeToFileTheBytes(inFile, ouFile);  
	}  			   
}
