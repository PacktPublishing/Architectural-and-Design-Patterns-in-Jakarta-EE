package com.packtpub.jeepatterns.behavioral;

public interface Context {
	void request();
	void setState(State state);
	State getState();
}
