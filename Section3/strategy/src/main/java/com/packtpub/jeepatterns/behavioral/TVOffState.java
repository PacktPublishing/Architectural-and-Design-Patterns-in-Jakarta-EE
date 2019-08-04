package com.packtpub.jeepatterns.behavioral;

public class TVOffState extends State {

	@Override
	public void handle() {
		System.out.println("The TV is OFF");
	}
}
