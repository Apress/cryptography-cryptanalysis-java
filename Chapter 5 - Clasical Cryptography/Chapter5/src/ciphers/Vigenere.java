package ciphers;

import java.util.Scanner;

public class Vigenere {
	static String ExtendKey(String message, String initialKey) {
		int toLength = message.length();

		for (int i = 0;; i++) {
			if (toLength == i)
				i = 0;
			if (initialKey.length() == message.length())
				break;
			initialKey += initialKey.charAt(i);
		}
		return initialKey;
	}

	static String Encrypt(String text, String extendedKey) {
		String result = "";

		for (int i = 0; i < text.length(); i++) {
			int toInt = (text.charAt(i) + extendedKey.charAt(i)) % 26;
			toInt += 'A';

			result += (char) (toInt);
		}
		return result;
	}

	static String Decrypt(String text, String extendedKey) {
		String result = "";

		for (int i = 0; i < text.length() && i < extendedKey.length(); i++) {

			int toInt = (text.charAt(i) - extendedKey.charAt(i) + 26) % 26;
			toInt += 'A';
			result += (char) (toInt);
		}
		return result;
	}

	static String ProcessText(String text) {
		String result = "";

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ' ')
				continue;
			else {
				if (Character.isLowerCase(text.charAt(i)))
					result += Character.toUpperCase(text.charAt(i));
				else
					result += text.charAt(i);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println("VIGENERE CIPHER\n");

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Type the message: ");
		String plainMessage = sc.nextLine();
		System.out.print("Type the key: ");
		String key = sc.nextLine();
		sc.close();
		
		System.out.println("\nEncrypting...");

		String processedText = ProcessText(plainMessage);
		String keyword = ProcessText(key);

		String extendedKey = ExtendKey(processedText, keyword);
		String encryptedText = Encrypt(processedText, extendedKey);

		System.out.println("The encrypted message is: " + encryptedText);
		System.out.println("\nDecrypting...");
		String recoveredMessage = Decrypt(encryptedText, extendedKey);
		System.out.println("The decrypted message is: " + recoveredMessage);
	}
}