import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Stream;

public class JEP356Example {

	public static void main(String[] args) {	    
		
		Stream<RandomGeneratorFactory<RandomGenerator>> allPRNGs = RandomGeneratorFactory.all();
		allPRNGs.map(prng -> prng.name() + " [ Group: "  + prng.group() + "; "
				+ (prng.isArbitrarilyJumpable() ? " arbitrary-jump" : "")
				+ (prng.isHardware()? " hardware" : "")
				+ (prng.isJumpable() ? " jump" : "")
				+ (prng.isLeapable()? " leap" : "")
				+ (prng.isSplittable() ? " split" : "")								
				+ (prng.isStatistical()? " statistical" : "")
				+ (prng.isStochastic()? " stochastic" : "")
				+ (prng.isStreamable() ? " stream" : "")
				+ "; noOfBits: "+ prng.stateBits()
				+ "]"
				).sorted().forEach(System.out::println);	
		
		System.out.println("\n*****\n");
		
		RandomGenerator prng1 = RandomGeneratorFactory.of("Random").create(45);
		System.out.println("prng1 - " + prng1.getClass()); 
		RandomGenerator prng2 = new Random(45);
		System.out.println("prng2 - " + prng2.getClass()); 
		RandomGenerator prng3 = RandomGeneratorFactory.getDefault().create(45); 
		System.out.println("prng3 - " + prng3.getClass());  
		RandomGenerator prng4 = RandomGenerator.getDefault();		
		System.out.println("prng4 - " + prng4.getClass()); 
	}
}
