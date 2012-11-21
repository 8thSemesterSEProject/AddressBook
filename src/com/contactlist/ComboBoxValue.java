package com.contactlist;

public class ComboBoxValue {

	private int id;
	private String text;

	public ComboBoxValue(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return text;
	}
}
