package com.packtpub.jeepatterns.behavioral;

public class TVRemoteContext implements Context {

	private State tvState;
	
	@Override
	public void setState(State state) {
		tvState = state;
	}

	@Override
	public State getState() {
		return tvState;
	}
	
	@Override
	public void request() {
		tvState.handle();
	}

}
