import java.math.BigInteger;
import java.util.Scanner;

public class ElGamalSignature {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Value of p");
		BigInteger p=new BigInteger(sc.next());
		System.out.println("Value of alpha");
		BigInteger alpha=new BigInteger(sc.next());
		System.out.println("Value of m");
		BigInteger m=new BigInteger(sc.next());
		System.out.println("Value of k");
		BigInteger k=new BigInteger(sc.next());	
		sc.close();
		
		signingAlgorithm sign = new signingAlgorithm(p, alpha, k, m);
		
		System.out.println("Public key: " + p.intValue() + " " + alpha.intValue() + " " + sign.beta.intValue());
		System.out.println("y1: " + sign.y1 + "; y2: " + sign.y2);
		
		verifyingAlgorithm verify = new verifyingAlgorithm(m, sign.y1, sign.y2, sign.alpha, sign.beta, p);
		verify.verify();
	}

}


class signingAlgorithm {
	public BigInteger p, alpha, beta, k, m, y1, y2;
	private BigInteger a = new BigInteger("3");
	
	public signingAlgorithm(BigInteger p, BigInteger alpha, BigInteger k, BigInteger m)
	{
		this.p = p;
		this.alpha = alpha;
		this.k = k;
		this.m = m;
		
		computeBeta();
		computeY1();
		computeY2();
	}	
	
	public void computeBeta() {
		this.beta = alpha.modPow(a, p);		
	}
	
	public void computeY1() {
		this.y1 = alpha.modPow(k, p);		
	}
	
	public void computeY2() {
		BigInteger invK = k.modInverse(p.subtract(BigInteger.ONE));
		this.y2 = ((m.subtract(a.multiply(y1))).modInverse(p.subtract(BigInteger.ONE)).multiply(invK)).mod(p.subtract(BigInteger.ONE));
	}	
}


class verifyingAlgorithm {
	
	public BigInteger m, y1, y2, alpha, beta, p;
	
	public verifyingAlgorithm(BigInteger m, BigInteger y1, BigInteger y2, BigInteger alpha, BigInteger beta, BigInteger p) {
		this.m = m;
		this.y1 = y1;
		this.y2 = y2;
		this.alpha = alpha;
		this.beta = beta;		
		this.p = p;
	}
	
	public void verify() {
		BigInteger left = (y1.pow(y2.intValue())).multiply(beta.pow(y1.intValue())).mod(p);
		BigInteger right = alpha.modPow(m, p);		
		
		if (left.compareTo(right) == 0) 
			System.out.println("Signature verified");		
		else 
			System.out.println("Signature missmatch");		
	}
}