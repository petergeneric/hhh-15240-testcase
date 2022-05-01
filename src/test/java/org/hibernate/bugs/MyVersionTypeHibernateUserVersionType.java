package org.hibernate.bugs;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserVersionType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MyVersionTypeHibernateUserVersionType implements UserVersionType<MyVersionType>
{
	@Override
	public int getSqlType()
	{
		return Types.BIGINT;
	}


	@Override
	public Class<MyVersionType> returnedClass()
	{
		return MyVersionType.class;
	}


	@Override
	public boolean equals(MyVersionType x, MyVersionType y) throws HibernateException
	{
		if (x == y)
		{
			return true;
		}
		else if (x == null || y == null)
		{
			return false;
		}
		else
		{
			return x.equals(y);
		}
	}


	@Override
	public int hashCode(final MyVersionType x) throws HibernateException
	{
		return x.hashCode();
	}


	@Override
	public MyVersionType nullSafeGet(final ResultSet resultSet,
	                                 final int position,
	                                 final SharedSessionContractImplementor session,
	                                 final Object owner) throws HibernateException, SQLException
	{
		final long encoded = resultSet.getLong(position);

		if (resultSet.wasNull())
			return null;
		else
		{
			return new MyVersionType(encoded);
		}
	}


	@Override
	public void nullSafeSet(final PreparedStatement statement,
	                        final MyVersionType value,
	                        final int index,
	                        final SharedSessionContractImplementor session) throws HibernateException, SQLException
	{
		if (value == null)
		{
			statement.setNull(index, Types.BIGINT);
		}
		else
		{
			final long millis = value.toLong();

			statement.setLong(index, millis);
		}
	}


	@Override
	public MyVersionType deepCopy(MyVersionType value) throws HibernateException
	{
		return value; // immutable type
	}


	@Override
	public boolean isMutable()
	{
		return false;
	}


	@Override
	public Serializable disassemble(final MyVersionType value)
	{
		return value;
	}


	@Override
	public MyVersionType assemble(final Serializable cached, final Object owner) throws HibernateException
	{
		if (cached == null)
			return null;
		else
			return (MyVersionType) cached;
	}


	@Override
	public MyVersionType replace(final MyVersionType original,
	                             final MyVersionType target,
	                             final Object owner) throws HibernateException
	{
		return original;
	}


	@Override
	public MyVersionType seed(final SharedSessionContractImplementor session)
	{
		return new MyVersionType(System.currentTimeMillis());
	}


	@Override
	public MyVersionType next(final MyVersionType current, final SharedSessionContractImplementor session)
	{
		return seed(session);
	}


	@Override
	public int compare(final MyVersionType a, final MyVersionType b)
	{
		return Long.compare(a.toLong(), b.toLong());
	}
}
