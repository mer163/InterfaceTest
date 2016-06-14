package jhss.test.inter.testcases;

import java.util.Scanner;

public class Welcome {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("what's your name?");
		String name = input.next();
		System.out.println("hello,"+ name +" welcome!");
		

	}

}
