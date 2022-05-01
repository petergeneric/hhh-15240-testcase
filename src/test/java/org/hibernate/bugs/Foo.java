package org.hibernate.bugs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity(name = "foo")
public class Foo
{
	@Id
	public int id;

	@Version
	@Column(name = "revision")
	MyVersionType version;
}
