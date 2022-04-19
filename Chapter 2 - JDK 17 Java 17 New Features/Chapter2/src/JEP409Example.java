public class JEP409Example {

	static void Test(Cryptosystem c) {
		if (c instanceof AES_Cryptosystem)
			System.out.println("AES chosen");
		else 
			if (c instanceof RSA_Cryptosystem)
				System.out.println("RSA chosen");
			else 
				throw new RuntimeException("Unknown instance of Cryptosystem.");
		
	}
	public static void main(String[] args) {
		AES_Cryptosystem aes = new AES_Cryptosystem();
		RSA_Cryptosystem rsa = new RSA_Cryptosystem();
		
		Test(aes);
		Test(rsa);
	}
}
