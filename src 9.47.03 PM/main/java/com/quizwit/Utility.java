package com.quizwit;

public class Utility {
	public static boolean isNumeric(String str) {
		if(str == null)
			return false;
		try {
			Double.parseDouble(str);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean onlyDigits(String str) {
		return str.matches("[0-9]+");
	}
	
	public static boolean validateEmail(String str) {
		return str.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
	}
	
	public static Double roundOff(Double num) {
		num = num*100 + 0.5;
		Integer temp = num.intValue();
		num = temp/100.00;
		return num;
	}
}