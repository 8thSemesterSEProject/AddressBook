package com.contactlist;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

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

@SuppressWarnings("serial")
public class ListContacts extends JPanel implements Textable {
	private JTable table;
	private JButton edit;
	private JButton delete;

	public ListContacts() {
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

		try {
			ResultSet result = DBConnection
					.executeQuery("select id, first_name, last_name, mobile, phone, email from contact");
			while (result.next()) {
				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						result.getInt(1), table.getRowCount() + 1,
						result.getString(2), result.getString(3),
						result.getString(4), result.getString(5),
						result.getString(6) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);

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
		panel.add(edit);

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
		panel.add(delete);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LanguageEngine.registerUI(ListContacts.this);
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

		((JTabbedPane) getParent()).setTitleAt(
				((JTabbedPane) getParent()).getSelectedIndex(),
				lang.getBundleText("listcontact.listContactTabTitle"));
	}
}
