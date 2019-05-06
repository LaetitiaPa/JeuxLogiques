package com.openclassrooms.game;

import java.util.Arrays;

public class test {
	
	public static boolean isSymbol;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 //List<String> symbols = Arrays.asList("+", "-", "=");
		    
		 //ArrayList<String> playerSymbols = new ArrayList<String>();
		 
		String[] symbols = new String[] {"+", "-", "="};
		String[] playerSymbols;
		
		
		
		String prop = "+,=,+";
		
		playerSymbols = prop.split(",");
		System.out.println(Arrays.toString(playerSymbols));
		 int digits = 3;

		 for (int i = 0; i < symbols.length; i++) {
			 
			 isSymbol = false;
			 System.out.println(playerSymbols[i]);
			 if (playerSymbols[i].equals("=") || playerSymbols[i].equals("+") || playerSymbols[i].equals("-") ) {
				 isSymbol = true;
			 }
		 }
		 System.out.println(isSymbol);
	}

}
