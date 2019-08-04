package com.packtpub.jeepatterns.behavioral;

public class TVStandbyState extends State {

	@Override
	public void handle() {
		System.out.println("The TV is STANDBY");
	}
}
