import java.security.SecureRandom;

public class SecureRandomExample {

	public static void main(String[] args) {		
        SecureRandom secureRandomObject = new SecureRandom(); 
        
        System.out.println("Securely generating an integer value < 1000... " + secureRandomObject.nextInt(1000));
        System.out.println("Securely generating an integer value... " + secureRandomObject.nextInt());
        System.out.println("Securely generating a long value... " + secureRandomObject.nextLong()); 
        System.out.println("Securely generating a Boolean value... " + secureRandomObject.nextBoolean());
        System.out.println("Securely generating a double value... " + secureRandomObject.nextDouble());
        System.out.println("Securely generating a float value... " + secureRandomObject.nextFloat());
        System.out.println("Securely generating an integer number... " + secureRandomObject.nextGaussian());
        
        byte[] byteArray = new byte[15];
        secureRandomObject.nextBytes(byteArray);
        System.out.println("Securely generating a sequence of bytes...");
        System.out.print("[");
        for(int i = 0; i< byteArray.length; i++)
        {
            System.out.print(byteArray[i] + " ");
        }
        System.out.print("]\n");   
	}
}
