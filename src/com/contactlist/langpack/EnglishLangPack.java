package com.contactlist.langpack;

import java.util.ListResourceBundle;

public class EnglishLangPack extends ListResourceBundle {

	private String[][] bundle = new String[][] {
			{
					"login.about",
					"<html><p align=center>" + "About US<br />"
							+ "DEVELOPED BY 8th SEMESTER STUDENTS<br />"
							+ "SOFTWARE ENGINEERING DEPARTMENT<br />"
							+ "CS FACULTY OF HERAT UNIVERSITY<br />"
							+ "<b>Version : 1.0  -  NOVEMBER, 2012</b>"
							+ "</p></html>" },
			{ "login.wrongPasswordText", "Wrong Password !!!" },
			{ "login.wrongPasswordTitle", "Wrong Password" },
			{ "contactList.fileMenu", "File" },
			{ "contactList.viewMenu", "View" },
			{ "contactList.settingMenu", "Setting" },
			{ "contactList.addContact", "Add Contacts" },
			{ "contactList.addCategory", "Add Categories" },
			{ "contactList.import", "Import" },
			{ "contactList.export", "Export" }, { "contactList.exit", "Exit" },
			{ "contactList.listContacts", "List Contacts" },
			{ "contactList.listCategories", "List Categories" },
			{ "contactList.advanceSearch", "Advance Search" },
			{ "contactList.langlst", "Language" },
			{ "contactList.dari", "دری" },
			{ "contactList.english", "English" },
			{ "contactList.themelst", "Theme" },
			{ "contactList.classic", "Classic" },
			{ "contactList.windows", "Windows" },
			{ "contactList.changePassword", "Change Password" },
			{ "contact.firstname", "First Name : " },
			{ "contact.lastname", "Last Name : " },
			{ "contact.nickname", "Nick Name : " },
			{ "contact.birthday", "Birthday : " },
			{ "contact.mobile", "Mobile : " }, 
			{ "contact.phone", "Phone : " },
			{ "contact.email", "Email : " },
			{ "contact.website", "Website : " },
			{ "contact.address", "Address : " },
			{ "contact.job", "Job Title : " },
			{ "contact.company", "Company Name : " },
			{ "contact.cmobile", "Company Mobile : " },
			{ "contact.cphone", "Company Phone : " },
			{ "contact.cemail", "Company Email : " },
			{ "contact.cwebsite", "Company Website : " },
			{ "contact.caddress", "Company Address : " },
			{ "contact.general", "General" }, 
			{ "contact.work", "Work" },
			{ "contact.note", "Note" },
			{ "contact.save", "Save" },
			{ "contact.delete", "Delete" },
			{ "contact.category", "Category : " },
			{ "contact.newContactTabTitle", "New Contact" },
			{ "contact.editContactTabTitle", "Edit Contact" },
			{ "contact.successfullSave", "New Contact Saved Successfully." },
			{ "contact.unsuccessfullSave", "New Contact Saved UnSuccessful." },
			{ "contact.editSuccessfullSave", "Edit Contact Saved Successfully." },
			{ "contact.editUnsuccessfullSave", "Edit Contact Saved UnSuccessful." },
			{ "contact.deleteSuccessfullSave", "Contact Deleted Successfully." },
			{ "contact.deleteUnsuccessfullSave", "Contact Deleted UnSuccessful." },
			
			{ "category.name", "Category Name : " },
			{ "category.description", "Description : " },
			{ "category.newCategoryTabTitle", "New Category" },
			{ "category.editCategoryTabTitle", "Edit Category" },
			{ "category.successfullSave", "New Category Saved Successfully." },
			{ "category.unsuccessfullSave", "New Category Saved UnSuccessful." },
			{ "category.editSuccessfullSave", "Edit Category Saved Successfully." },
			{ "category.editUnsuccessfullSave", "Edit Category Saved UnSuccessful." },
			{ "category.deleteSuccessfullSave", "Category Deleted Successfully." },
			{ "category.deleteUnsuccessfullSave", "Category Deleted UnSuccessful." },
			
			{ "listcontact.number", "#" },
			{ "listcontact.name", "Name" },
			{ "listcontact.lastname", "Last Name" },
			{ "listcontact.mobile", "Mobile" },
			{ "listcontact.phone", "Phone" },
			{ "listcontact.email", "Email" },
			{ "listcontact.delete", "Delete" },
			{ "listcontact.edit", "Edit" },
			{ "listcontact.listContactTabTitle", "List Contacts" },
			{ "listcontact.search", "Search" },
			
			{ "listcategories.number", "#" },
			{ "listcategories.name", "Name" },
			{ "listcategories.description", "Last Name" },
			{ "listcategories.delete", "Delete" },
			{ "listcategories.edit", "Edit" },
			{ "listcategories.listCategoryTabTitle", "List Contacts" },
			
			{ "advanceSearch.firstName", "First Name" },
			{ "advanceSearch.lastName", "Last Name" },
			{ "advanceSearch.nickName", "Nick Name" },
			{ "advanceSearch.phone", "Phone" },
			{ "advanceSearch.mobile", "Mobile" },
			{ "advanceSearch.email", "Email" },
			{ "advanceSearch.website", "Website" },
			{ "advanceSearch.address", "Address" },
			{ "listcategories.listAdvanceContactTabTitle", "Advance Search" },
	};

	@Override
	protected Object[][] getContents() {
		return bundle;
	}
}
