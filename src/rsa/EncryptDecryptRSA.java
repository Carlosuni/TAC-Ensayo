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
			BigInteger p = new BigInteger("0");
			BigInteger q = new BigInteger("0");
			BigInteger n = new BigInteger("0");
			BigInteger z = new BigInteger("0");
			BigInteger d = new BigInteger("0");
			BigInteger e;
			//BigInteger i;
			BigInteger unitBigInt = new BigInteger("1");
			BigInteger zeroBigInt = new BigInteger("0");

			//int msg = sc.nextInt();
			BigInteger msg = new BigInteger(args[0]);
			System.out.println("El mensaje (numero) introducido es = " + msg);
			BigInteger c;
			BigInteger msgback; 
			p =  new BigInteger(args[1]);
			System.out.println("El primer primo p introducido es = " + p);
			q =  new BigInteger(args[2]);
			System.out.println("El segundo primo q introducido es = " + q);
			
			n = p.multiply(q);
			z = p.subtract(unitBigInt).multiply(q.subtract(unitBigInt));
			System.out.println("El valor de  z es = " + z);		

			for (e = new BigInteger("2"); e.compareTo(z) == -1; e = e.add(unitBigInt)) {
				if (gcd(e, z, zeroBigInt).compareTo(unitBigInt) == 0) {	          // e is for public key exponent
					break;
				}
			}
			
			System.out.println("El valor de es es = " + e);	
			
			for (BigInteger i = new BigInteger("0");
					(i.compareTo(new BigInteger("9")) == 0) || (i.compareTo(new BigInteger("9")) == -1);
					i = i.add(unitBigInt)) {
				BigInteger x = unitBigInt.add(i.multiply(z));
				
				if (x.mod(e).compareTo(zeroBigInt) == 0) {      //d is for private key exponent
					d = x.divide(e);
					break;
				}
			}
			System.out.println("El valor de d es = " + d);		
			c = msg.modPow(e, n);
			System.out.println("El mensaje encriptado es = " + c);
			//System.out.println(c);
	        // Converting int value of n to BigInteger
			//BigInteger N = BigInteger.valueOf(n);
			// Converting float value of c to BigInteger
			//BigInteger C = BigDecimal.valueOf(c).toBigInteger();
			msgback = (c.modPow(d, n));
			System.out.println("EL mensaje desencriptado es = " + msgback);
			//System.out.println(msgback);
		} else {
			System.out.println("Error: Introduzca los 3 parámetros-> mensaje, p y q (primos)\n");
		}
		
	}

	static BigInteger gcd(BigInteger e, BigInteger z, BigInteger zeroBigInt) {
		if (e.compareTo(zeroBigInt) == 0) {
			return z;
		} else {
			return gcd(z.mod(e),e, zeroBigInt);
		}
	}
}
