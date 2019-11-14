/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Hello! Welcome to the fraction calculator. At any time, you can type quit to quit.");
		System.out.print("Enter an equation: ");
		String input = userInput.nextLine();
		while(input.indexOf("quit") == -1) {
			produceAnswer(input);
			System.out.print("Enter an equation: ");
			input = userInput.nextLine();			
	}
		System.out.println("Program quit.");
	}

	public static String produceAnswer(String equation) {
		int firstSpace = equation.indexOf(" ");
		String firstNumber = equation.substring(0, firstSpace);
		String secondNumber = equation.substring(firstSpace + 3);
		char cha1 = secondNumber.charAt(1);
		char cha2 = secondNumber.charAt(2);
		
		
		if(cha1 == '_' || cha2 == '_') {
			
		}
		String operator = equation.substring(firstSpace + 1, firstSpace + 2);
		String separated = "whole:" + firstNumber + "";

		int whole = wholeNum(secondNumber);
		int numerator = numerator1(secondNumber);
		int denominator = denominator1(secondNumber);
		String split = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
		System.out.println(split);
		return(split);
	}
	public static int wholeNum(String secondNumber) {
		int whole = 0;
		if(secondNumber.indexOf('_') != -1) {
			whole = Integer.parseInt(secondNumber.substring(0, secondNumber.indexOf('_')));
		} else if(secondNumber.indexOf('/') == -1){
			whole = Integer.parseInt(secondNumber);
		} else {
			whole = 0;
		}
		return whole;
	}
	public static int numerator1(String secondNumber) {
		int numerator = 0;
		if(secondNumber.indexOf('_') != -1) {
			numerator = Integer.parseInt(secondNumber.substring((secondNumber.indexOf('_') + 1), secondNumber.indexOf('/')));
		} else {
			numerator = Integer.parseInt(secondNumber.substring(0, secondNumber.indexOf('/')));
		}
		return numerator;
	}
	public static int denominator1(String secondNumber) {
		int denominator = 0;
		if(secondNumber.indexOf('/') != -1) {
			denominator = Integer.parseInt(secondNumber.substring((secondNumber.indexOf('/') + 1)));
		} else {
			denominator = 1;
		}
		return denominator;
	}

}
