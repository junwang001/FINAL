package rocketBase;

import java.util.ArrayList;
import java.util.OptionalDouble;

import org.apache.poi.ss.formula.functions.FinanceLib;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	public static double getRate(int GivenCreditScore) throws RateException
	{
		double dInterestRate = 0;

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();

		OptionalDouble rateOptional = rates.stream()
			.filter(rate -> rate.getiMinCreditScore() <= GivenCreditScore)
			.mapToDouble(RateDomainModel::getdInterestRate).min();

		if(!rateOptional.isPresent()) {
			throw new RateException("no rate is found", null);
		}

		dInterestRate = rateOptional.getAsDouble();

		return dInterestRate;


	}

	public static double getPayment(double r, double n, double p, double f, boolean t)
	{
		return FinanceLib.pmt(r, n, p, f, t);
	}

}
