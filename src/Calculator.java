
public class Calculator {
  private String express;
  private static final String OPERATORS = "+-*/";
  private static final String INTORDOT = "0123456789.";
  private MyStack operandStack;
  private double first;
  private double second;
  private double result;
  private char[] test;


  Calculator() {
  }


  public double calculate(String expression) {
    operandStack = new MyStack<>();

    String[] tokens = expression.split("\\s+");


    for(int i = 0; i < tokens.length; i ++){
  
        if(isNumber(tokens[i])){
          operandStack.push(tokens[i]);
        }else if(isOperator(tokens[i].charAt(0)) && tokens[i].length() == 1){
          operate(tokens[i].charAt(0));
          operandStack.push(String.valueOf(result));
        }else{
          throw new IllegalArgumentException("Will not handle charactor:" + tokens[i]);
        }
      }
    try{
    return Double.parseDouble((String) operandStack.pop());
    }
    catch(NumberFormatException ex){

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
  }
