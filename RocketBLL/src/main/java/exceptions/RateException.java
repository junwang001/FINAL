package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	private RateDomainModel rateDomainModel;

	public RateException(String message, RateDomainModel rateDomainModel) {
		super();
		this.message = message;
		this.rateDomainModel = rateDomainModel;
	}

	public RateDomainModel getRateDomainModel() {
		return rateDomainModel;
	}

	public String getMessage() {
		return message;
	}

}
