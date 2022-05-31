import java.util.Scanner;

public class TruongPhong extends NhanSu {

	//Attributes
	private final float LUONG1NGAY = 200;
	private final int PHUCAP = 100;
	private int maTP;
	private int soNV;
	
	//Get & Set
	public int getMaTP() {
		return maTP;
	}
	public void setMaTP(int maTP) {
		this.maTP = maTP;
	}
	public int getSoNV() {
		return soNV;
	}
	public void setSoNV(int soNV) {
		this.soNV = soNV;
	}
	//Constructor
	public TruongPhong() {
		
	}
	//Other Method
	@Override
	public void nhap(Scanner scan) {
		super.nhap(scan);
		System.out.print("Nhập vào MÃ TRƯỞNG PHÒNG: ");
		this.maTP = Integer.parseInt(scan.nextLine());
	}
	
	@Override
	public float tinhLuong() {
		return this.luongThangNV = LUONG1NGAY * this.soNgayLamViec + PHUCAP * this.soNV;
	}
	
	@Override 
	public void xuat() {
		System.out.print("Chức vụ: Trưởng phòng\t");
		super.xuat();
		System.out.println("\tMã Trưởng Phòng: " + this.maTP + "\tsố Nhân Viên dưới quyền: " + this.soNV
						+ "\tLương/Ngày: " + LUONG1NGAY + "\t có Lương tháng là: " + this.luongThangNV);
	}
	
	public void tangSoLuong() {
		this.soNV++;
	}
	public void giamSoLuong() {
		this.soNV--;
	}
	
//	public void themNVDuoiQuyen(int ma) {
//		if (ma == this.maTP) {
//			tangSoLuong();
//		}
//		
	//}
	
	public void xuatMavaTen() {
		System.out.println("Trưởng phòng -- MãNV: "+ this.getMaNV() +" Mã Trưởng Phòng: " + this.getMaTP() + "\t Tên: " + this.getTenNV());
	}
}
