class RSA {
	
}

class AES {
	
}

class ElGamal {
	
}

public class JEP406Example {	


	static void TypeOfKeys(Object o) {	    
	    if (o instanceof RSA ) {
	        System.out.println("Two keys needed. Type the secret on the first line. Type the public key on the second line.");
	    } else if (o instanceof AES) {
	    	System.out.println("One key needed. Type the secret key on the first line.");
	    } else if (o instanceof ElGamal) {
	    	System.out.println("Two keys needed. Type the secret on the first line. Type the public key on the second line.");
	    } 	    
	}
	
	static void TypeOfKeysPatternSwitch(Object o) {	    
		if(o == null)
			throw new NullPointerException();
		else
		{
			switch (o.getClass().toString()) {
	    	case null -> throw new NullPointerException();
	        case "RSA2" -> System.out.println("Two keys needed. Type the secret on the first line. Type the public key on the second line.");
	        case "AES2" -> System.out.println("One key needed. Type the secret key on the first line.");
	        case "ElGamal2" -> System.out.println("Two keys needed. Type the secret on the first line. Type the public key on the second line.");        
	        default -> System.out.println("Pick an encryption system");
	        }
		}
	}
	

	public static void main(String[] args) {
		TypeOfKeys(new RSA());
		TypeOfKeysPatternSwitch(new RSA());
		
		System.out.println("\n***\n");
		
		TypeOfKeys(new AES());
		TypeOfKeysPatternSwitch(new AES());
	}
}
