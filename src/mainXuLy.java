import java.util.Scanner;

public class mainXuLy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thucHien();
	}

	public static void inMenu() {
		System.out.println("+=========== Chương trình quản lý nhân sự Công Ty ===========+");
		System.out.println("|1.  Nhập và sửa thông tin công ty                           |");
		System.out.println("|2.  Thêm Nhân Viên mới                                      |");
		System.out.println("|3.  Sửa thông tin nhân viên công ty                         |");
		System.out.println("|4.  Xóa thông tin nhân viên công ty                         |");
		System.out.println("|5.  Xem danh sách Nhân Viên của Công Ty                     |");
		System.out.println("|6.  Xem tổng lương của toàn công ty                         |");
		System.out.println("|7.  Xem trưởng phòng có lương cao nhất                      |");
		System.out.println("|8.  Xem trưởng phòng có nhiều nhân viên dưới quyền nhất     |");
		System.out.println("|9.  Xem giám đốc có số lượng cổ phần nhiều nhất             |");
		System.out.println("|10. Sửa lại doanh thu tháng của công ty                     |");
		System.out.println("|11. Xem tổng thu nhập của từng giám đốc                     |");
		System.out.println("|0. Thoát                                                    |");
		System.out.println("+============================================================+");
	}

	public static void thucHien() {
		Scanner scan = new Scanner(System.in);
		boolean dangChon = true;
		CongTy ct = new CongTy();
		System.out.print("Vui lòng nhập doanh thu tháng của Công ty: ");
		float doanhThuThang = Float.parseFloat(scan.nextLine());
		ct.setDoanhThuThang(doanhThuThang);
		do {
			System.out.println("Doanh thu hàng tháng của cty là: "+ct.getDoanhThuThang());
			inMenu(); // In thông tin để người dùng chọn
			System.out.print("Mời bạn nhập vào lựa chọn của mình: ");
			int chon = Integer.parseInt(scan.nextLine());
			switch (chon) {
			case 1:
				System.out.print("Mời bạn nhập tên mới cho Cty: ");
				String tenMoi = scan.nextLine();
				ct.setTenCongTy(tenMoi);
				break;
			case 2:
				ct.nhapNV(scan);
				break;
			case 3:
				ct.suaNV(scan);
				break;
			case 4:
				ct.xoaNV(scan);
				break;
			case 5:
				ct.xuat();
				break;
			case 6:
				float tongLuong = ct.tongLuongCongTy();
				System.out.println("Tổng lương toàn bộ công ty là: " + tongLuong);
				break;
			case 7:
				ct.timTruongPhongLuongMax();
				break;
			case 8:
				ct.timTruongPhongMAXNV();
				break;
			case 9:
				ct.timGiamDocCoPhan_Max();
				break;
			case 10:
				System.out.print("Vui lòng nhập lại doanh thu tháng của công ty: ");
				doanhThuThang = Float.parseFloat(scan.nextLine());
				ct.setDoanhThuThang(doanhThuThang);
				break;
			case 11:
				ct.tongThuNhapCuaTungGiamDoc();
				break;
			case 0:
				dangChon = false;
				break;
			}
		} while (dangChon);
	}
}
