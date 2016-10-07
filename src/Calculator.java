import java.io.InputStreamReader;
import java.util.Scanner;

public class Calculator {
  private String express;
  private static final String OPERATORS = "+-*/";
  private static final String INTORDOT = "0123456789.";
  private MyStack operandStack;
  private double first;
  private double second;
  private double result;
  private char[] test;
  private InputStreamReader userInput = new InputStreamReader(System.in);
  private Scanner inputScanner = new Scanner(userInput);


  Calculator() {
  }


  public double calculate(String expression) {
    operandStack = new MyStack<>();

    String[] tokens = expression.split("\\s+");

    for (int i = 0; i < tokens.length; i++) {

      if (isNumber(tokens[i])) {
        operandStack.push(tokens[i]);
      }
      else if (isOperator(tokens[i].charAt(0)) && tokens[i].length() == 1) {
        operate(tokens[i].charAt(0));
        operandStack.push(String.valueOf(result));
      }
      else {
        throw new IllegalArgumentException("Will not handle charactor:" + tokens[i]);
      }
    }
    try {
      return Double.parseDouble((String) operandStack.pop());
    }
    catch (NumberFormatException ex) {

      throw new IllegalArgumentException("More input required");
    }
  }


  private boolean isOperator(char ch) {
    return OPERATORS.indexOf(ch) != -1;
  }


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


  private boolean isIntOrDot(char ch) {
    return INTORDOT.indexOf(ch) != -1;
  }


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


  public void userInterface() {
    System.out.println("Please enter a postfix mathmatical expression");
    System.out.println("Only characters '0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , . , + , - , * , /' are valid ");
    System.out.println("Between each of the operands and operators provide a space");
    System.out.println("     ex. 14 76 + 999 /");
    System.out.println("when the expression is complete, press enter");
    System.out.println("To quit, enter command 'exit'");
    do {
      express = inputScanner.nextLine();
      if(!express.equals("exit")){
      System.out.println("= " + calculate(express));
    }
    }
    while (!express.equals("exit"));
    
    System.out.println("Goodbye");
  }
}
