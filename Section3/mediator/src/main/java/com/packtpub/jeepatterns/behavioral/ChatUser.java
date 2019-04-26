package com.packtpub.jeepatterns.behavioral;

public class ChatUser extends User {

	public ChatUser(ChatMediator chatMediator, String name) {
		super(chatMediator, name);
	}

	@Override
	public void send(String message) {
		System.out.println(this.name + " sending message: " + message + "\n");
		mediator.sendMessage(message, this);
	}

	@Override
	public void receive(String message) {
		System.out.println(this.name + " received message: " + message);
	}
}