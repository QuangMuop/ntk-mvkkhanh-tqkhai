package propertyeditor;

import java.beans.PropertyEditorSupport;

import pojos.TrangThaiDonHang;
import dao.TrangThaiDonHangDAO;

public class CartStatusPropertyEditor extends PropertyEditorSupport {
	public void setAsText(String text) {
		TrangThaiDonHang ttdh = TrangThaiDonHangDAO.layThongTinTrangThaiDonHang(Long.parseLong(text));
		setValue(ttdh);
	}
	
	public String getAsText() {
        if (getValue() == null) {
            return "";
        }
        return "" + ((TrangThaiDonHang)getValue()).getMaTrangThaiDonHang();
    }
}
