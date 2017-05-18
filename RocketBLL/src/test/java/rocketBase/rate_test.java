package rocketBase;

import org.junit.Assert;
import org.junit.Test;

import exceptions.RateException;
import util.NumberUtil;

public class rate_test {

	@Test
	public void rateTest() {
		try {
			Assert.assertEquals(6, RateBLL.getRate(700));
		} catch (RateException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals("1432.25", String.valueOf(NumberUtil.format(RateBLL.getPayment(0.04/12, 360, -300_000, 0, false))));
	}
}
