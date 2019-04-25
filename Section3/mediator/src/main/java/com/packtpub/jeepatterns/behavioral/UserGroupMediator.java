package com.packtpub.jeepatterns.behavioral;

public interface UserGroupMediator
{

  public void sendMessage( String msg, User user );

  void registerUser( User user );
}
