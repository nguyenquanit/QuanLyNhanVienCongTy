public class NhanVien extends NhanSu {

	//Attributes
	private final float LUONG1NGAY = 100;
	private int maTP;

	//Get & Set
	
	public int getMaTP() {
		return maTP;
	}

	public void setMaTP(int maTP) {
		this.maTP = maTP;
	}

	//Constructor
	public NhanVien() {
		this.maTP = -1;
	}
	//Other Method
//	@Override
//	public void nhap(Scanner scan) {
//		super.nhap(scan);
//		System.out.print("Nhập vào MÃ TRƯỞNG PHÒNG: ");
//		this.maTP = Integer.parseInt(scan.nextLine());
//	}
	
	@Override
	public void xuat() {
		System.out.print("Chức vụ: Nhân viên\t");
		super.xuat();
		System.out.println("\tMã Trưởng Phòng: " + this.maTP + "\tLương/Ngày: " + LUONG1NGAY +
						"\tcó Lương tháng là: " + this.luongThangNV);
	}
	
	@Override
	public float tinhLuong() {
		return this.luongThangNV = this.LUONG1NGAY * this.getSoNgayLamViec(); 
	}
	
	public void xuatMavaTen() {
		System.out.println("Nhân Viên thường -- Mã: " + this.getMaNV() + "\t Tên: " + this.getTenNV());
	}

}
