package com.contactlist;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

@SuppressWarnings("serial")
public class AdvanceSearchListContacts extends JPanel implements Textable {
	private JTable table;
	private JButton edit;
	private JButton delete;
	private JPanel searchpnl;
	private JTextField searchkey;
	private JComboBox<SearchNode> columnNames;
	private JButton searchbtn;
	private Component gap_1;
	private Component gap_2; 

	public AdvanceSearchListContacts() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		Language lang = LanguageEngine.getLanguage();
		table = new JTable(new DefaultTableModel(new String[] { "",
				lang.getBundleText("listcontact.number"),
				lang.getBundleText("listcontact.name"),
				lang.getBundleText("listcontact.lastname"),
				lang.getBundleText("listcontact.mobile"),
				lang.getBundleText("listcontact.phone"),
				lang.getBundleText("listcontact.email") }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});
		table.getColumn("").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return null;
			}
		});
		table.getColumn("").setMaxWidth(5);

		scrollPane.setViewportView(table);

		JPanel controlpnl = new JPanel();
		FlowLayout fl_controlpnl = (FlowLayout) controlpnl.getLayout();
		fl_controlpnl.setAlignment(FlowLayout.RIGHT);
		controlpnl.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(controlpnl, BorderLayout.SOUTH);

		edit = new JButton("edit");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1)
					return;

				int id = Integer.parseInt("" + table.getValueAt(selectedRow, 0));

				((JTabbedPane) getParent()).addTab(LanguageEngine.getLanguage()
						.getBundleText("contact.editContactTabTitle"),
						new ContactUI(UIType.EDIT, id));
			}
		});
		controlpnl.add(edit);

		delete = new JButton("delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					if (selectedRow == -1)
						return;

					int id = Integer.parseInt(""
							+ table.getValueAt(selectedRow, 0));
					if (DBConnection
							.executeUpdate("delete from contact where id = "
									+ id) == 1) {
						((DefaultTableModel) table.getModel())
								.removeRow(selectedRow);
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"contact.deleteSuccessfullSave"), "",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"contact.deleteUnsuccessfullSave"), "",
								JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		controlpnl.add(delete);

		searchpnl = new JPanel();
		searchpnl.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(searchpnl, BorderLayout.NORTH);
		searchpnl.setLayout(new BoxLayout(searchpnl, BoxLayout.X_AXIS));

		searchkey = new JTextField();
		searchpnl.add(searchkey);
		searchkey.setColumns(10);

		gap_1 = Box.createHorizontalStrut(5);
		searchpnl.add(gap_1);

		columnNames = new JComboBox<SearchNode>();
		columnNames.addItem(new SearchNode("first_name",
				"advanceSearch.firstName"));
		columnNames.addItem(new SearchNode("last_name",
				"advanceSearch.lastName"));
		columnNames.addItem(new SearchNode("nick_name",
				"advanceSearch.nickName"));
		columnNames.addItem(new SearchNode("phone", "advanceSearch.phone"));
		columnNames.addItem(new SearchNode("mobile", "advanceSearch.mobile"));
		columnNames.addItem(new SearchNode("email", "advanceSearch.email"));
		columnNames.addItem(new SearchNode("website", "advanceSearch.website"));
		columnNames.addItem(new SearchNode("address", "advanceSearch.address"));

		searchpnl.add(columnNames);

		gap_2 = Box.createHorizontalStrut(5);
		searchpnl.add(gap_2);

		searchbtn = new JButton("search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((DefaultTableModel) table.getModel()).setRowCount(0);

					ResultSet result = DBConnection
							.executeQuery("select id, first_name, last_name, mobile, phone, email from contact where "
									+ ((SearchNode) columnNames
											.getSelectedItem()).getColumnName()
									+ " like '%" + searchkey.getText() + "%'");
					while (result.next()) {
						((DefaultTableModel) table.getModel())
								.addRow(new Object[] { result.getInt(1),
										table.getRowCount() + 1,
										result.getString(2),
										result.getString(3),
										result.getString(4),
										result.getString(5),
										result.getString(6) });
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		searchpnl.add(searchbtn);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LanguageEngine.registerUI(AdvanceSearchListContacts.this);
			}
		});
	}

	public void updateText() {
		Language lang = LanguageEngine.getLanguage();

		edit.setText(lang.getBundleText("listcontact.edit"));
		delete.setText(lang.getBundleText("listcontact.delete"));

		table.getColumnModel().getColumn(1)
				.setHeaderValue(lang.getBundleText("listcontact.number"));
		table.getColumnModel().getColumn(2)
				.setHeaderValue(lang.getBundleText("listcontact.name"));
		table.getColumnModel().getColumn(3)
				.setHeaderValue(lang.getBundleText("listcontact.lastname"));
		table.getColumnModel().getColumn(4)
				.setHeaderValue(lang.getBundleText("listcontact.mobile"));
		table.getColumnModel().getColumn(5)
				.setHeaderValue(lang.getBundleText("listcontact.phone"));
		table.getColumnModel().getColumn(6)
				.setHeaderValue(lang.getBundleText("listcontact.email"));

		((JTabbedPane) getParent())
				.setTitleAt(
						((JTabbedPane) getParent()).getSelectedIndex(),
						lang.getBundleText("listcategories.listAdvanceContactTabTitle"));

		searchbtn.setText(lang.getBundleText("listcontact.search"));

		columnNames.getItemAt(0).setTitleText(
				lang.getBundleText("advanceSearch.firstName"));
		columnNames.getItemAt(1).setTitleText(
				lang.getBundleText("advanceSearch.lastName"));
		columnNames.getItemAt(2).setTitleText(
				lang.getBundleText("advanceSearch.nickName"));
		columnNames.getItemAt(3).setTitleText(
				lang.getBundleText("advanceSearch.phone"));
		columnNames.getItemAt(4).setTitleText(
				lang.getBundleText("advanceSearch.mobile"));
		columnNames.getItemAt(5).setTitleText(
				lang.getBundleText("advanceSearch.email"));
		columnNames.getItemAt(6).setTitleText(
				lang.getBundleText("advanceSearch.website"));
		columnNames.getItemAt(7).setTitleText(
				lang.getBundleText("advanceSearch.address"));
	}

	private static final class SearchNode {
		private String columnName;
		private String titleText;

		public SearchNode(String columnName, String titleText) {
			this.columnName = columnName;
			this.titleText = titleText;
		}

		public void setTitleText(String titleText) {
			this.titleText = titleText;
		}

		public String getColumnName() {
			return columnName;
		}

		@Override
		public String toString() {
			return titleText;
		}
	}
}
