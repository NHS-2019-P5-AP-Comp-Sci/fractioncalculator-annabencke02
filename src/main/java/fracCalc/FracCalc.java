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
		while (input.indexOf("quit") == -1) {
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
		String operator = equation.substring(firstSpace + 1, firstSpace + 2);

		int whole1 = wholeNum1(firstNumber);
		int numerator1 = numerator1(firstNumber);
		int denominator1 = denominator1(firstNumber);

		int whole2 = wholeNum1(secondNumber);
		int numerator2 = numerator1(secondNumber);
		int denominator2 = denominator1(secondNumber);

		// adding
		String answer = "";
		if (operator.indexOf("+") != -1) {
			// adding two whole numbers
			if (numerator1 == 0 && numerator2 == 0 && denominator1 == 1 && denominator2 == 1) {
				answer = "" + (whole1 + whole2);
				// adding two fractions
			} else if (whole1 == 0 && whole2 == 0) {
				if (denominator1 != denominator2) {
					int newDenominator = denominator1 * denominator2;
					numerator1 *= denominator2;
					numerator2 *= denominator1;
					int newNumerator = numerator1 + numerator2;
					answer = newNumerator + "/" + newDenominator;
				} else if (denominator1 == denominator2) {
					int newNumerator = numerator1 + numerator2;
					answer = newNumerator + "/" + denominator1;
				}
			} else if (whole1 != 0 || whole2 != 0) {
				int newNumerator1 = 0;
				int newNumerator2 = 0;
				if (whole1 < 0) {
					newNumerator1 += (whole1 * denominator1) - numerator1;
				} else {
					newNumerator1 += (whole1 * denominator1) + numerator1;
				}
				if (whole2 < 0) {
					newNumerator2 += (whole2 * denominator2) - numerator2;
				} else {
					newNumerator2 += (whole2 * denominator2) + numerator2;
				}
				int newNumerator = 0;
				if (denominator1 != denominator2) {
					int d1 = denominator1;
					int d2 = denominator2;
					denominator1 *= denominator2;
					denominator2 *= d1;
					newNumerator1 *= d2;
					newNumerator2 *= d1;
					newNumerator += newNumerator1 + newNumerator2;
					int newDenominator = denominator1;
					answer = newNumerator + "/" + newDenominator;
				} else {
					newNumerator += (newNumerator1 + newNumerator2);
					answer = newNumerator + "/" + denominator1;
				}
			}
		}
		// multiplying
		if (operator.indexOf("*") != -1) {
			// whole numbers
			if (numerator1 == 0 && numerator2 == 0 && denominator1 == 1 && denominator2 == 1) {
				answer = "" + (whole1 * whole2);
			}
			// one fraction, one mixed
			else if ((numerator1 == 0 && denominator1 == 1) || (numerator2 == 0 && denominator2 == 1)) {
				if (numerator1 == 0 && denominator1 == 1) {
					int newNum2 = 0;
					if (whole2 < 0) {
						newNum2 += (whole2 * denominator2) - numerator2;
						newNum2 *= whole1;
					} else {
						newNum2 += (whole2 * denominator2) + numerator2;
						newNum2 *= whole1;
					}
					answer = newNum2 + "/" + denominator2;
				} else if (numerator2 == 0 && denominator2 == 1) {
					int newNum1 = 0;
					if (whole1 < 0) {
						newNum1 += (whole1 * denominator1) - numerator1;
						newNum1 *= whole2;
					} else {
						newNum1 = (whole1 * denominator1) + numerator1;
						newNum1 *= whole2;
					}
					answer = newNum1 + "/" + denominator1;
				}
			} else {
				int newNum1 = 0;
				int newNum2 = 0;
				if (whole1 < 0) {
					newNum1 += (whole1 * denominator1) - numerator1;
				} else {
					newNum1 += (whole1 * denominator1) + numerator1;
				}
				if (whole2 < 0) {
					newNum2 += (whole2 * denominator2) - numerator2;
				} else {
					newNum2 += (whole2 * denominator2) + numerator2;
				}

				answer = (newNum1 * newNum2) + "/" + (denominator1 * denominator2);
			}
		}
		// dividing
		if (operator.indexOf("/") != -1) {
			if (numerator1 == 0 && numerator2 == 0 && denominator1 == 1 && denominator2 == 1) {
				int wholeDivided = whole1 / whole2;
				int wholeRemainder = Math.abs(whole1 % whole2);
				System.out.println(wholeRemainder);
				if (wholeRemainder == 0) {
					answer = wholeDivided + "";
				} else {
					answer = wholeDivided + "_" + Math.abs(wholeRemainder) + "/" + Math.abs(whole2);
				}
				// two fractions
			} else if (whole1 == 0 && whole2 == 0) {
				int numerator2part2 = numerator2;
				numerator2 = denominator2;
				denominator2 = numerator2part2;
				int newNumerator = numerator1 * numerator2;
				int newDenominator = denominator1 * denominator2;
				answer = newNumerator + "/" + newDenominator;
				// fraction divided by a mixed number
			} else if (secondNumber.indexOf("_") != -1 && firstNumber.indexOf("_") == -1
					&& firstNumber.indexOf("/") != -1) {
				int newNum2 = 0;
				if (whole2 < 0) {
					newNum2 += (whole2 * denominator2) - numerator2;
				} else if (whole2 >= 0) {
					newNum2 += (whole2 * denominator2) + numerator2;
				}
				int finalNumerator = numerator1 * denominator2;
				int finalDenominator = newNum2 * denominator1;
				answer = finalNumerator + "/" + finalDenominator;
				// mixed number divided by a fraction
			} else if (firstNumber.indexOf("_") != -1 && secondNumber.indexOf("_") == -1
					&& secondNumber.indexOf("/") != -1) {
				int newNum1 = 0;
				if (whole1 < 0) {
					newNum1 += (whole1 * denominator1) - numerator1;
				} else if (whole1 >= 0) {
					newNum1 += (whole1 * denominator1) + numerator1;
				}
				int finalNumerator = newNum1 * denominator2;
				int finalDenominator = denominator1 * numerator2;
				answer = finalNumerator + "/" + finalDenominator;
			}
			// mixed number divided by a mixed number
			else if (firstNumber.indexOf("_") != -1 && secondNumber.indexOf("_") != -1
					&& secondNumber.indexOf("/") != -1) {
				int newNum1 = 0;
				int newNum2 = 0;
				if (whole1 < 0) {
					newNum1 += (whole1 * denominator1) - numerator1;
				} else if (whole1 >= 0) {
					newNum1 += (whole1 * denominator1) + numerator1;
				}
				if (whole2 < 0) {
					newNum2 += (whole2 * denominator2) - numerator2;
				} else if (whole2 >= 0) {
					newNum2 += (whole2 * denominator2) + numerator2;
				}
				int finalNumerator = newNum1 * denominator2;
				int finalDenominator = denominator1 * newNum2;
				if(finalNumerator < 0 && finalDenominator < 0) {
					answer = Math.abs(finalNumerator) + "/" + Math.abs(finalDenominator);
				} else if(finalDenominator < 0 && finalNumerator > 0) {
					answer = "-" + finalNumerator + "/" + Math.abs(finalDenominator);
				} else {
				answer = finalNumerator + "/" + finalDenominator;
				}
				// whole number divided by a mixed number
			} else if (secondNumber.indexOf("_") != -1 && firstNumber.indexOf("_") == -1
					&& firstNumber.indexOf("/") == -1) {
				int newDenom2 = 0;
				if (whole2 < 0) {
					newDenom2 += (whole2 * denominator2) - numerator2;
				} else if (whole2 >= 0) {
					newDenom2 += (whole2 * denominator2) + numerator2;
				}
				int numerator2part2 = denominator2;
				int finalNumerator = whole1 * numerator2part2;
				answer = finalNumerator + "/" + newDenom2;
				// mixed number divided by a whole number
			} else if (firstNumber.indexOf("_") != -1 && secondNumber.indexOf("_") == -1
					&& secondNumber.indexOf("/") == -1) {
				int newNum1 = 0;
				if (whole1 < 0) {
					newNum1 += (whole1 * denominator1) - numerator1;
				} else if (whole2 >= 0) {
					newNum1 += (whole1 * denominator1) + numerator1;
				}
				int finalDenominator = denominator1 * whole2;
				answer = newNum1 + "/" + finalDenominator;
			}
		}
		if (operator.indexOf("-") != -1) {
			// adding two whole numbers
			if (numerator1 == 0 && numerator2 == 0 && denominator1 == 1 && denominator2 == 1) {
				answer = "" + (whole1 - whole2);
				// adding two fractions
			} else if (whole1 == 0 && whole2 == 0) {
				if (denominator1 != denominator2) {
					int newDenominator = denominator1 * denominator2;
					numerator1 *= denominator2;
					numerator2 *= denominator1;
					int newNumerator = numerator1 - numerator2;
					answer = newNumerator + "/" + newDenominator;
					// System.out.println(answer);
				} else if (denominator1 == denominator2) {
					int newNumerator = numerator1 - numerator2;
					answer = newNumerator + "/" + denominator1;
				}
			} else if (whole1 != 0 || whole2 != 0) {
				if (whole1 == 0) {
					int newNum2 = 0;
					int newNumerator = 0;
					int newDenominator = 0;
					if (whole2 < 0) {
						newNum2 += (whole2 * denominator2) - numerator2;
					} else if (whole2 >= 0) {
						newNum2 += (whole2 * denominator2) + numerator2;
					}
					if (denominator1 != denominator2) {
						int d1 = denominator1;
						int d2 = denominator2;
						denominator2 *= d1;
						denominator1 *= d2;
						numerator1 *= d2;
						newNum2 *= d1;
						newNumerator += numerator1 - newNum2;
						newDenominator += denominator1;
						answer = newNumerator + "/" + newDenominator;
					} else {
						newNumerator += numerator1 - newNum2;
						answer = newNumerator + "/" + denominator1;
					}
				} else if (whole2 == 0) {
					int newNum1 = 0;
					int newNumerator = 0;
					int newDenominator = 0;
					if (whole1 < 0) {
						newNum1 += (whole1 * denominator1) - numerator1;
					} else if (whole1 >= 0) {
						newNum1 += (whole1 * denominator1) + numerator1;
					}
					if (denominator1 != denominator2) {
						int d1 = denominator1;
						int d2 = denominator2;
						denominator2 *= d1;
						denominator1 *= d2;
						numerator2 *= d1;
						newNum1 *= d2;
						newNumerator += newNum1 - numerator2;
						newDenominator += denominator1;
						answer = newNumerator + "/" + newDenominator;
					} else {
						newNumerator += newNum1 - numerator2;
						answer = newNumerator + "/" + denominator1;
					}
				} else if (whole1 != 0 && whole2 != 0) {
					int newNum1 = 0;
					int newNum2 = 0;
					int newNumerator = 0;
					int newDenominator = 0;
					if (whole1 < 0) {
						newNum1 += (whole1 * denominator1) - numerator1;
					} else if (whole1 >= 0) {
						newNum1 += (whole1 * denominator1) + numerator1;
					}
					if (whole2 < 0) {
						newNum2 += (whole2 * denominator2) - numerator2;
					} else if (whole2 >= 0) {
						newNum2 += (whole2 * denominator2) + numerator2;
					}
					if (denominator1 != denominator2) {
						int d1 = denominator1;
						int d2 = denominator2;
						denominator1 *= d2;
						denominator2 *= d1;
						newNum1 *= d2;
						newNum2 *= d1;
						newNumerator += newNum1 - newNum2;
						newDenominator += denominator1;
						answer = newNumerator + "/" + newDenominator;
					} else {
						newNumerator += newNum1 - newNum2;
						newDenominator += denominator1;
						answer = newNumerator + "/" + newDenominator;
					}
				}

			}
		}
		// notes: get rid of 0/x and make 0/0 not an answer
		//System.out.println(answer);
		answer = simplifier(answer);
		return (answer);
	}

	public static int wholeNum1(String firstNumber) {
		int whole1 = 0;
		if ((firstNumber.indexOf('_') != -1)) {
			whole1 = Integer.parseInt(firstNumber.substring(0, firstNumber.indexOf("_")));
		} else if ((firstNumber.indexOf("_") == -1 && firstNumber.indexOf("/") == -1)) {
			whole1 = Integer.parseInt(firstNumber);
		} else if (firstNumber.indexOf('/') != -1 && firstNumber.indexOf("_") == -1) {
			whole1 = 0;
		} else if (firstNumber.indexOf("-") != -1) {
			if (firstNumber.indexOf("_") != -1) {
				whole1 = Integer.parseInt(firstNumber.substring(0, firstNumber.indexOf("_")));
			} else {
				whole1 = Integer.parseInt(firstNumber);
			}
		}
		return whole1;
	}

	public static int numerator1(String firstNumber) {
		int numerator1 = 0;
		if (firstNumber.indexOf('/') != -1 && firstNumber.indexOf("_") != -1) {
			numerator1 = Integer
					.parseInt(firstNumber.substring((firstNumber.indexOf("_") + 1), firstNumber.indexOf('/')));
		} else if (firstNumber.indexOf("/") != -1 && firstNumber.indexOf("_") == -1) {
			numerator1 = Integer.parseInt(firstNumber.substring(0, firstNumber.indexOf("/")));
		} else {
			numerator1 = 0;
		}
		return numerator1;
	}

	public static int denominator1(String firstNumber) {
		int denominator1 = 0;
		if (firstNumber.indexOf('/') != -1) {
			denominator1 = Integer.parseInt(firstNumber.substring((firstNumber.indexOf("/") + 1)));
		} else {
			denominator1 = 1;
		}
		return denominator1;
	}

	public static int gcd(int numerator, int denominator) {
		int gcd = 1;
		int smaller = Math.abs(numerator);
		if (Math.abs(numerator) > Math.abs(denominator)) {
			smaller = denominator;
		}
		if (Math.abs(numerator) == denominator) {
			gcd = Math.abs(numerator);
		} else {
			for (int i = smaller; i >= 2; i--) {
				if (numerator % i == 0 && denominator % i == 0) {
					return i;
				}
			}
		}
		return gcd;
	}

	public static String simplifier(String answer) {
		String finalAnswer = "";
		int whole = wholeNum1(answer);
		int numerator = numerator1(answer);
		int denominator = denominator1(answer);
		if (numerator == 0 && denominator == 1) {
			finalAnswer = whole + "";
		} // fractions
		else if (whole == 0) {
			numerator *= 1.0;
			if (numerator == denominator) {
				finalAnswer = "1";
			} else if (numerator == 0) {
				finalAnswer = "0";
			} else if (Math.abs(numerator) <= denominator) {
				int x = gcd(numerator, denominator);
				if (x != 0) {
					finalAnswer = (numerator / x) + "/" + (denominator / x);
				} else {
					finalAnswer = numerator + "/" + denominator;
				}

			} else if (denominator <= Math.abs(numerator)) {
				whole += numerator / denominator;
				numerator -= (whole * denominator);
				if (numerator == 0) {
					finalAnswer = whole + "";
				} else {
					numerator = Math.abs(numerator);
					int x = gcd(numerator, denominator);
					if (x != 0) {
						finalAnswer = whole + "_" + (numerator / x) + "/" + Math.abs((denominator / x));
					} else {
						finalAnswer = whole + "_" + numerator + "/" + denominator;
					}
				}
			}
		} else if (whole != 0) {
			numerator *= 1.0;
			if (numerator == denominator) {
				finalAnswer = (whole + 1) + "";
			} else if (numerator == 0) {
				finalAnswer = whole + "";
			} else if (Math.abs(numerator) <= denominator) {
				int x = gcd(numerator, denominator);
				if (x != 0) {
					finalAnswer = whole + "_" + (numerator / x) + "/" + (denominator / x);
				} else {
					finalAnswer = whole + "_" + numerator + "/" + denominator;
				}
			} else if (denominator <= Math.abs(numerator)) {
				whole += numerator / denominator;
				numerator -= (whole * denominator);
				numerator = Math.abs(numerator);
				int x = gcd(numerator, denominator);
				if (x != 0) {
					finalAnswer = whole + "_" + (numerator / x) + "/" + (denominator / x);
				} else {
					finalAnswer = whole + "_" + numerator + "/" + denominator;
				}
			}
		}
		System.out.println(finalAnswer);
		return finalAnswer;
	}
}
