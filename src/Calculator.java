import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Calculator {
	private String express;
	private static final String OPERATORS = "+-*/";
	private static final String INTORDOT = "0123456789.";
	private MyStack<String> operandStack;
	private double first;
	private double second;
	private double result;
	private double temp;
	private char[] test;
	private InputStreamReader userInput = new InputStreamReader(System.in);
	private Scanner inputScanner = new Scanner(userInput);
	private boolean operated = false;

	Calculator() {
	}

	/**
	 * calculates a postfix mathematical expression
	 * 
	 * @param expression
	 *            is the expression to be calculated
	 * @return the answer
	 */
	public double calculate(String expression) {
		operandStack = new MyStack<>();

		String[] tokens = expression.split("\\s+");

		for (int i = 0; i < tokens.length; i++) {

			if (isNumber(tokens[i])) {
				operandStack.push(tokens[i]);
				operated = false;
			} else if (isOperator(tokens[i].charAt(0)) && tokens[i].length() == 1) {
				operate(tokens[i].charAt(0));
				operandStack.push(String.valueOf(result));
				operated = true;
			} else {
				throw new IllegalArgumentException("Will not handle charactor:" + tokens[i]);
			}
		}
		try {
			if (operated == true) {
				temp = Double.parseDouble((String) operandStack.pop());
				if (operandStack.empty()) {
					return temp;
				} else {
					throw new IllegalArgumentException("More input required");
				}
			} else {
				throw new IllegalArgumentException("More input required");
			}
		} catch (NumberFormatException ex) {

			throw new IllegalArgumentException("More input required");
		}
	}

	/**
	 * checks to see if 'ch' is +,-,* or /
	 * 
	 * @param ch
	 *            is the char to be evaluated
	 * @return true if ch is +,-,* or /
	 */
	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}

	/**
	 * tests to see if s is a number
	 * 
	 * @param s
	 *            is the string to be evaluated
	 * @return true if s is a number with 1 or less periods
	 */
	private boolean isNumber(String s) {
		test = new char[s.length()];
		test = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			if (!isIntOrDot(test[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checks to see if 'ch' is and integer or a period
	 * 
	 * @param ch
	 *            the single character to be evaluated
	 * @return true if ch is 0-9 or .
	 */
	private boolean isIntOrDot(char ch) {
		return INTORDOT.indexOf(ch) != -1;
	}

	/**
	 * first and second are the operands
	 * 
	 * @param ch
	 *            the operator
	 * @return first operated by second example: first = 1 second = 2 ch = /
	 *         returns 0.5
	 */
	private double operate(char ch) { 
		second = Double.parseDouble((String) operandStack.pop());
		first = Double.parseDouble((String) operandStack.pop());

		switch (ch) {
		case '+': {
			result = first + second;
			break;
		}
		case '-': {
			result = first - second;
			break;
		}
		case '*': {
			result = first * second;
			break;
		}
		case '/': {
			result = first / second;
			break;
		}
		}

		return result;
	}

	/**
	 * displays start message
	 */
	public void startMessage() {
		System.out.println("Please enter a postfix mathematical expression");
		System.out.println("Only characters '0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , . , + , - , * , /' are valid ");
		System.out.println("Between each of the operands and operators provide a space");
		System.out.println("     ex. 14 76 + 999 /");
		System.out.println("when the expression is complete, press enter");
		System.out.println("Enter command 'help' for further instructions");
		System.out.println("To quit, enter command 'exit'");
	}

	/**
	 * displays help message
	 */
	public void helpMessage() {
		System.out.println("\n" + "\n" + " ____" + "\n" + "|HELP|" + "\n" + " ----" + "\n"
				+ "This program is designed to calculate a mathematical expression in postfix form." + "\n"
				+ "The form of mathematical expression that most people are familiar with is the " + "\n"
				+ "infix form. In the infix form the order of operation is determined by rules of" + "\n"
				+ "precedence rather than just token placement. An example of an infix expression" + "\n"
				+ "would be something like this." + "\n" + "" + "\n" + "3 + 54 * (8 - 150) / 4" + "\n" + "" + "\n"
				+ "In this expression we know '8 - 150' is calculated first, because it is in" + "\n"
				+ "parenthesis. '54 * (-142)' is calculated next because it is the first" + "\n"
				+ "multiplication or division symbol. '-7668 / 4 is calculated next because it is" + "\n"
				+ "the next multiplication or division symbol. '3 + -1917' is calculated last " + "\n"
				+ "because addition subtraction have the lowest order of precedence compared to" + "\n"
				+ "parenthesis and multiplication and division. The expression = -1914" + "\n" + "" + "\n"
				+ "In postfix notation the precedence comes from the order in which the tokens are" + "\n"
				+ "placed. The last two operands given before the operator will be operated on by " + "\n"
				+ "the operator. After the operation, the answer is added to the stack of operands." + "\n"
				+ "This means that the above expression could be written in this way if it was in" + "\n"
				+ "postfix notation:" + "\n" + "" + "\n" + "8 150 - 54 * 4 / 3 +" + "\n" + "" + "\n"
				+ "8 150 -    -->  -148" + "\n" + "-148 54 *  -->  -7668" + "\n" + "-7668 4 /  -->  -1917" + "\n"
				+ "-1917 3 +  -->  -1914" + "\n" + "" + "\n"
				+ "In this version of the program entering expressions with negative numbers are" + "\n"
				+ "not yet supported, but can be entered using the form '0 yourNumber -'" + "\n" + "" + "\n"
				+ "-15 32 *" + "\n" + "" + "\n" + "would be entered as" + "\n" + "" + "\n" + "0 15 - 32 *" + "\n"
				+ "----------------------------------------------------------------------------------");
	}

	/**
	 * creates a text based user interface which catches exceptions so that
	 * users can try again if they entered an illegal command/expression
	 */
	public void userInterface() {

		startMessage();
		do {
			try {
				express = inputScanner.nextLine();
				if (express.equals("help")) {
					startMessage();
					helpMessage();
				} else if (!express.equals("exit")) {
					System.out.println("= " + calculate(express));
				}
			} catch (IllegalArgumentException | EmptyStackException ex) {

				System.out.println("Please enter a postfix mathematical expression");
				System.out.println("Enter command 'help' for further instructions");
			}
		} while (!express.equals("exit"));

		System.out.println("Goodbye");
	}
}
