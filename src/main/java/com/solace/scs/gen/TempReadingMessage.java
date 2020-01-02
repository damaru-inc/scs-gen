
package com.solace.scs.gen;
import java.util.HashMap;

public class TempReadingMessage { 



	// Payload


	private TempReading tempReading;

	public TempReading getPayload() {
		return tempReading;
	}

	public TempReadingMessage setPayload(TempReading tempReading) {
		this.tempReading = tempReading;
		return this;
	}

	// Listers

	public interface SubscribeListener {
		public void onReceive(TempReadingMessage tempReadingMessage);
		public void handleException(Exception exception);
	}
}
