package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;
import util.NumberUtil;


public class RocketHub extends Hub {

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");

		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;

			try {
				double rate = RateBLL.getRate(lq.getiCreditScore());
				lq.setdRate(rate);
				lq.setdPayment(NumberUtil.format(RateBLL.getPayment(rate/100/12, lq.getiTerm() * 12, -lq.getiDownPayment(), 0, false)));
			} catch (RateException e) {
				e.printStackTrace();
			}
			sendToAll(lq);
		}
	}

}
