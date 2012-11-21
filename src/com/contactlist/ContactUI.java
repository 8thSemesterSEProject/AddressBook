package com.contactlist;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ContactUI extends JPanel implements Textable {

	private UIType type;

	private int id;
	private JTextField nametxf;
	private JTextField lastnametxf;
	private JTextField nicknametxf;
	private JTextField birthdaytxf;
	private JTextField mobiletxf;
	private JTextField phonetxf;
	private JTextField emailtxf;
	private JTextField websitetxf;
	private JLabel firstnamelbl;
	private JLabel lastnamelbl;
	private JLabel nichnamelbl;
	private JLabel birhdaylbl;
	private JLabel mobilelbl;
	private JLabel phonelbl;
	private JLabel emailbl;
	private JLabel websitelbl;
	private JLabel addresslbl;
	private JTextField jobtxf;
	private JTextField companytxf;
	private JTextField cmobiletxf;
	private JTextField cphonetxf;
	private JTextField cemailtxf;
	private JTextField cwebsitetxf;
	private JLabel joblbl;
	private JLabel companylbl;
	private JLabel cmobilelbl;
	private JLabel cphonelbl;
	private JLabel cemaillbl;
	private JLabel cwebsitelbl;
	private JLabel caddresslbl;
	private JTextArea companyAddress;
	private JTextArea description;
	private JTabbedPane contacttaps;
	private JPanel controlpnl;
	private JButton savebtn;
	private JButton deletebtn;
	private JComboBox<ComboBoxValue> categorycmb;
	private JLabel categorylbl;
	private JTextArea addresstxf;

	private int currentContactId = -1;

	public ContactUI(UIType type, int id) {
		this();
		this.type = type;
		this.id = id;

		if (this.type.equals(UIType.NEW)) {
			deletebtn.setVisible(false);
			savebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection.executeUpdate("INSERT INTO B8XCYHMAD4TXDLPU.CONTACT "
								+ "(CATEGORY_ID, FIRST_NAME, LAST_NAME, NICK_NAME, BIRTHDAY, ADDRESS, MOBILE, PHONE, EMAIL, "
								+ "WEBSITE, JOB_TITLE, COMPANY, COMPANY_ADDRESS, COMPANY_MOBILE, COMPANY_PHONE, COMPANY_EMAIL, "
								+ "COMPANY_WEBSITE, DESCRIPTION) " + "VALUES ("
								+ ((ComboBoxValue) categorycmb
										.getSelectedItem()).getId()
								+ ", '"
								+ nametxf.getText()
								+ "', '"
								+ lastnametxf.getText()
								+ "', '"
								+ nicknametxf.getText()
								+ "', '"
								+ (birthdaytxf.getText().replaceAll("/", "-"))
								+ "', '"
								+ addresstxf.getText()
								+ "', '"
								+ mobiletxf.getText()
								+ "', '"
								+ phonetxf.getText()
								+ "', '"
								+ emailtxf.getText()
								+ "', '"
								+ websitetxf.getText()
								+ "', '"
								+ jobtxf.getText()
								+ "', '"
								+ companytxf.getText()
								+ "', '"
								+ companyAddress.getText()
								+ "', '"
								+ cmobiletxf.getText()
								+ "', '"
								+ cphonetxf.getText()
								+ "', '"
								+ cemailtxf.getText()
								+ "', '"
								+ cwebsitetxf.getText()
								+ "', '"
								+ description.getText() + "')") == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.successfullSave"), "",
									JOptionPane.INFORMATION_MESSAGE);
							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.unsuccessfullSave"), "",
									JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"contact.unsuccessfullSave"), "",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			});
		}
		if (this.type.equals(UIType.EDIT)) {
			loadContact(this.id);
			savebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection.executeUpdate("UPDATE B8XCYHMAD4TXDLPU.CONTACT "
								+ "SET CATEGORY_ID = "
								+ ((ComboBoxValue) categorycmb
										.getSelectedItem()).getId()
								+ ", FIRST_NAME = '"
								+ nametxf.getText()
								+ "', LAST_NAME = '"
								+ lastnametxf.getText()
								+ "', "
								+ "NICK_NAME = '"
								+ nicknametxf.getText()
								+ "', BIRTHDAY = '"
								+ (birthdaytxf.getText().replaceAll("/", "-"))
								+ "', ADDRESS = '"
								+ addresstxf.getText()
								+ "', MOBILE = '"
								+ mobiletxf.getText()
								+ "', "
								+ "PHONE = '"
								+ phonetxf.getText()
								+ "', EMAIL = '"
								+ emailtxf.getText()
								+ "', WEBSITE = '"
								+ websitetxf.getText()
								+ "', JOB_TITLE = '"
								+ jobtxf.getText()
								+ "', "
								+ "COMPANY = '"
								+ companytxf.getText()
								+ "', COMPANY_ADDRESS = '"
								+ companyAddress.getText()
								+ "', COMPANY_MOBILE = '"
								+ cmobiletxf.getText()
								+ "', "
								+ "COMPANY_PHONE = '"
								+ cphonetxf.getText()
								+ "', COMPANY_EMAIL = '"
								+ cemailtxf.getText()
								+ "', COMPANY_WEBSITE = '"
								+ cwebsitetxf.getText()
								+ "', "
								+ "DESCRIPTION = '"
								+ description.getText()
								+ "' WHERE id = " + currentContactId) == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.editSuccessfullSave"), "",
									JOptionPane.INFORMATION_MESSAGE);
							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.editUnsuccessfullSave"),
									"", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"contact.editUnsuccessfullSave"), "",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			});
			deletebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection
								.executeUpdate("delete from contact where id = "
										+ currentContactId) == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.deleteSuccessfullSave"),
									"", JOptionPane.INFORMATION_MESSAGE);

							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"contact.deleteUnsuccessfullSave"),
									"", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"contact.deleteUnsuccessfullSave"), "",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			});
		} else if (this.type.equals(UIType.VIEW)) {
			savebtn.setVisible(false);
			deletebtn.setVisible(false);
			controlpnl.setVisible(false);
		}
	}

	public ContactUI() {
		setLayout(new BorderLayout(0, 0));

		controlpnl = new JPanel();
		controlpnl.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		FlowLayout fl_controlpnl = (FlowLayout) controlpnl.getLayout();
		fl_controlpnl.setAlignment(FlowLayout.RIGHT);
		add(controlpnl, BorderLayout.SOUTH);

		savebtn = new JButton("save");
		controlpnl.add(savebtn);

		deletebtn = new JButton("delete");
		controlpnl.add(deletebtn);

		contacttaps = new JTabbedPane(JTabbedPane.TOP);
		add(contacttaps, BorderLayout.CENTER);

		JPanel general = new JPanel();
		contacttaps.addTab("general", null, general, null);
		general.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		categorylbl = new JLabel("category : ");
		general.add(categorylbl, "2, 2");

		categorycmb = new JComboBox<ComboBoxValue>();
		general.add(categorycmb, "4, 2, left, default");
		try {
			ResultSet result = DBConnection
					.executeQuery("select id, name from category");
			while (result.next())
				categorycmb.addItem(new ComboBoxValue(result.getInt(1), result
						.getString(2)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		firstnamelbl = new JLabel("first name : ");
		general.add(firstnamelbl, "2, 4, right, default");

		nametxf = new JTextField();
		nametxf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				JTabbedPane tabs = (JTabbedPane) getParent();
				tabs.setTitleAt(
						tabs.getSelectedIndex(),
						LanguageEngine.getLanguage().getBundleText(
								"contact.newContactTabTitle")
								+ " - "
								+ (nametxf.getText().substring(0, nametxf
										.getText().length() > 15 ? 15 : nametxf
										.getText().length())));
			}
		});
		general.add(nametxf, "4, 4, left, default");
		nametxf.setColumns(20);

		lastnamelbl = new JLabel("last name : ");
		general.add(lastnamelbl, "2, 6, right, default");

		lastnametxf = new JTextField();
		lastnametxf.setColumns(20);
		general.add(lastnametxf, "4, 6, left, default");

		nichnamelbl = new JLabel("nick name : ");
		general.add(nichnamelbl, "2, 8, right, default");

		nicknametxf = new JTextField();
		nicknametxf.setColumns(20);
		general.add(nicknametxf, "4, 8, left, default");

		birhdaylbl = new JLabel("birthday : ");
		general.add(birhdaylbl, "2, 10, right, default");

		MaskFormatter birthdayMask = null;
		try {
			birthdayMask = new MaskFormatter("####/##/##");
			birthdayMask.setPlaceholder("0");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		birthdaytxf = new JFormattedTextField(birthdayMask);
		birthdaytxf.setText("1990/01/01");
		birthdaytxf.setColumns(20);
		general.add(birthdaytxf, "4, 10, left, default");

		mobilelbl = new JLabel("mobile : ");
		general.add(mobilelbl, "2, 12, right, default");

		mobiletxf = new JTextField();
		mobiletxf.setColumns(20);
		general.add(mobiletxf, "4, 12, left, default");

		phonelbl = new JLabel("phone : ");
		general.add(phonelbl, "2, 14, right, default");

		phonetxf = new JTextField();
		phonetxf.setColumns(20);
		general.add(phonetxf, "4, 14, left, default");

		emailbl = new JLabel("email : ");
		general.add(emailbl, "2, 16, right, default");

		emailtxf = new JTextField();
		emailtxf.setColumns(20);
		general.add(emailtxf, "4, 16, left, default");

		websitelbl = new JLabel("website : ");
		general.add(websitelbl, "2, 18, right, default");

		websitetxf = new JTextField();
		websitetxf.setColumns(20);
		general.add(websitetxf, "4, 18, left, default");

		addresslbl = new JLabel("address : ");
		general.add(addresslbl, "2, 20, right, top");

		JScrollPane addressscrl = new JScrollPane();
		general.add(addressscrl, "4, 20, left, top");

		addresstxf = new JTextArea();
		addresstxf.setRows(5);
		addresstxf.setColumns(35);
		addressscrl.setViewportView(addresstxf);

		JPanel work = new JPanel();
		contacttaps.addTab("work", null, work, null);
		work.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		joblbl = new JLabel("job title : ");
		work.add(joblbl, "2, 2, right, default");

		jobtxf = new JTextField();
		work.add(jobtxf, "4, 2, left, default");
		jobtxf.setColumns(25);

		companylbl = new JLabel("company : ");
		work.add(companylbl, "2, 4, right, default");

		companytxf = new JTextField();
		companytxf.setColumns(25);
		work.add(companytxf, "4, 4, left, default");

		cmobilelbl = new JLabel("mobile : ");
		work.add(cmobilelbl, "2, 6, right, default");

		cmobiletxf = new JTextField();
		cmobiletxf.setColumns(25);
		work.add(cmobiletxf, "4, 6, left, default");

		cphonelbl = new JLabel("phone : ");
		work.add(cphonelbl, "2, 8, right, default");

		cphonetxf = new JTextField();
		cphonetxf.setColumns(25);
		work.add(cphonetxf, "4, 8, left, default");

		cemaillbl = new JLabel("email : ");
		work.add(cemaillbl, "2, 10, right, default");

		cemailtxf = new JTextField();
		cemailtxf.setColumns(25);
		work.add(cemailtxf, "4, 10, left, default");

		cwebsitelbl = new JLabel("website : ");
		work.add(cwebsitelbl, "2, 12, right, default");

		cwebsitetxf = new JTextField();
		cwebsitetxf.setColumns(25);
		work.add(cwebsitetxf, "4, 12, left, default");

		caddresslbl = new JLabel("addres : ");
		work.add(caddresslbl, "2, 14, right, top");

		JScrollPane companyAddressscrl = new JScrollPane();
		work.add(companyAddressscrl, "4, 14, left, top");

		companyAddress = new JTextArea();
		companyAddress.setRows(5);
		companyAddress.setColumns(35);
		companyAddressscrl.setViewportView(companyAddress);

		JPanel note = new JPanel();
		contacttaps.addTab("note", null, note, null);
		note.setLayout(new BorderLayout(0, 0));

		JScrollPane descriptionscrl = new JScrollPane();
		note.add(descriptionscrl, BorderLayout.CENTER);

		description = new JTextArea();
		description.setFont(UIManager.getFont("TextField.font"));
		description.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		descriptionscrl.setViewportView(description);

		LanguageEngine.registerUI(this);
	}

	private void loadContact(int id) {
		currentContactId = id;

		try {
			ResultSet result = DBConnection
					.executeQuery("SELECT CATEGORY_ID, FIRST_NAME, LAST_NAME, "
							+ "NICK_NAME, BIRTHDAY, ADDRESS, MOBILE, PHONE, EMAIL, "
							+ "WEBSITE, JOB_TITLE, COMPANY, COMPANY_ADDRESS, COMPANY_MOBILE, "
							+ "COMPANY_PHONE, COMPANY_EMAIL, COMPANY_WEBSITE, DESCRIPTION "
							+ "FROM B8XCYHMAD4TXDLPU.CONTACT WHERE ID = "
							+ currentContactId);
			if (result.next()) {
				int category = result.getInt(1);
				for (int i = 0; i < categorycmb.getItemCount(); i++)
					if (category == categorycmb.getItemAt(i).getId()) {
						categorycmb.setSelectedIndex(i);
						break;
					}

				nametxf.setText(result.getString(2));
				lastnametxf.setText(result.getString(3));
				nicknametxf.setText(result.getString(4));
				birthdaytxf.setText(result.getString(5).replaceAll("-", "/"));
				addresstxf.setText(result.getString(6));
				mobiletxf.setText(result.getString(7));
				phonetxf.setText(result.getString(8));
				emailtxf.setText(result.getString(9));
				websitetxf.setText(result.getString(10));
				jobtxf.setText(result.getString(11));
				companytxf.setText(result.getString(12));
				companyAddress.setText(result.getString(13));
				cmobiletxf.setText(result.getString(14));
				cphonetxf.setText(result.getString(15));
				cemailtxf.setText(result.getString(16));
				cwebsitetxf.setText(result.getString(17));
				description.setText(result.getString(18));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateText() {
		Language lang = LanguageEngine.getLanguage();
		firstnamelbl.setText(lang.getBundleText("contact.firstname"));
		lastnamelbl.setText(lang.getBundleText("contact.lastname"));
		nichnamelbl.setText(lang.getBundleText("contact.nickname"));
		birhdaylbl.setText(lang.getBundleText("contact.birthday"));
		mobilelbl.setText(lang.getBundleText("contact.mobile"));
		phonelbl.setText(lang.getBundleText("contact.phone"));
		emailbl.setText(lang.getBundleText("contact.email"));
		websitelbl.setText(lang.getBundleText("contact.website"));
		addresslbl.setText(lang.getBundleText("contact.address"));
		joblbl.setText(lang.getBundleText("contact.job"));
		companylbl.setText(lang.getBundleText("contact.company"));
		cmobilelbl.setText(lang.getBundleText("contact.cmobile"));
		cphonelbl.setText(lang.getBundleText("contact.cphone"));
		cemaillbl.setText(lang.getBundleText("contact.cemail"));
		cwebsitelbl.setText(lang.getBundleText("contact.cwebsite"));
		caddresslbl.setText(lang.getBundleText("contact.caddress"));
		contacttaps.setTitleAt(0, lang.getBundleText("contact.general"));
		contacttaps.setTitleAt(1, lang.getBundleText("contact.work"));
		contacttaps.setTitleAt(2, lang.getBundleText("contact.note"));
		savebtn.setText(lang.getBundleText("contact.save"));
		deletebtn.setText(lang.getBundleText("contact.delete"));
		categorylbl.setText(lang.getBundleText("contact.category"));
	}

}
