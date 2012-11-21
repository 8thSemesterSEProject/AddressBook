package com.contactlist;

import java.util.ResourceBundle;

public enum Language {
	DARI("دری", "DariLangPack"), ENGLISH("English", "EnglishLangPack");

	private String languageName;
	private ResourceBundle textBundle;

	private Language(String languageName, String language) {
		this.languageName = languageName;
		textBundle = ResourceBundle.getBundle("com.contactlist.langpack."
				+ language);
	}

	public String getBundleText(String key) {
		return textBundle.getString(key);
	}

	@Override
	public String toString() {
		return languageName;
	}
}
