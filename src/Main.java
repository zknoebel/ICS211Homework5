import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.Scanner;


public class Main {

	public static void main(String[] args)throws java.io.IOException{
		Calculator calc = new Calculator();
    Scanner fs = new Scanner(new FileReader("src//TestInput"));
		
		
		
		System.out.println(calc.calculate("1 1 +"));
    System.out.println(calc.calculate("1 1 -"));
    System.out.println(calc.calculate("1 2 *"));
    System.out.println(calc.calculate("1 2 / 4 +"));
    try{

      System.out.println(calc.calculate("no the right stuff"));
    }
    catch(IllegalArgumentException ex){
      System.out.println("Success!!!");
    }
    
    System.out.println("");
    
    while(fs.hasNextLine()){
      System.out.println(calc.calculate(fs.nextLine()));
    }

    System.out.println("");
    System.out.println("");
    calc.userInterface();
    
    fs.close();
	}
}
