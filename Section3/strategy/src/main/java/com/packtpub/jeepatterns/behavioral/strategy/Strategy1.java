package com.packtpub.jeepatterns.behavioral.strategy;

	// First declaration
	@Implementation
	public class Strategy1 implements Strategy {
	 
	    public void execute() {
	        System.out.println("first");
	    }
	}