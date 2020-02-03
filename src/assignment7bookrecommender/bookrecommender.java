//Dominic Salvatore Lanza
//Professor John Fiore
//1068 Program Design & Abstraction; Instructor Zhongdong Liu Section 004
//PROGRAM DESCRIPTION

//This program simply prompts the user to enter books they may or may not have read.
//The program reads these books from a book.txt file in the background. 
//As well as compares other peoples scores to give the user the a recommended book to read.
//based on a few metrics such as similarity scores and averages.


package assignment7bookrecommender;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class bookrecommender {
	public static void main(String args[]) throws FileNotFoundException {
	String A[]=booknames();
	double userrating[] = userrating(A);
	double pplratings[][] = ratings2();
	double simscores[] = similarityscore(pplratings,userrating); 
	double weightaverage[] = weightedaveragepercol(pplratings);
	finalmethod(pplratings, simscores, weightaverage, A, userrating);
	}
	
	
	
	public static double[] userrating(String A[]) {
		Scanner in = new Scanner(System.in);
			double userrating[] = new double[20];
			String prompt = "What is your rating of this book 1-5?: ";
			String unless = "If you have not read the book type in -1";
		for(int i =0; i<A.length; i++) {
			System.out.println(prompt + " " + A[i]);
			System.out.println(unless);
			userrating[i] = in.nextInt();
			if(i >= 19) {
				break;
			}
		}
		return userrating;
	}
	
	public static String[] booknames() throws FileNotFoundException {
		Scanner in = new Scanner(new File("books.txt"));
		String []books;
		books = new String[20];	
			for(int i = 0; i < books.length; i++  ) {
				String line = in.nextLine();
				books[i] = line;	
			}	
			return books;
		}

	public static double sqrtSquares(double []A) {
			//check A for -1
		double sum = 0;
				for(int i = 0; i<A.length; i++) {
					if(A[i] < 0 ) {
						A[i] = 0;
					}
					A[i] = Math.sqrt(A[i]);
					//calculate the running sum;
					sum += A[i] * A[i] ;
				}
		return Math.sqrt(sum);
		}
		
		
	public static double similarity(double []A, double []B) {
		double sum = 0;
		double p1 = sqrtSquares(A);
		double p2 = sqrtSquares(B);
		for (int i=0; i<A.length; i++) {
			if (A[i]> 0) {
				if (B[i]> 0) {
					sum += A[i]*B[i];
				}
		    }
		}
		return sum/(p1*p2);
	}
	
	public static double[] similarityscore(double pplratings[][], double yourrating[] ) {
		double []scores = new double[30];
		for(int i = 0; i< 30; i++) {
			scores[i] = similarity(yourrating, pplratings[i]);
		}
		
			return scores;
	}
	
		
	public static double[][] ratings2() throws FileNotFoundException {
		Scanner in = new Scanner(new File("ratings.txt"));
		double [][]ratings = new double[30][20];
		for (int rows = 0; rows< ratings.length; rows++) {
			for(int col = 0; col < ratings[rows].length; col++) {
				double line = in.nextDouble();
				ratings[rows][col] = line;
			}
		}
		return ratings;
				}
		
		public static double[] weightedaveragepercol(double A[][]) {
			double []averageof20 = new double[20];
			double average = 0;
			for(int row =0; row < 20; row++) {
				double total = 0;

				for(int column = 0; column < 30; column++) {
					if(A[column][row] > 0) {
					total += A[column][row];
					} else {
						total += 0.0;
					}
					}
				average = total/ A.length;
				averageof20[row] = average;
				}
			return averageof20;
		}
		public static double finalmethod(double pplratings[][], double simscore[], double weightaverage[], String A[], double userrating[]) {
			double largest = 0;
			int largestindex = 0;
			int largestweight = 0;
			for(int i = 0; i < simscore.length; i++) {
				if (simscore[i] > largest) {
				largest = simscore[i];
				}
				}
				//largest similarity score
			for (int j = 0; j< simscore.length; j++) {
				if (simscore[j] > simscore[largestindex]) {
					largestindex = j;
					}
				}
					int smallestvalue = 0;
						for(int g = 0; g < userrating.length; g++) {
							if (userrating[g] < userrating[smallestvalue] ) {
							smallestvalue = g;
							}
					}
				
					for(int p = 0; p < pplratings[largestindex].length; p++) {
						if(pplratings[largestindex][smallestvalue] > 0) {
							System.out.println("We recommend you to read " + A[smallestvalue]);
							break;
						}
						if(pplratings[largestindex][smallestvalue + 1]> 0) {
							System.out.println("We recommend you to read " + A[smallestvalue]);
							break;
						}
						if(pplratings[largestindex][smallestvalue + 2]> 0) {
							System.out.println("We recommend you to read " + A[smallestvalue]);
							break;
						}
						if(pplratings[largestindex][smallestvalue + 3]> 0) {
							System.out.println("We recommend you to read " + A[smallestvalue]);
							break;
						}
						
						break;
					}
			return 0.0;	
			}
				}

		
	

