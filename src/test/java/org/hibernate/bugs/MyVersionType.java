package org.hibernate.bugs;

import java.io.Serializable;

public class MyVersionType implements Serializable
{
	private static final long serialVersionUID = 42L;
	
	private final long l;


	public MyVersionType(long l)
	{
		this.l = l;
	}


	public long toLong()
	{
		return l;
	}
}
