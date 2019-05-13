/**
 * 
 */
package rsa;

/**
 * @author Carlos Dumont & Luis Monge
 *
 */

import java.util.*;
import java.math.*;

class EncryptDecryptRSA{
	/**
	 * @param args
	 */
	
	public static void main(String args[]) {
		if (args.length == 3) {
			//Scanner sc = new Scanner(System.in);
			int p, q, n, z, d = 0, e, i;
			//int msg = sc.nextInt();
			int msg = Integer.parseInt(args[0]);
			System.out.println("El mensaje (numero) introducido es = " + msg);
			double c;
			BigInteger msgback; 
			p =  Integer.parseInt(args[1]);
			System.out.println("El primer primo p introducido es = " + p);
			q =  Integer.parseInt(args[2]);
			System.out.println("El primer primo p introducido es = " + q);
			
			n = p * q;
			z = (p - 1) * (q - 1);
			System.out.println("El valor de  z es = " + z);		

			for (e = 2; e < z; e++) {
				if (gcd(e, z) == 1) {	          // e is for public key exponent
					break;
				}
			}
			System.out.println("El valor de es es = " + e);				
			for (i = 0; i <= 9; i++) {
				int x = 1 + (i * z);
				if(x % e == 0) {      //d is for private key exponent
					d = x / e;
					break;
				}
			}
			System.out.println("El valor de d es = " + d);		
			c = (Math.pow(msg, e)) % n;
			System.out.println("El mensaje encriptado es = " + c);
			//System.out.println(c);
	        // Converting int value of n to BigInteger
			BigInteger N = BigInteger.valueOf(n);
			// Converting float value of c to BigInteger
			BigInteger C = BigDecimal.valueOf(c).toBigInteger();
			msgback = (C.pow(d)).mod(N);
			System.out.println("EL mensaje desencriptado es = " + msgback);
			//System.out.println(msgback);
		} else {
			System.out.println("Error: Introduzca los 3 parámetros-> mensaje, p y q (primos)\n");
		}
		
	}

	static int gcd(int e, int z) {
		if(e == 0) {
			return z;
		} else {
			return gcd(z % e,e);
		}
	}
}
