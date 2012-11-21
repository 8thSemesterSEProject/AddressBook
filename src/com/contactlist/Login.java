package com.contactlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
 
@SuppressWarnings("serial")
public class Login extends JFrame implements Textable {

	private JPasswordField passwordtxf;
	  private ImageIcon okImg, okOverImg;
	private JLabel submitbtn;
	private JComboBox<Language> langcmb;
	private JLabel aboutUslbl;

	public Login() {
		setSize(new Dimension(324, 464));
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Login.class.getResource("/images/c_logo.png")));
		getContentPane().setBackground(new Color(255, 0, 0));

		okImg = new ImageIcon(Login.class.getResource("/images/ok.png"));
		okOverImg = new ImageIcon(Login.class.getResource("/images/ok_over.png"));
		new ImageIcon(Login.class.getResource("/images/ok_over.png"));

		JLabel backgroundImage = new JLabel();
		backgroundImage.setIcon(new ImageIcon(Login.class
				.getResource("/images/bg.png")));
		getContentPane().add(backgroundImage, BorderLayout.NORTH);
		backgroundImage.setBounds(0, 0, 314, 433);
		backgroundImage.setLayout(null);

		passwordtxf = new JPasswordField();
		passwordtxf.setText("password");
		passwordtxf.setEchoChar('*');
		passwordtxf.setBorder(new LineBorder(new Color(0, 52, 0), 2));
		passwordtxf.setForeground(SystemColor.textInactiveText);
		passwordtxf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordtxf.setHorizontalAlignment(SwingConstants.CENTER);
		passwordtxf.setBounds(88, 155, 152, 32);
		backgroundImage.add(passwordtxf);
		passwordtxf.setColumns(10);

		submitbtn = new JLabel("");
		submitbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitbtn.setIcon(okImg);
		submitbtn.setBounds(250, 150, 40, 45);
		backgroundImage.add(submitbtn);

		langcmb = new JComboBox<Language>();
		langcmb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LanguageEngine.setLanguage((Language) langcmb.getSelectedItem());
			}
		});
		langcmb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		langcmb.addItem(Language.ENGLISH);
		langcmb.addItem(Language.DARI);
		langcmb.setOpaque(false);
		langcmb.setBounds(7, 11, 121, 25);
		backgroundImage.add(langcmb);

		JLabel contactIconlbl = new JLabel("");
		contactIconlbl.setIcon(new ImageIcon(Login.class
				.getResource("/images/book.png")));
		contactIconlbl.setBounds(22, 138, 64, 67);
		backgroundImage.add(contactIconlbl);

		aboutUslbl = new JLabel("ABOUT US");
		aboutUslbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		aboutUslbl.setHorizontalAlignment(SwingConstants.CENTER);
		aboutUslbl.setBounds(7, 312, 291, 106);
		backgroundImage.add(aboutUslbl);

		// ====================== Actions =============================
		submitbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String password = new String(passwordtxf.getPassword());

				try {
					ResultSet isOk = DBConnection
							.executeQuery("select 'OK' from login where password = '"
									+ password + "'");

					if (isOk.next()) {
						dispose();
						LanguageEngine.unregisterUI(Login.this);

						new ContactList();
					} else {
						JOptionPane.showMessageDialog(
								null,
								LanguageEngine.getLanguage().getBundleText(
										"login.wrongPasswordText"),
								LanguageEngine.getLanguage().getBundleText(
										"login.wrongPasswordTitle"),
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		

			@Override
			public void mouseEntered(MouseEvent arg0) {
				submitbtn.setIcon(okOverImg);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				submitbtn.setIcon(okImg);
				
			}

			});

		LanguageEngine.registerUI(this);
	}

	@Override
	public void updateText() {
		aboutUslbl.setText(LanguageEngine.getLanguage().getBundleText(
				"login.about"));
	}

	public static void main(String[] args) {
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
	}
}
