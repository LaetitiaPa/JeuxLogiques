package com.openclassrooms.game;

import java.util.ArrayList;

public class Test {

	   public static void main(String[] args) {
			 ArrayList<Integer> t1 = new ArrayList<Integer>();
			 ArrayList<Integer> t2 = new ArrayList<Integer>();
			 String resultT2 = "";
			 String resultT1 = "";
			 
			 t1.add(122456);
			 t2.add(1);
			 t2.add(2);
			 t2.add(3);
			 t2.add(4);
			 t2.add(5);
			 t2.add(6);
			 
			 for (int value : t2) 
			 { 
		
			     resultT2 = resultT2 + value;
			 }
			 System.out.println(resultT2);
			 resultT1 = t1.get(0).toString();
			 System.out.println(resultT1);
			 
			 if (resultT2.equals(resultT1)) {
				 System.out.println("Vous avez gagné");
			 } else {
				 System.out.println("Vous avez perdu");
			 }
	    }

}
