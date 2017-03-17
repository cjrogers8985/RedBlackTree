/***************************************************************************************\
 *                                                                                     *
 *  FILENAME:                       Project4.java                                      *
 *                                                                                     *
 *  NAME:     Chris Rogers                                                             *
 *  CLASS:    SE3345 Data Structures and Introduction to Algorithmic Analysis          *
 *  SECTION:  003                                                                      *
 *  SEMESTER: Spring 2017                                                              *
 *                                                                                     *
 *  PROJECT4                                                                           *
 *                                                                                     *
 *  DESCRIPTION:                                                                       *
 *    Command line interface for red-black tree with no deletion.                      *
 *                                                                                     *
\***************************************************************************************/

package project4;

import java.util.Scanner;
import java.util.InputMismatchException;

class Project4{
	
	public static void main(String[] args){
		
		RedBlackTree rBTree = new RedBlackTree();

		showMenu();                                // show menu
		int userChoice = getMenuChoice();          // get user choice
		while(userChoice != 5){                    // while user does not choose exit ...
			runUserChoice(userChoice, rBTree);     // run method for corresponding user choice
			userChoice = getMenuChoice();          // get new user choice
		}                                          // end while loop
	}

	// runs method on RedBlackTree according to user choice
	private static void runUserChoice(int i, RedBlackTree rbt){
		switch(i){
			case 1: runInsert(rbt);
					break;
			case 2: runContains(rbt);
					break;
			case 3: rbt.printTree();
					break;
			case 4: showMenu();
					break;
		}
	}

	// gets input from user and runs insert method on RedBlackTree
	private static void runContains(RedBlackTree rbt){
		Scanner in = new Scanner(System.in);
		System.out.print("Contains: ");
		try{
			int userInput = in.nextInt();
			System.out.println(rbt.contains(userInput));
		}
		catch(InputMismatchException e){
			System.out.println("Non-integer input");
		}
	}

	// gets input from user and runs insert method on RedBlackTree
	private static void runInsert(RedBlackTree rbt){
		Scanner in = new Scanner(System.in);
		System.out.print("Insert: ");
		try{
			int userInput = in.nextInt();
			rbt.insert(userInput);
		}
		catch(InputMismatchException e){
			System.out.println("Non-integer input");
		}
	}

	// gets the menu choice from user
	private static int getMenuChoice(){
		Scanner in = new Scanner(System.in);
		int userInput = 0;
		do{
			System.out.print("Menu Choice: ");
			try{
			userInput = in.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Non-integer input");
			}
			if(userInput < 1 || userInput > 5){
				System.out.println("Number entered not 1-5");
			}
			in.nextLine();
		}while(userInput < 1 || userInput > 5);
		return userInput;
	}

	// displays menu to screen
	private static void showMenu(){
		System.out.println("MENU");
		System.out.println("1. Insert");
		System.out.println("2. Contains");
		System.out.println("3. Print Tree");
		System.out.println("4. Show Menu");
		System.out.println("5. Exit");
	}
}