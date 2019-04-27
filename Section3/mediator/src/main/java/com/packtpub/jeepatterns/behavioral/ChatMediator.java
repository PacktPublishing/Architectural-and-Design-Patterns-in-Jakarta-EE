package com.packtpub.jeepatterns.behavioral;

/**
 * The Mediator
 *
 * @author Werner Keil
 */
public interface ChatMediator
{
  public void sendMessage( String msg, User user );
  void registerUser( User user );
}
