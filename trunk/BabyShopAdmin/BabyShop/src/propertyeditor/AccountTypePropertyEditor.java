package propertyeditor;

import java.beans.PropertyEditorSupport;

import pojos.LoaiTaiKhoan;

import dao.LoaiTaiKhoanDAO;

public class AccountTypePropertyEditor extends PropertyEditorSupport {
	public void setAsText(String text) {
		LoaiTaiKhoan ltk = LoaiTaiKhoanDAO.layThongTinLoaiTaiKhoan(Long.parseLong(text));
		setValue(ltk);
	}
	
	public String getAsText() {
        if (getValue() == null) {
            return "";
        }
        return "" + ((LoaiTaiKhoan)getValue()).getMaLoaiTaiKhoan();
    }
}
