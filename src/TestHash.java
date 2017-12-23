/**
 * A class to test hash function variations
 * Requires two integers to be provided on the command line
 *
 */
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class TestHash {

	
	public static void main(String[] args) {

		try {
			int radix = Integer.parseInt(args[0]);
			int modulus = Integer.parseInt(args[1]);
			File inFile = new File(args[2]);
			Scanner in = new Scanner(inFile);
			String inStr;
            //set a counter array. the hash value is index, and the value is each hashvalue' total.
			int[] counter = new int[modulus];

			System.out.println();
			System.out.println("Using radix " + radix + " and modulus " + modulus + ".");
			System.out.println();
			System.out.println("  Input  |  hash value");
			System.out.println("----------------------");
            
			int hashValue;
			while (in.hasNext()) {
				inStr = in.next();
				hashValue = Hash.hash(inStr, radix, modulus);
				System.out.printf("%7s  |%8d\n", inStr, hashValue);
				counter[hashValue]++;
			}

			System.out.println("\n");
			
			double sum = 0; 
			int max = 0;
			int min = counter[0];
			int zeroCount = 0;
			double variance = 0;
			double deviation = 0;
			//print counter for each hash value
			for (int i=0; i< counter.length ; i++) {
				System.out.println( "The total of hash value " + i + " is:" + counter[i]);
				sum = sum + counter[i];
				//calculate max
				if(counter[i]> max){
					max = counter[i];
				}
				//calculate min
				if(counter[i] < min){
					min = counter[i];
				}
				//count zeros
				if(counter[i] == 0){
					zeroCount += 1;
				}
			}
			
			//calculate mean
			double mean = sum/counter.length;
			//calculate variance
			for (int i = 0; i < counter.length; i++){
				variance += (counter[i] - mean) * (counter[i] - mean);
			}
			variance = variance/counter.length;
			//calculate standard deviation
			deviation = Math.sqrt(variance);
			
			System.out.println("total is: " + sum);
			System.out.println("mean is: " + mean);
			System.out.println("variance is: " + variance);
			System.out.println("standard deviation: " + deviation);
			System.out.println("max is: " + max);
			System.out.println("min is: " + min);
			System.out.println("The count of zeros is: " + zeroCount);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found. " + e);
		}
	}
}
