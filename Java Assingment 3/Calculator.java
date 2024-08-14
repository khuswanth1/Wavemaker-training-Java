package com.pack1;
import java.util.Scanner;
public class Calculator 
{
	static  Scanner scanner = new Scanner(System.in);
	void meth1() {
		System.out.println("Enter the 1st No:");
		float num1= scanner.nextFloat();
		System.out.println("Enter the operator (+,-,*,/):");
		char operator = scanner.next().charAt(0);
		System.out.println("Enter the 2nd No:");
		float num2 = scanner.nextFloat();
		double result;
		switch(operator) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			if(num2 != 0) {
				result = num1 / num2;
			}
			else
			{
				System.out.println("Divide with 0 is not allowed");
				return;
			}
				break;
				default:
					System.out.println("Invalid Input Check properly");
					return;
					
		}
		System.out.println("enter the result is:"+result);
	}
	void meth2() {

	}
	public static void main(String[] args) {
		System.out.println("Start");
		Calculator obj = new Calculator();
		obj.meth1();
		System.out.println("End");
	}
	

}
