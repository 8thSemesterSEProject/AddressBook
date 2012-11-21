package com.contactlist;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ContactList extends JFrame implements Textable {

	private JMenu fileMenu;
	private JMenu viewMenu;
	private JMenu settingMenu;
	private JMenuItem addContactbtn;
	private JMenuItem addCategorybtn;
	private JMenuItem importbtn;
	private JMenuItem exportbtn;
	private JMenuItem exitbtn;
	private JMenuItem listContactsbtn;
	private JMenuItem listCategoriesbtn;
	private JMenuItem advanceSearchbtn;
	private JMenu themlst;
	private JMenu langlst;
	private JMenuItem changePasswordbtn;
	private JMenuItem englishbtn;
	private JMenuItem daribtn;
	private JMenuItem classicbtn;
	private JMenuItem windowsbtn;
	private JTabbedPane content;

	public ContactList() {
		setSize(new Dimension(600, 510));
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);

		fileMenu = new JMenu("file");
		menu.add(fileMenu);

		addContactbtn = new JMenuItem("add contact");
		addContactbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				content.addTab(
						LanguageEngine.getLanguage().getBundleText(
								"contact.newContactTabTitle"), new ContactUI(
								UIType.NEW, -1));
			}
		});
		fileMenu.add(addContactbtn);

		addCategorybtn = new JMenuItem("add category");
		addCategorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content.addTab(
						LanguageEngine.getLanguage().getBundleText(
								"category.newCategoryTabTitle"),
						new CategoryUI(UIType.NEW, -1));
			}
		});
		fileMenu.add(addCategorybtn);

		importbtn = new JMenuItem("import");
		fileMenu.add(importbtn);

		exportbtn = new JMenuItem("export");
		fileMenu.add(exportbtn);

		exitbtn = new JMenuItem("exit");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitbtn);

		viewMenu = new JMenu("view");
		menu.add(viewMenu);

		listContactsbtn = new JMenuItem("list contacts");
		listContactsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content.addTab(
						LanguageEngine.getLanguage().getBundleText(
								"listcontact.listContactTabTitle"),
						new ListContacts());
			}
		});
		viewMenu.add(listContactsbtn);

		listCategoriesbtn = new JMenuItem("list categories");
		listCategoriesbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content.addTab(
						LanguageEngine.getLanguage().getBundleText(
								"listcategories.listCategoryTabTitle"),
						new ListCategories());
			}
		});
		viewMenu.add(listCategoriesbtn);

		advanceSearchbtn = new JMenuItem("advance seach");
		advanceSearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content.addTab(
						LanguageEngine.getLanguage().getBundleText(
								"listcategories.listAdvanceContactTabTitle"),
						new AdvanceSearchListContacts());
			}
		});
		viewMenu.add(advanceSearchbtn);

		settingMenu = new JMenu("setting");
		menu.add(settingMenu);

		langlst = new JMenu("language");
		settingMenu.add(langlst);

		englishbtn = new JMenuItem("english");
		englishbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LanguageEngine.setLanguage(Language.ENGLISH);
			}
		});
		langlst.add(englishbtn);

		daribtn = new JMenuItem("dari");
		daribtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LanguageEngine.setLanguage(Language.DARI);
			}
		});
		langlst.add(daribtn);

		themlst = new JMenu("theme");
		settingMenu.add(themlst);

		classicbtn = new JMenuItem("classic");
		classicbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());

					SwingUtilities.updateComponentTreeUI(ContactList.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		themlst.add(classicbtn);

		windowsbtn = new JMenuItem("windows");
		windowsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());

					SwingUtilities.updateComponentTreeUI(ContactList.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		themlst.add(windowsbtn);

		changePasswordbtn = new JMenuItem("change password");
		settingMenu.add(changePasswordbtn);

		Component horizontalGlue = Box.createHorizontalGlue();
		menu.add(horizontalGlue);

		JButton helpbtn = new JButton("?");
		helpbtn.setFocusable(false);
		helpbtn.setMargin(new Insets(2, 8, 2, 8));
		menu.add(helpbtn);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		LanguageEngine.registerUI(this);

		content = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(content, BorderLayout.CENTER);
	}

	@Override
	public void updateText() {
		Language lang = LanguageEngine.getLanguage();
		fileMenu.setText(lang.getBundleText("contactList.fileMenu"));
		viewMenu.setText(lang.getBundleText("contactList.viewMenu"));
		settingMenu.setText(lang.getBundleText("contactList.settingMenu"));
		addContactbtn.setText(lang.getBundleText("contactList.addContact"));
		addCategorybtn.setText(lang.getBundleText("contactList.addCategory"));
		importbtn.setText(lang.getBundleText("contactList.import"));
		exportbtn.setText(lang.getBundleText("contactList.export"));
		exitbtn.setText(lang.getBundleText("contactList.exit"));
		listContactsbtn.setText(lang.getBundleText("contactList.listContacts"));
		listCategoriesbtn.setText(lang
				.getBundleText("contactList.listCategories"));
		advanceSearchbtn.setText(lang
				.getBundleText("contactList.advanceSearch"));
		langlst.setText(lang.getBundleText("contactList.langlst"));
		daribtn.setText(lang.getBundleText("contactList.dari"));
		englishbtn.setText(lang.getBundleText("contactList.english"));
		classicbtn.setText(lang.getBundleText("contactList.classic"));
		windowsbtn.setText(lang.getBundleText("contactList.windows"));
		themlst.setText(lang.getBundleText("contactList.themelst"));
		changePasswordbtn.setText(lang
				.getBundleText("contactList.changePassword"));
	}

}