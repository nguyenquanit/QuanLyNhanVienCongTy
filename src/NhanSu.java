import java.util.Scanner;

public class NhanSu {

	//Attributes
	protected int maNV;
	protected String tenNV;
	protected String sdtNV;
	protected float soNgayLamViec;
	protected float luongThangNV;
	//protected int maTP; không có để ở đây
	//Get & Set
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSdtNV() {
		return sdtNV;
	}
	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}
	public float getSoNgayLamViec() {
		return soNgayLamViec;
	}
	public void setSoNgayLamViec(float soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}
	
	public float getLuongThangNV() {
		return luongThangNV;
	}
	
	public void setLuongThangNV(float luongThangNV) {
		this.luongThangNV = luongThangNV;
	}
	//Constructor
	public NhanSu() {
		
	}
	
	//Other Method
	public void nhap(Scanner scan) {
		System.out.print("Nhập vào MÃ nhân viên: ");
		this.maNV = Integer.parseInt(scan.nextLine());
		System.out.print("Nhập vào HỌ TÊN nhân viên: ");
		this.tenNV = scan.nextLine();
		System.out.print("Nhập vào SỐ ĐIỆN THOẠI nhân viên: ");
		this.sdtNV = scan.nextLine();
		System.out.print("Nhập vào SỐ NGÀY LÀM VIỆC của nhân viên: ");
		this.soNgayLamViec = Float.parseFloat(scan.nextLine());
	}
	
	public void xuat() {
		System.out.print("---Mã: " + this.maNV + "\t Họ và tên: " + this.tenNV +
				"\t Số điện thoại: " + this.sdtNV + "\t số Ngày Làm Việc là: " + this.soNgayLamViec);
	}
	
	public float tinhLuong() {
		return this.luongThangNV = 0; 
	}
	
	
	
}
