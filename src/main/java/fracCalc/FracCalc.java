/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
public class FracCalc {
	
    public static void main(String[] args)
    {
       Scanner userInput = new Scanner(System.in);
       System.out.println("Hello! Welcome to the fraction calculator. At any time, you can type quit to quit.");
       System.out.print("Enter an equation. ");
       String input = userInput.nextLine();
       produceAnswer(input);
    }


    public static String produceAnswer(String input)
    {
    	int firstSpace = input.indexOf(" ");
    	String firstNumber = input.substring(0 , firstSpace);
    	String secondNumber = input.substring(firstSpace + 3);
    	String operator = input.substring(firstSpace + 1, firstSpace + 2);
    	String separated = "whole:" + firstNumber + "";
    	
    	System.out.println("The first input is " + firstNumber);
    	System.out.println("The operator is " + operator);
    	System.out.println("The second input is " + secondNumber);
        return secondNumber;
    }



}
