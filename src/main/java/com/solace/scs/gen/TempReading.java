
package com.solace.scs.gen;


public  class TempReading {

	private String sensorId;
	private Double temperature;
	private BaseUnit baseUnit;

	public String getSensorId() {
		return sensorId;
	}

	public TempReading setSensorId(String sensorId) {
		this.sensorId = sensorId;
		return this;
	}

	public Double getTemperature() {
		return temperature;
	}

	public TempReading setTemperature(Double temperature) {
		this.temperature = temperature;
		return this;
	}

	public BaseUnit getBaseUnit() {
		return baseUnit;
	}

	public TempReading setBaseUnit(BaseUnit baseUnit) {
		this.baseUnit = baseUnit;
		return this;
	}

	public static enum BaseUnit { CELSIUS,FAHRENHEIT }

}

