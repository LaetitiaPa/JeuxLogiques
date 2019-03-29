package com.openclassrooms.game;

public class Debug {
	static void print(String msg) {
		if (Config.getValue("debug").equals("true")) {
			System.out.println(msg);
		}
	}
}
