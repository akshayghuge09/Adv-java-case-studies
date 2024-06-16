package com.Model;

import java.sql.Date;


public class Coin {
	private int sr_no;
	private String countryName;
	private float denomination;
	private int yearOfMinting;
	private double currentValue;
	private Date acuquiredDate;

	public Coin() {
		// TODO Auto-generated constructor stub
	}

	public Coin(int sr_no, String countryName, float denomination, int yearOfMinting, double currentValue, Date date) {
		super();
		this.sr_no = sr_no;
		this.countryName = countryName;
		this.denomination = denomination;
		this.yearOfMinting = yearOfMinting;
		this.currentValue = currentValue;
		this.acuquiredDate = date;
	}

	public int getSr_no() {
		return sr_no;
	}

	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public float getDenomination() {
		return denomination;
	}

	public void setDenomination(float denomination) {
		this.denomination = denomination;
	}

	public int getYearOfMinting() {
		return yearOfMinting;
	}

	public void setYearOfMinting(int yearOfMinting) {
		this.yearOfMinting = yearOfMinting;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public Date getAcuquiredDate() {
		return acuquiredDate;
	}

	public void setAcuquiredDate(Date date) {
		this.acuquiredDate = date;
	}

}
