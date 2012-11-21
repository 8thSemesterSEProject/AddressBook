package com.contactlist;

import java.util.ArrayList;

public class LanguageEngine {

	private static Language language = Language.ENGLISH;
	private static ArrayList<Textable> textableUIs = new ArrayList<Textable>();

	public static void setLanguage(Language language) {
		LanguageEngine.language = language;

		refreshUITexts();
	}

	public static Language getLanguage() {
		return language;
	}

	public static void registerUI(Textable textableUI) {
		textableUIs.add(textableUI);
		textableUI.updateText();
	} 

	public static void unregisterUI(Textable textableUI) {
		textableUIs.remove(textableUI);
	}

	private static void refreshUITexts() {
		for (Textable textableUI : textableUIs)
			textableUI.updateText();
	}
}
