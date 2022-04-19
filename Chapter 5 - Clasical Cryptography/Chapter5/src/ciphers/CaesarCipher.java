package ciphers;

import java.util.Scanner;

public class CaesarCipher {

	public static String Encrypt(String text, int positions) {
		String toEncrypt = "", result = "";
		//format the text to be encrypted
		for (int i = 0; i < text.length(); i++) {
			//remove space characters
			if (text.charAt(i) == ' ')
				continue;
			else {
				//if the character is lowercase, make it uppercase
				if (Character.isLowerCase(text.charAt(i)))
					toEncrypt += Character.toUpperCase(text.charAt(i));
				//otherwise keep the uppercase character
				else
					toEncrypt += text.charAt(i);
			}
		}
		for (int i = 0; i < toEncrypt.length(); i++) {
			//shift the current letter of the message with given positions to right
			char shiftedLetter = (char) (toEncrypt.charAt(i) + positions);

			//if the ASCII code exceeds Z, then bring it back in the interval A..Z
			if (shiftedLetter > 'Z')
				shiftedLetter = (char) (shiftedLetter + 'A' - 'Z' - 1);

			result += shiftedLetter;
		}

		return result;
	}

	public static String Decrypt(String text, int positions) {
		//the encrypted code is already uppercase, 
		//therefore there is no need of formatting 
		String result = "";
		for (int i = 0; i < text.length(); i++) {

			//shift the current letter of the message with given positions to left
			char shiftedLetter = (char) (text.charAt(i) - positions);

			//if the ASCII code exceeds A, then bring it back in the interval A..Z
			if (shiftedLetter < 'A')
				shiftedLetter = (char) (shiftedLetter - 'A' + 'Z' + 1);

			result += shiftedLetter;

		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println("CAESAR CIPHER\n");

		Scanner sc = new Scanner(System.in);

		// Reading the input: plain message and the secret key
		System.out.print("Type the message: ");
		String message = sc.nextLine();
		System.out.print("Type the key: ");
		int key = sc.nextInt();
		sc.close();

		// Encrypting the plain message
		System.out.println("\nEncrypting...");
		String encryptedMessage = Encrypt(message, key);
		System.out.println("The encrypted message is: " + encryptedMessage);

		// Decrypting the encrypted message
		System.out.println("\nDecrypting...");
		String recoveredMessage = Decrypt(encryptedMessage, key);
		System.out.println("The decrypted message is: " + recoveredMessage);

	}
}
