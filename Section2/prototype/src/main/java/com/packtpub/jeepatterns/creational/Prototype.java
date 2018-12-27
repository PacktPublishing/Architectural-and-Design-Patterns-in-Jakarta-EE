package com.packtpub.jeepatterns.creational;

public interface Prototype extends Cloneable {
	public Prototype clone() throws CloneNotSupportedException;
}
