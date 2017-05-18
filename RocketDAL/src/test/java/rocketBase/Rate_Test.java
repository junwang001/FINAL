package rocketBase;

import org.junit.Test;

import junit.framework.Assert;

public class Rate_Test {

	@Test
	public void rateTest() {
		Assert.assertEquals(5, RateDAL.getAllRates().size());
	}

}
