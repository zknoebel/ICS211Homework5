
import java.io.FileReader;
import java.util.Scanner; 

/**
 * @author Zachery Knoebel Test class for MyStack and Calculator
 */
public class Main {

	public static void main(String[] args) throws java.io.IOException {
		Calculator calc = new Calculator();
		Scanner fs = new Scanner(new FileReader("src//TestInput"));

		// tests all four operators and one expression with two operands
		System.out.println(calc.calculate("1 1 +"));
		System.out.println(calc.calculate("1 1 -"));
		System.out.println(calc.calculate("1 2 *"));
		System.out.println(calc.calculate("1 2 / 4 +"));
		try {

			// tests an illegal expression to see if the
			// IllegalArgumentException is thrown
			System.out.println(calc.calculate("no the right stuff"));
		} catch (IllegalArgumentException ex) {
			System.out.println("Success!!!");
		}

		System.out.println("");

		// tests the expressions required for the assignment
		while (fs.hasNextLine()) {
			System.out.println(calc.calculate(fs.nextLine()));
		}

		System.out.println("");
		System.out.println("");

		// brings up the user interface
		calc.userInterface();

		fs.close();
	}
}
