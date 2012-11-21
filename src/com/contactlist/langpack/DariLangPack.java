package com.contactlist.langpack;

import java.util.ListResourceBundle;

public class DariLangPack extends ListResourceBundle {

	private String[][] bundle = new String[][] { 
			{ "login.about", "<html><p align=center>" +
					"درباره ی ما<br />" +
					"طراحی و ساخت توسط محصلین سمستر 8<br />" +
					"دیپارتمنت مهندسی نرم افزار<br />" +
					"فاکولته ی کامپیوتر ساینس پوهنتون هرات<br />" +
					"<b>نسخه ی 1.0 - عقرب  1391</b>" +
					"</p></html>" },
			{ "login.wrongPasswordText" , "پسورد اشتباه !!!"},
			{ "login.wrongPasswordTitle" , "پسورد اشتباه"},
			{ "contactList.fileMenu" , "فایل"},
			{ "contactList.viewMenu" , "نمایش"},
			{ "contactList.settingMenu" , "تنظیمات"},
			{ "contactList.addContact" , "اضافه کردن کانتکت"},
			{ "contactList.addCategory" , "اضافه کردن گروه"},
			{ "contactList.import" , "ایمپورت"},
			{ "contactList.export" , "اکسپورت"},
			{ "contactList.exit" , "خروج"},
			{ "contactList.listContacts" , "لیست کردن کانتکت ها"},
			{ "contactList.listCategories" , "لیست کردن کتگوری ها"},
			{ "contactList.advanceSearch" , "جستجوی پیشرفته"},
			{ "contactList.langlst" , "زبان"},
			{ "contactList.dari" , "دری"},
			{ "contactList.english" , "English"},
			{ "contactList.themelst" , "نمایه"},
			{ "contactList.classic" , "Classic"},
			{ "contactList.windows" , "Windows"},
			{ "contactList.changePassword" , "تغییر پسورد"},
			{ "contact.firstname" , "نام : "},
			{ "contact.lastname" , "تخلص : "},
			{ "contact.nickname" , "نام مستعار : "},
			{ "contact.birthday" , "تاریخ تولد : "},
			{ "contact.mobile" , "موبایل : "},
			{ "contact.phone" , "تلفن : "},
			{ "contact.email" , "ایمیل : "},
			{ "contact.website" , "وب سایت : "},
			{ "contact.address" , "آدرس : "},
			{ "contact.job" , "شغل : "},
			{ "contact.company" , "شرکت : "},
			{ "contact.cmobile" , "موبایل شرکت : "},
			{ "contact.cphone" , "تلفن شرکت : "},
			{ "contact.cemail" , "ایمیل شرکت : "},
			{ "contact.cwebsite" , "وب سایت شرکت : "},
			{ "contact.caddress" , "آدرس شرکت : "},
			{ "contact.general" , "عمومیات"},
			{ "contact.work" , "کار"},
			{ "contact.note" , "توضیحات"},
			{ "contact.save" , "ذخیره"},
			{ "contact.delete" , "حذف"},
			{ "contact.category" , "کتگوری : "},
			{ "contact.newContactTabTitle" , "کانتکت جدید"},
			{ "contact.editContactTabTitle", "ویرایش کانتکت" },
			{ "contact.successfullSave", "کانتکت جدید با موفقیت ذخیره شد." },
			{ "contact.unsuccessfullSave", "کانتکت جدید با موفقیت ذخیره نشد." },
			{ "contact.editSuccessfullSave", "کانتکت با موفقیت ذخیره شد." },
			{ "contact.editUnsuccessfullSave", "کانتکت با موفقیت ذخیره نشد." },
			{ "contact.deleteSuccessfullSave", "کانتکت با موفقیت حذف شد." },
			{ "contact.deleteUnsuccessfullSave", "کانتکت با موفقیت حذف نشد." },
			
			{ "category.name", "نام کتگوری : " },
			{ "category.description", "توضیحات : " },
			{ "category.newCategoryTabTitle", "کتگوری جدید" },
			{ "category.editCategoryTabTitle", "ویرایش کتگوری" },
			{ "category.successfullSave", "کتگوری جدید با موفقیت ذخیره شد." },
			{ "category.unsuccessfullSave", "کتگوری جدید با موفقیت ذخیره نشد." },
			{ "category.editSuccessfullSave", "کتگوری با موفقیت ذخیره شد." },
			{ "category.editUnsuccessfullSave", "کتگوری با موفقیت ذخیره نشد." },
			{ "category.deleteSuccessfullSave", "کتگوری با موفقیت حذف شد." },
			{ "category.deleteUnsuccessfullSave", "کتگوری با موفقیت حذف شد." },
			
			{ "listcontact.number", "#" },
			{ "listcontact.name", "نام" },
			{ "listcontact.lastname", "تخلص" },
			{ "listcontact.mobile", "موبایل" },
			{ "listcontact.phone", "تلفن" },
			{ "listcontact.email", "ایمیل" },
			{ "listcontact.delete", "حذف" },
			{ "listcontact.edit", "ویرایش" },
			{ "listcontact.listContactTabTitle", "لیست کانتکت ها" },
			{ "listcontact.search", "جستجو" },
			
			{ "listcategories.number", "#" },
			{ "listcategories.name", "نام" },
			{ "listcategories.description", "تخلص" },
			{ "listcategories.delete", "حذف" },
			{ "listcategories.edit", "ویرایش" },
			{ "listcategories.listCategoryTabTitle", "لیست کتگوری ها" },
			
			{ "advanceSearch.firstName", "نام" },
			{ "advanceSearch.lastName", "تخلص" },
			{ "advanceSearch.nickName", "نام مستعار" },
			{ "advanceSearch.phone", "تلفن" },
			{ "advanceSearch.mobile", "موبایل" },
			{ "advanceSearch.email", "ایمیل" },
			{ "advanceSearch.website", "وب سایت" },
			{ "advanceSearch.address", "آدرس" },
			{ "listcategories.listAdvanceContactTabTitle", "جستجوی پیشرفته" },
	};

	@Override
	protected Object[][] getContents() {
		return bundle;
	}
}
