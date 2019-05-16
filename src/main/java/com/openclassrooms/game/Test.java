package com.openclassrooms.game;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main( String args[] ) {
		// String to be scanned to find the pattern.
		//String line = "===4"; // kO
		String line = "=+-="; //OK
  

		String pattern = "([=+-]{4})";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		
		if (m.find( )) {
			System.out.println("GOOD ");
		}else {
			System.out.println("NO MATCH");
		}  
			  
	}

}


