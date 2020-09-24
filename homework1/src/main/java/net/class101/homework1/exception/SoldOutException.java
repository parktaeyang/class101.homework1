package net.class101.homework1.exception;

public class SoldOutException extends Exception{
	private static final long serialVersionUID = 1L;

	public SoldOutException(){
		super();
	}
	
	public SoldOutException(String msg){
		super(msg);
	}
}
