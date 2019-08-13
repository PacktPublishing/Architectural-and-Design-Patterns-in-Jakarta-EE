package com.packtpub.jeepatterns.behavioral.strategy;

	// First declaration
	@Implementation
	public class Strategy1 implements Strategy {
	 
	    public String execute() {
	        return "first";
	    }
	}