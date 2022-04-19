import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FFSScheme {

	// declare system paramters: prime numbers p,q, computed n, and given k
	private static BigInteger p;
	private static BigInteger q;
	private static BigInteger n;
	private static int k;
	
	// used to generate random values
	private static Random random = new Random();

	// the set of k random integer values (private/secret key)
	private static List<BigInteger> random_s_set = new ArrayList<>();
	// the set of k random bits
	private static BitSet random_b_set = new BitSet(k);
	// the set of k computed values in the form of tuple v (public key)
	private static List<BigInteger> computed_v_set = new ArrayList<>();

	// the witness
	private static BigInteger x;
	// the set of the challenge values
	private static BitSet random_e_set = new BitSet(k);
	// the response
	private static BigInteger y;

	// the product used in the computation of y
	private static BigInteger product_s;
	// the product used in the computation of z
	private static BigInteger product_v;
	
	// the random integer value used in messages exchange
	private static BigInteger r;
	
	// the random bit value used in messages exchange
	private static int b;
	
	// method that implements system parameters generation
	private static void GenerateParameters() {
		// p = generate a big prime integer on 128 bits
		p = BigInteger.probablePrime(128, random);
		// q = generate a big prime integer on 128 bits
		q = BigInteger.probablePrime(128, random);
		// n = pq
		n = p.multiply(q);

		// k is given by the user
		System.out.println("k= ");
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		sc.close();
	}

	private static void GenerateKeys() {

		for (int i = 0; i < k; i++) {

			// generate each s_i value
			// the number of bits used for representation of each s_i
			// should be the same as n
			BigInteger s_i = new BigInteger(n.bitLength() + 1, random).mod(n);

			// check whether gcd(s_i, n) = 1
			while (s_i.gcd(n).intValue() != 1) {
				s_i = new BigInteger(n.bitLength() + 1, random).mod(n);
			}

			// add the value s_i for which gcd(s_i, n) = 1 to the s set
			random_s_set.add(s_i);

			// generate random bit b_i and add it to the set of bits b
			random_b_set.set(i, random.nextBoolean());

			// compute (-1)^(b_i)
			BigInteger minus_one_pow = (((new BigInteger("-1")).pow(random_b_set.get(i) ? 1 : 0)));

			// compute v_i = (-1)^(b_i) x ((s_i)^2)^(-1) mod n
			BigInteger computed_v_i = minus_one_pow.multiply(random_s_set.get(i).pow(2)).modInverse(n);

			// add v_i to the set v
			computed_v_set.add(computed_v_i);
		}

	}

	private static void ExchangeMessages() {

		// generate random integer r
		// mod n ensures that r is in [1, n-1]
		r = new BigInteger(n.bitLength() + 1, random).mod(n);		

		// generate random bit b
		b = random.nextBoolean() ? 1 : 0;		

		// compute x = (-1)^b x r^2
		x = (((new BigInteger("-1")).pow(b)).multiply((r.pow(2)))).mod(n);

		// generate random bit e_i and add it to the set of challenge bits e
		for (int i = 0; i < k; i++) {
			random_e_set.set(i, random.nextBoolean());
		}

		// compute the product (s_1)^(e_1) x ... x (s_k)^(e_k)
		product_s = new BigInteger("1");
		for (int i = 0; i < k; i++) {
			product_s = product_s.multiply(random_s_set.get(i).pow(random_e_set.get(i) ? 1 : 0));
		}

		// compute y = [the product from above] x r (mod n)
		y = product_s.multiply(r.mod(n)).mod(n);
	}

	private static void CheckProof() {

		// compute the product (v_1)^(e_1) x ... x (v_k)^(e_k)
		product_v = new BigInteger("1");
		for (int i = 0; i < k; i++) {
			product_v = product_v.multiply(computed_v_set.get(i).pow(random_e_set.get(i) ? 1 : 0));
		}
		
		//compute z = [the product from above] x y^2 (mod n)
		//BigInteger z = (y.pow(2).mod(n)).multiply(product_v).mod(n);

		BigInteger z = new BigInteger("1234555");

		System.out.print("\nz = " + z.toString());

		System.out.print("\nResponse: ");

		//check whether z = +/- x and z != 0
		if ((z.equals(x) || z.equals(x.negate().mod(n))) && !z.equals(new BigInteger("0"))) {
			System.out.print("\nAccept");
		} else {
			System.out.print("\nReject");
		}
	}

	public static void main(String[] args) {

		GenerateParameters();

		GenerateKeys();

		ExchangeMessages();

		System.out.println("\n=== System parameters and computed values ===");

		System.out.print("\np = " + p);
		System.out.print("\nq = " + q);
		System.out.print("\nn = " + n);

		System.out.print("\nSecret key: ( ");
		for (int i = 0; i < k; i++)
			System.out.print(random_s_set.get(i) + " ");
		System.out.print(")");

		System.out.print("\nPublic key: ( ");
		for (int i = 0; i < k; i++)
			System.out.print(computed_v_set.get(i) + " ");
		System.out.print(")");
		
		System.out.print("\nr = " + r);
		
		System.out.print("\nb = " + b);

		System.out.print("\nx = " + x);

		System.out.print("\nRandom bits e: ( ");
		for (int i = 0; i < k; i++)
			System.out.print(random_e_set.get(i) ? 1 + " " : 0 + " ");
		System.out.print(")");

		System.out.print("\ny = " + y);

		CheckProof();
	}
}