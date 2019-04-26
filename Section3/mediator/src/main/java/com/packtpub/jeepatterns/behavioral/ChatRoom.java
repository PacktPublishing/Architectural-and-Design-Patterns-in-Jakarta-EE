package com.packtpub.jeepatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {

	private List<User> users;

	public ChatRoom() {
		this.users = new ArrayList<>();
	}

	@Override
	public void registerUser(User user) {
		this.users.add(user);
	}

	@Override
	public void sendMessage(String message, User user) {
		for (User u : this.users) {
			// Message should not be received by the sender
			if (u != user) {
				u.receive(message);
			}
		}
	}
}