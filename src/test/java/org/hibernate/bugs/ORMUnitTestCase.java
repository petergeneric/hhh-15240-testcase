/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.bugs;

import org.hibernate.cfg.Configuration;
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.junit.Test;

public class ORMUnitTestCase extends BaseCoreFunctionalTestCase
{
	@Override
	protected Class[] getAnnotatedClasses()
	{
		return new Class[]{Foo.class};
	}


	@Override
	protected void configure(Configuration configuration)
	{
		super.configure(configuration);

		// Register user type for HHH-15240. N.B. if we don't do this then we just get a NullPointerException as part of the assert since legacyType is null
		configuration.registerTypeOverride(new MyVersionTypeHibernateUserVersionType(),
		                                   new String[]{MyVersionType.class.getName()});
	}


	@Test
	public void hhh15240Test() throws Exception
	{
		// N.B. exception occurs during setup before getting to this unit test method
	}
}
