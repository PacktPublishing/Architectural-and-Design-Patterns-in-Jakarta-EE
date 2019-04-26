package com.packtpub.jeepatterns.behavioral;

public interface ChatMediator
{
  public void sendMessage( String msg, User user );
  void registerUser( User user );
}
