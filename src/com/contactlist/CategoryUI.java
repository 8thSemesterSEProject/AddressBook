package com.contactlist;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class CategoryUI extends JPanel implements Textable {

	private UIType type;

	private int id;
	private JTextField nametxf;
	private JLabel namelbl;
	private JLabel descriptionlbl;
	private JPanel controlpnl;
	private JButton savebtn;
	private JButton deletebtn;
	private JTextArea descriptiontxf;

	private int currentCategoryId = -1;

	public CategoryUI(UIType type, int id) {
		this();
		this.type = type;
		this.id = id;

		if (this.type.equals(UIType.NEW)) {
			deletebtn.setVisible(false);
			savebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection
								.executeUpdate("INSERT INTO B8XCYHMAD4TXDLPU.CATEGORY (NAME, DESCRIPTION) "
										+ "VALUES ('"
										+ nametxf.getText()
										+ "', '"
										+ descriptiontxf.getText()
										+ "')") == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"category.successfullSave"), "",
									JOptionPane.INFORMATION_MESSAGE);
							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"category.unsuccessfullSave"), "",
									JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (this.type.equals(UIType.EDIT)) {
			loadCategory(this.id);
			savebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection
								.executeUpdate("UPDATE B8XCYHMAD4TXDLPU.CATEGORY "
										+ "SET NAME = '"
										+ nametxf.getText()
										+ "', DESCRIPTION = '"
										+ descriptiontxf.getText()
										+ "' WHERE id = " + currentCategoryId) == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"category.editSuccessfullSave"),
									"", JOptionPane.INFORMATION_MESSAGE);
							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"category.editUnsuccessfullSave"),
									"", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			deletebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (DBConnection
								.executeUpdate("delete from category where id = "
										+ currentCategoryId) == 1) {
							JOptionPane.showMessageDialog(
									null,
									LanguageEngine.getLanguage().getBundleText(
											"category.deleteSuccessfullSave"),
									"", JOptionPane.INFORMATION_MESSAGE);

							JTabbedPane tab = (JTabbedPane) getParent();
							tab.removeTabAt(tab.getSelectedIndex());
						} else
							JOptionPane
									.showMessageDialog(
											null,
											LanguageEngine
													.getLanguage()
													.getBundleText(
															"category.deleteUnsuccessfullSave"),
											"", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
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

	public CategoryUI() {
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

		JPanel general = new JPanel();
		add(general, BorderLayout.CENTER);
		general.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		namelbl = new JLabel("website : ");
		general.add(namelbl, "2, 2, right, default");
		nametxf = new JTextField();
		nametxf.setColumns(20);
		general.add(nametxf, "4, 2, left, default");
		nametxf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				JTabbedPane tabs = (JTabbedPane) getParent();
				tabs.setTitleAt(
						tabs.getSelectedIndex(),
						LanguageEngine.getLanguage().getBundleText(
								"category.newCategoryTabTitle")
								+ " - "
								+ (nametxf.getText().substring(0, nametxf
										.getText().length() > 15 ? 15 : nametxf
										.getText().length())));
			}
		});

		descriptionlbl = new JLabel("address : ");
		general.add(descriptionlbl, "2, 4, right, top");

		JScrollPane descriptionscrl = new JScrollPane();
		general.add(descriptionscrl, "4, 4, left, top");

		descriptiontxf = new JTextArea();
		descriptiontxf.setRows(5);
		descriptiontxf.setColumns(35);
		descriptionscrl.setViewportView(descriptiontxf);

		LanguageEngine.registerUI(this);
	}

	private void loadCategory(int id) {
		currentCategoryId = id;

		try {
			ResultSet result = DBConnection
					.executeQuery("SELECT NAME, DESCRIPTION FROM B8XCYHMAD4TXDLPU.CATEGORY WHERE ID = "
							+ currentCategoryId);
			if (result.next()) {
				nametxf.setText(result.getString(1));
				descriptiontxf.setText(result.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateText() {
		Language lang = LanguageEngine.getLanguage();
		namelbl.setText(lang.getBundleText("category.name"));
		descriptionlbl.setText(lang.getBundleText("category.description"));
		savebtn.setText(lang.getBundleText("contact.save"));
		deletebtn.setText(lang.getBundleText("contact.delete"));
	}
}
