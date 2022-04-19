import java.util.Random;

public class RandomExamples {

	public static void main(String[] args) {
		
		Random randomObject = new Random();
        System.out.println("Generating an integer value < 1000... " + randomObject.nextInt(1000));
        System.out.println("Generating an integer value... " + randomObject.nextInt());
        System.out.println("Generating a long value... " + randomObject.nextLong()); 
        System.out.println("Generating a Boolean value... " + randomObject.nextBoolean());
        System.out.println("Generating a double value... " + randomObject.nextDouble());
        System.out.println("Generating a float value... " + randomObject.nextFloat());
        System.out.println("Generating an integer number... " + randomObject.nextGaussian());
        
        byte[] byteArray = new byte[15];
        randomObject.nextBytes(byteArray);
        System.out.println("Generating a sequence of bytes...");
        System.out.print("[");
        for(int i = 0; i< byteArray.length; i++)
        {
            System.out.print(byteArray[i] + " ");
        }
        System.out.print("]\n");     
	}
}
