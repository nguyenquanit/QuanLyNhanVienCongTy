import java.util.Scanner;

public class GiamDoc extends NhanSu {

	// Attributes
	private final float LUONG1NGAY = 300;
	private float coPhan;

	// Get & Set
	public float getCoPhan() {
		return coPhan;
	}

	public void setCoPhan(float coPhan) {
		this.coPhan = coPhan;
	}

	// Constructor
	public GiamDoc() {
		this.coPhan = 0;
	}
	// Other Method

	@Override
	public void nhap(Scanner scan) {
		super.nhap(scan);
		do {
			System.out.print("Nhập vào CỔ PHẦN của Giám Đốc: ");
			this.coPhan = Float.parseFloat(scan.nextLine());
			if (this.coPhan <= 0 || this.coPhan > 100) {
				System.out.println("Cổ phần không quá 100%, vui lòng nhập lại");
			}
		} while (this.coPhan <= 0 || this.coPhan > 100);
	}

	@Override
	public float tinhLuong() {
		return this.luongThangNV = LUONG1NGAY * this.getSoNgayLamViec();
	}

	public void xuat(float thuNhap) {
		System.out.print("Chức vụ: giám đốc\t");
		super.xuat();
		System.out.println("\tsố Cổ Phần: " + this.coPhan + "%" + "\tLương/Ngày: " + LUONG1NGAY + "\tcó Lương tháng là: "
				+ this.luongThangNV + "\t tổng Thu Nhập là: " + thuNhap);
	}
	public void xuatMavaTen() {
		System.out.println("Giám đốc -- Mã: " + this.getMaNV() + "\t Tên: " + this.getTenNV());
	}

}
