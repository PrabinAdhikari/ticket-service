package service;

import org.junit.Assert;
import org.junit.Test;

public class PrabinsServiceTest {
	@Test
	public void giveMeIntegerTest() {
		Assert.assertEquals(27, new PrabinsService().getInteger());
	}
}
