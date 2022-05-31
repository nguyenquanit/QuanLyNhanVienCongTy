import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CongTy {
	//
	final private int NV_THUONG = 1;
	final private int TRUONGPHONG = 2;
	final private int GIAMDOC = 3;

	// Attributes
	private float doanhThuThang;
	private float loiNhuan;
	private float tongLuong;
	private List<NhanSu> dsNV;
	private String tenCongTy;
	private int maCongTy;

	// Get & Set
	public void setLoiNhuan(float _loiNhuan) {
		this.loiNhuan = _loiNhuan;
	}

	public float getLoiNhuan() {
		return loiNhuan;
	}

	public void setDoanhThuThang(float _doanhThuThang) {
		this.doanhThuThang = _doanhThuThang;
	}

	public float getDoanhThuThang() {
		return doanhThuThang;
	}

	public float getTongLuong() {
		return tongLuong;
	}

	public List<NhanSu> getDsNV() {
		return dsNV;
	}

	public void setDsNV(List<NhanSu> dsNV) {
		this.dsNV = dsNV;
	}

	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
		System.out.println("Đã cập nhật thành công tên CT");
	}

	public int getMaCongTy() {
		return maCongTy;
	}

	public void setMaCongTy(int maCongTy) {
		this.maCongTy = maCongTy;
	}

	// Constructor
	public CongTy() {
		this.dsNV = new ArrayList<NhanSu>();
		this.tenCongTy = "ABC";
	}
	// Other Method

	public void themNV(NhanSu nv) {
		this.dsNV.add(nv);
	}

	public NhanSu timNVTheoMa(int ma) {
		NhanSu nv1 = null; // chưa tìm thấy
		for (NhanSu nv2 : this.dsNV) {
			if (nv2.getMaNV() == ma) {
				nv1 = nv2;
				break;
			}
		}
		return nv1;
	}

	public void nhapNV(Scanner scan) {
		boolean thoat = false;
		boolean chonTiep = false;
		do {
			thoat = false;
			NhanSu nv;
			System.out.println("Chọn loại Nhân Viên muốn thêm vào");
			System.out.println(
					"1. Nhân Viên Thường \t 2. Trưởng Phòng \t 3. Giám Đốc \t 4. Cập nhật trưởng phòng \t 0. Trở về menu chính");
			System.out.print("Chọn >>> ");
			int chon = Integer.parseInt(scan.nextLine());
			switch (chon) {
			case NV_THUONG: {
				nv = new NhanVien();
				nv.nhap(scan);
				nv.tinhLuong();
				themNV(nv);
				chonTiep = true;
			}
				break;
			case TRUONGPHONG: {
				nv = new TruongPhong();
				nv.nhap(scan);
				nv.tinhLuong();
				themNV(nv);
				chonTiep = true;
			}
				break;
			case GIAMDOC: {
				nv = new GiamDoc();
				nv.nhap(scan);
				nv.tinhLuong();
				themNV(nv);
				chonTiep = true;
			}
				break;
			case 4: {
				List<NhanSu> dstp = this.LayDSNV(TRUONGPHONG);
				if (dstp.size() != 0) {
					for (NhanSu tp : dstp) {
						((TruongPhong) tp).setSoNV(0);
					}
					capNhatToanBoNV();
				} else {
					System.out.println("Hiện tại danh sách trưởng phòng đang rỗng! Yêu cầu nhập thêm trưởng phỏng!");
				}
				chonTiep = true;
			}
				break;
			case 0: {
				chonTiep = false;
				thoat = true;
			}
				break;
			default: {
				System.out.println("Mời bạn chọn lại!");
				chonTiep = false;
			}
			}
			if (chonTiep) {
				do {
					System.out.print("Tiếp tục thêm Nhân Viên? \t 1 - Có, 0 - Không : ");
					int tieptuc = Integer.parseInt(scan.nextLine());
					if (tieptuc == 1) {
						thoat = false;
						break;
					} else if (tieptuc == 0) {
						thoat = true;
						break;
					} else {
						System.out.println("Mời bạn chọn lại!");
					}
				} while (true);
			}
		} while (!thoat);
	}

	public void xuat() {
		if (this.dsNV.size() != 0) {
			this.tinhLoiNhuanCty();
			System.out.println("=========== DANH SÁCH NHÂN VIÊN CÔNG TY " + this.tenCongTy + " ===========");
			for (NhanSu nv : this.dsNV) {
				if (nv instanceof GiamDoc) {
					float thuNhap = nv.luongThangNV + (((GiamDoc) nv).getCoPhan() / 100) * this.loiNhuan;
					((GiamDoc) nv).xuat(thuNhap);
				} else {
					nv.xuat();
				}
			}
			System.out.println("====================================================================");
		} else {
			System.out.println("Danh sách nhân viên đang rỗng! Yêu cầu nhập thêm nhân viên.");
		}
	}

	public void capNhatToanBoNV() {
		// Phân bổ nhân viên vào phòng ban
		// duyệt danh sách nhân viên thương và cập nhật trưởng phòng
		inDSNV(2);// in DS trưởng phòng
		for (NhanSu nv : this.dsNV) {
			if (nv instanceof NhanVien) {
				((NhanVien) nv).xuatMavaTen();
				this.capNhatTruongPhong(nv);
			}
		}
	}

	private void capNhatTruongPhong(NhanSu nv) {
		boolean thoat = false;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Nhập mã trưởng phòng mới cho nhân viên, nhập -1 nếu chưa ấn định:");
			int chon = Integer.parseInt(scan.nextLine());
			TruongPhong truongPhong = timTPTheoMaTP(chon, this.dsNV);
			// ((NhanVien) nv).setMaTP(Integer.parseInt(scan.nextLine())); //viết siêu tắt
			if (truongPhong != null) {
				((NhanVien) nv).setMaTP(chon);
				// cập nhật số lượng nhân viên cho trưởng phòng đang chọn
				truongPhong.tangSoLuong();
				thoat = true;
				System.out.println("Cập nhật thành công!");
			} else if (chon == -1) {
				thoat = true;
			} else {
				System.out.println("Cập nhật thất bại!");
				System.out.println("Vui lòng chọn trưởng phòng có trong danh sách hoặc -1");
				thoat = false;
			}
			// count++;
		} while (!thoat);

	}

	private NhanVien timNVThuongTheoMaNV(int ma, List<NhanSu> dsnv) {
		for (NhanSu nv : dsnv) {
			if (nv instanceof NhanVien) {
				if (((NhanVien) nv).getMaNV() == ma) {
					return (NhanVien) nv;
				}
			}
		}
		return null;
	}

	private TruongPhong timTPTheoMaNV(int ma, List<NhanSu> dsnv) {
		for (NhanSu nv : dsnv) {
			if (nv instanceof TruongPhong) {
				if (((TruongPhong) nv).getMaNV() == ma) {
					return (TruongPhong) nv;
				}
			}
		}
		return null;
	}

	private GiamDoc timGDTheoMaNV(int ma, List<NhanSu> dsnv) {
		for (NhanSu nv : dsnv) {
			if (nv instanceof GiamDoc) {
				if (((GiamDoc) nv).getMaNV() == ma) {
					return (GiamDoc) nv;
				}
			}
		}
		return null;
	}

	private TruongPhong timTPTheoMaTP(int ma, List<NhanSu> dsnv) {
		for (NhanSu nv : dsnv) {
			if (nv instanceof TruongPhong) {
				if (((TruongPhong) nv).getMaTP() == ma) {
					return (TruongPhong) nv;
				}
			}
		}
		return null;
	}

	private void inDSNV(int loaiNV) {
		if (loaiNV == NV_THUONG) {
			System.out.println("Đây là danh sách nhân viên thường");
		} else if (loaiNV == TRUONGPHONG) {
			System.out.println("Đây là danh sách trưởng phòng");
		} else {
			System.out.println("Đây là danh sách giám đốc");
		}
		for (NhanSu nv : this.dsNV) {
			if (loaiNV == NV_THUONG) {
				if (nv instanceof NhanVien) {
					((NhanVien) nv).xuatMavaTen();
				}
			} else if (loaiNV == TRUONGPHONG) {
				if (nv instanceof TruongPhong) {
					((TruongPhong) nv).xuatMavaTen();
				}
			} else {
				if (nv instanceof GiamDoc) {
					((GiamDoc) nv).xuatMavaTen();
				}
			}
		}
	}

// Sửa tên CTY
	public void suaTenCTY(String tenMoi) {
		this.tenCongTy = tenMoi;
	}

// Tính tổng lương
	public float tongLuongCongTy() {
		float tongluong = 0;
		for (NhanSu nv : this.dsNV) {
			nv.tinhLuong();
			tongluong += nv.getLuongThangNV();
		}
		return this.tongLuong = tongluong;
	}

//	Xóa Nhân viên
	private void inXoa() {
		System.out.println("Chọn loại Nhân Viên xóa thêm vào");
		System.out.println("1. Nhân Viên Thường \t 2. Trưởng Phòng \t 3. Giám Đốc \t 0. Trở về menu chính");
		System.out.print("Chọn >>> ");
	}

	private void inSua() {
		System.out.println("Chọn loại Nhân Viên cần sửa thông tin");
		System.out.println("1. Nhân Viên Thường \t 2. Trưởng Phòng \t 3. Giám Đốc \t 0. Trở về menu chính");
		System.out.print("Chọn >>> ");
	}

	private List<NhanSu> LayDSNV(int loaiNV) {
		List<NhanSu> dsnv = new ArrayList<NhanSu>();
		for (NhanSu nv : this.dsNV) {
			if (loaiNV == NV_THUONG) {
				if (nv instanceof NhanVien) {
					dsnv.add(nv);
				}
			} else if (loaiNV == TRUONGPHONG) {
				if (nv instanceof TruongPhong) {
					dsnv.add(nv);
				}
			} else {
				if (nv instanceof GiamDoc) {
					dsnv.add(nv);
				}
			}
		}
		return dsnv;
	}

	private void inDSNV(int loaiNV, List<NhanSu> dsnv) {
		if (loaiNV == NV_THUONG) {
			System.out.println("Đây là danh sách nhân viên thường");
		} else if (loaiNV == TRUONGPHONG) {
			System.out.println("Đây là danh sách trưởng phòng");
		} else {
			System.out.println("Đây là danh sách giám đốc");
		}
		for (NhanSu nv : dsnv) {
			if (loaiNV == NV_THUONG) {
				if (nv instanceof NhanVien) {
					((NhanVien) nv).xuatMavaTen();
				}
			} else if (loaiNV == TRUONGPHONG) {
				if (nv instanceof TruongPhong) {
					((TruongPhong) nv).xuatMavaTen();
				}
			} else {
				if (nv instanceof GiamDoc) {
					((GiamDoc) nv).xuatMavaTen();
				}
			}
		}
	}

	private void XoaNVThuong(List<NhanSu> dsnv) {
		Scanner scan = new Scanner(System.in);
		do {
			inDSNV(NV_THUONG, dsnv);// in toàn bộ nhân viên
			System.out.print("Vui lòng chọn mã nhân viên thường cần xóa hoặc -1 để thoát: ");
			int chonMa = Integer.parseInt(scan.nextLine());
			if (chonMa == -1) {
				break;
			}
			NhanVien nvThuong = timNVThuongTheoMaNV(chonMa, dsnv);
			if (nvThuong != null) {
				if (nvThuong.getMaTP() != -1) {
					TruongPhong tp = timTPTheoMaTP(nvThuong.getMaTP(), this.dsNV);
					tp.giamSoLuong();
				}
				System.out.println("Đã xóa thành công nhân viên với mã: " + nvThuong.getMaNV());
				this.dsNV.remove(nvThuong);
				break;
			} else {
				System.out.println("Không tìm thấy NV với mã vừa nhập");
				break;
			}
		} while (true);
	}

	private void XoaTruongPhong(List<NhanSu> dsnv) {
		Scanner scan = new Scanner(System.in);
		do {
			inDSNV(TRUONGPHONG, dsnv);// in toàn bộ nhân viên
			System.out.print("Vui lòng chọn mã nhân viên của trưởng phòng cần xóa hoặc -1 để thoát: ");
			int chonMa = Integer.parseInt(scan.nextLine());
			if (chonMa == -1) {
				break;
			}
			TruongPhong tp = timTPTheoMaNV(chonMa, dsnv);
			if (tp != null) {
				for (NhanSu nv : this.dsNV) {
					if (nv instanceof NhanVien) {
						if (((NhanVien) nv).getMaTP() == tp.getMaTP()) {
							((NhanVien) nv).setMaTP(-1);
						}
					}
				}
				System.out.println("Đã xóa thành công trưởng phòng với mã: " + tp.getMaNV());
				this.dsNV.remove(tp);
				break;
			} else {
				System.out.println("Không tìm thấy Trưởng phòng với mã vừa nhập");
				break;
			}
		} while (true);
	}

	private void XoaGiamDoc(List<NhanSu> dsnv) {
		Scanner scan = new Scanner(System.in);
		do {
			inDSNV(GIAMDOC, dsnv);// in toàn bộ nhân viên
			System.out.print("Vui lòng chọn mã nhân viên của giám đốc cần xóa hoặc -1 để thoát: ");
			int chonMa = Integer.parseInt(scan.nextLine());
			if (chonMa == -1) {
				break;
			}
			GiamDoc giamDoc = timGDTheoMaNV(chonMa, dsnv);
			if (giamDoc != null) {
				System.out.println("Đã xóa thành công nhân viên với mã: " + giamDoc.getMaNV());
				this.dsNV.remove(giamDoc);
				break;
			} else {
				System.out.println("Không tìm thấy giám đốc với mã vừa nhập");
				break;
			}
		} while (true);
	}

	public void xoaNV(Scanner scan) {
		boolean thoat = false;
		if (this.dsNV.size() != 0) {
			do {
				this.inSua();
				int chon = Integer.parseInt(scan.nextLine());
				switch (chon) {
				case NV_THUONG: {
					List<NhanSu> dsnv = LayDSNV(1);
					if (dsnv.size() != 0) {
						this.XoaNVThuong(dsnv);
					} else {
						System.out.println("Hiện tại danh sách nhân viên thường đang rỗng!");
					}
				}
					break;
				case TRUONGPHONG: {
					List<NhanSu> dsnv = LayDSNV(2);
					if (dsnv.size() != 0) {
						this.XoaTruongPhong(dsnv);
					} else {
						System.out.println("Hiện tại danh sách Trưởng phòng đang rỗng!");
					}
				}
					break;
				case GIAMDOC: {
					List<NhanSu> dsnv = LayDSNV(3);
					if (dsnv.size() != 0) {
						this.XoaGiamDoc(dsnv);
					} else {
						System.out.println("Hiện tại danh sách Giám đốc đang rỗng!");
					}
				}
					break;
				case 0:
					thoat = true;
					break;
				}
				do {
					System.out.println("Bạn có muốn tiếp tục xóa nhân viên của công ty " + this.tenCongTy + " ?");
					System.out.println("1: có; 0: không");
					System.out.print("Chọn >> ");
					int chonTiepTuc = Integer.parseInt(scan.nextLine());
					if (chonTiepTuc == 1) {
						break;
					} else if (chonTiepTuc == 0) {
						thoat = true;
						break;
					} else {
						System.out.println("Lựa chọn không hợp lệ! Yêu cầu chọn lại.");
					}
				} while (true);
			} while (!thoat);
		} else {
			System.out.println("Yêu cầu nhập thêm nhân viên để thực hiện xóa!");
		}
	}

	public void suaNV(Scanner scan) {
		boolean thoat = false;
		if (this.dsNV.size() != 0) {
			do {
				this.inSua();
				int chon = Integer.parseInt(scan.nextLine());
				switch (chon) {
				case NV_THUONG: {
					List<NhanSu> dsnv = LayDSNV(NV_THUONG);
					if (dsnv.size() != 0) {
						this.suaThongTinNV(dsnv, NV_THUONG);
					} else {
						System.out.println("Hiện tại danh sách nhân viên thường đang rỗng!");
					}
				}
					break;
				case TRUONGPHONG: {
					List<NhanSu> dsnv = LayDSNV(TRUONGPHONG);
					if (dsnv.size() != 0) {
						this.suaThongTinNV(dsnv, TRUONGPHONG);
					} else {
						System.out.println("Hiện tại danh sách Trưởng phòng đang rỗng!");
					}
				}
					break;
				case GIAMDOC: {
					List<NhanSu> dsnv = LayDSNV(GIAMDOC);
					if (dsnv.size() != 0) {
						this.suaThongTinNV(dsnv, GIAMDOC);
					} else {
						System.out.println("Hiện tại danh sách Giám đốc đang rỗng!");
					}
				}
					break;
				case 0:
					thoat = true;
					break;
				}
				do {
					System.out.println(
							"Bạn có muốn tiếp tục sửa thông tin nhân viên của công ty " + this.tenCongTy + " ?");
					System.out.println("1: có; 0: không");
					System.out.print("Chọn >> ");
					int chonTiepTuc = Integer.parseInt(scan.nextLine());
					if (chonTiepTuc == 1) {
						break;
					} else if (chonTiepTuc == 0) {
						thoat = true;
						break;
					} else {
						System.out.println("Lựa chọn không hợp lệ! Yêu cầu chọn lại.");
					}
				} while (true);
			} while (!thoat);
		} else {
			System.out.println("Yêu cầu nhập thêm nhân viên để thực hiện sửa thông tin!");
		}
	}

	private void inMenuSuaThongTin() {
		System.out.println("Nhập vào nội dung muốn sửa");
		System.out.println("1. Sửa mã nhân viên");
		System.out.println("2. Sửa tên nhân viên");
		System.out.println("3. Sửa Số điện thoại");
		System.out.println("4. Sửa số ngày làm việc");
		System.out.println("Để thoát chọn -1");
		System.out.print("Chọn >> ");
	}

	private boolean suaThongTinChiTiet(int luaChon, NhanSu nv, String chucDanh) {
		Scanner scan = new Scanner(System.in);
		do {
			switch (luaChon) {
			case -1: {
				return true;
			}
			case 1: {
				System.out.println("Vui lòng nhập mã nhân viên mới cho " + chucDanh);
				System.out.print("Nhập: ");
				nv.setMaNV(Integer.parseInt(scan.nextLine()));
				System.out.println("Đã cập nhật mã nhân viên cho " + chucDanh + " thành công");
				return true;
			}
			case 2: {
				System.out.println("Vui lòng nhập tên mới cho " + chucDanh);
				System.out.print("Nhập: ");
				nv.setTenNV(scan.nextLine());
				System.out.println("Đã cập nhật tên " + chucDanh + " thành công");
				return true;
			}
			case 3: {
				System.out.println("Vui lòng nhập số điện thoại mới cho " + chucDanh);
				System.out.print("Nhập: ");
				nv.setSdtNV(scan.nextLine());
				System.out.println("Đã cập nhật sđt cho " + chucDanh + " thành công");
				return true;
			}
			case 4: {
				System.out.println("Vui lòng nhập số ngày làm việc mới cho " + chucDanh);
				System.out.print("Nhập: ");
				nv.setSoNgayLamViec(Float.parseFloat(scan.nextLine()));
				nv.tinhLuong();
				System.out.println("Đã cập nhật số ngày làm việc cho " + chucDanh + " thành công");
				return true;
			}
			default:
				System.out.println("Bạn đã lựa chọn sai. Vui lòng chọn lại! từ 1 > 4 hoặc -1 để thoát");
				return false;
			}
		} while (true);
	}

	public void suaThongTinNV(List<NhanSu> dsnv, int loaiNV) {
		Scanner scan = new Scanner(System.in);
		boolean thoat = false;
		String chucDanh = "";
		if (loaiNV == NV_THUONG) {
			chucDanh = "Nhân viên thường";
		} else if (loaiNV == TRUONGPHONG) {
			chucDanh = "Trưởng phòng";
		} else {
			chucDanh = "Giám đốc";
		}
		do {
			inDSNV(loaiNV, dsnv);// in toàn bộ nhân viên
			System.out.println("Vui lòng chọn mã nhân viên của " + chucDanh + " cần sửa thông tin hoặc -1 để thoát: ");
			System.out.print("Chọn >>> ");
			int chonMa = Integer.parseInt(scan.nextLine());
			if (chonMa == -1) {
				break;
			}
			NhanVien nvThuong = timNVThuongTheoMaNV(chonMa, dsnv);
			if (nvThuong != null) {
				thoat = false;
				do {
					inMenuSuaThongTin();
					int chon = Integer.parseInt(scan.nextLine());
					thoat = suaThongTinChiTiet(chon, nvThuong, chucDanh);
				} while (!thoat);
				break;
			} else {
				System.out.println("Không tìm thấy " + chucDanh + " với mã vừa nhập");
				break;
			}
		} while (true);
	}

	private List<TruongPhong> timTPLuongMax_ChiTiet(List<NhanSu> dstp) {
		List<TruongPhong> dstp_max = new ArrayList<TruongPhong>();
		TruongPhong tp_max = null;
		if (dstp.size() == 1) {
			tp_max = ((TruongPhong) dstp.get(0));
			dstp_max.add(tp_max);
		} else {
			tp_max = ((TruongPhong) dstp.get(0));
			for (NhanSu tp : dstp) {
				if (tp_max.getLuongThangNV() < tp.getLuongThangNV()) {
					tp_max = (TruongPhong) tp;
				}
			}
			for (NhanSu tp : dstp) {
				if (tp.getLuongThangNV() == tp_max.getLuongThangNV()) {
					dstp_max.add((TruongPhong) tp);
				}
			}
		}
		return dstp_max;
	}

	public void timTruongPhongLuongMax() {
		if (this.dsNV.size() != 0) {
			List<NhanSu> dstp = this.LayDSNV(TRUONGPHONG);
			if (dstp.size() != 0) {
				for (NhanSu nv : dstp) {
					nv.tinhLuong();
				}
				List<TruongPhong> dstp_max = timTPLuongMax_ChiTiet(dstp);
				if (dstp_max.size() == 1) {
					System.out.println("Đã tìm ra trưởng phòng có lương cao nhất");
					dstp_max.get(0).xuatMavaTen();
					System.out.println("Với lương là: " + dstp_max.get(0).getLuongThangNV());
				} else {
					System.out.println("Đây là danh sách các trưởng phòng có lương cao nhất");
					for (TruongPhong tp : dstp_max) {
						tp.xuatMavaTen();
					}
					System.out.println("Với lương là: " + dstp_max.get(0).getLuongThangNV());
				}
			} else {
				System.out.println("Hiện tại danh sách trưởng phòng đang rỗng. Yêu cầu nhập thêm trưởng phòng");
			}
		} else {
			System.out.println("Danh sách nhân viên đang rỗng! yếu cầu nhập thêm nhân viên");
		}
	}

	private List<TruongPhong> layDSTP_CoNVDuoiQuyen(List<NhanSu> dstp) {
		List<TruongPhong> dstp_CoNVDuoiQuyen = new ArrayList<TruongPhong>();
		for (NhanSu tp : dstp) {
			if (((TruongPhong) tp).getSoNV() != 0) {
				dstp_CoNVDuoiQuyen.add((TruongPhong) tp);
			}
		}
		return dstp_CoNVDuoiQuyen;
	}

	private List<TruongPhong> layDSTP_CoNVDuoiQuyenMax(List<TruongPhong> dstp) {
		List<TruongPhong> dstp_MaxNVDuoiQuyen = new ArrayList<TruongPhong>();
		TruongPhong tp_MaxNV = dstp.get(0);
		for (TruongPhong tp : dstp) {
			if (tp_MaxNV.getSoNV() < tp.getSoNV()) {
				tp_MaxNV = tp;
			}
		}
		for (TruongPhong tp : dstp) {
			if (tp_MaxNV.getSoNV() == tp.getSoNV()) {
				dstp_MaxNVDuoiQuyen.add(tp);
			}
		}
		return dstp_MaxNVDuoiQuyen;
	}

	public void timTruongPhongMAXNV() {
		if (this.dsNV.size() != 0) {
			List<NhanSu> dstp = this.LayDSNV(TRUONGPHONG);
			if (dstp.size() != 0) {
				List<TruongPhong> dstp_CoNVDuoiQuyen = layDSTP_CoNVDuoiQuyen(dstp);
				if (dstp_CoNVDuoiQuyen.size() == 0) {
					System.out.println("Hiện tại không có trưởng phòng nào có nhân viên dưới quyền");
				} else if (dstp_CoNVDuoiQuyen.size() == 1) {
					System.out.println("Có 1 Trưởng phòng có số NV dưới quyền: ");
					dstp_CoNVDuoiQuyen.get(0).xuatMavaTen();
					System.out.println("Với số lượng nhân viên dưới quyền là: " + dstp_CoNVDuoiQuyen.get(0).getSoNV());
				} else {
					List<TruongPhong> dstp_MaxNVDuoiQuyen = layDSTP_CoNVDuoiQuyenMax(dstp_CoNVDuoiQuyen);
					System.out.println("Đây là danh sách trưởng phòng có số NV cao nhất:");
					for (TruongPhong tp : dstp_MaxNVDuoiQuyen) {
						tp.xuatMavaTen();
					}
					System.out.println("Với số nhân viên dưới quyền là: " + dstp_MaxNVDuoiQuyen.get(0).getSoNV());
				}
			} else {
				System.out.println("Hiện tại danh sách trưởng phòng đang rỗng. Yêu cầu nhập thêm trưởng phòng");
			}
		} else {
			System.out.println("Danh sách nhân viên đang rỗng! yếu cầu nhập thêm nhân viên");
		}
	}

	public List<GiamDoc> timGiamDocCoPhan_MaxChiTiet(List<NhanSu> dsgd) {
		GiamDoc gd_max = (GiamDoc) dsgd.get(0);
		List<GiamDoc> dsgd_max = new ArrayList<GiamDoc>();
		for (NhanSu gd : dsgd) {
			if (gd_max.getCoPhan() < ((GiamDoc) gd).getCoPhan()) {
				gd_max = ((GiamDoc) gd);
			}
		}
		for (NhanSu gd : dsgd) {
			if (gd_max.getCoPhan() == ((GiamDoc) gd).getCoPhan()) {
				dsgd_max.add((GiamDoc) gd);
			}
		}
		return dsgd_max;
	}

	public void timGiamDocCoPhan_Max() {
		if (this.dsNV.size() != 0) {
			List<NhanSu> dsgd = this.LayDSNV(GIAMDOC);
			if (dsgd.size() != 0) {
				List<GiamDoc> dsgd_max = timGiamDocCoPhan_MaxChiTiet(dsgd);
				if (dsgd_max.size() != 0) {
					System.out.println("Đây là danh sách giám đốc có cổ phần cao nhất: ");
					for (GiamDoc gd : dsgd_max) {
						gd.xuatMavaTen();
					}
					System.out.println("Với số cổ phần là: " + dsgd_max.get(0).getCoPhan() + "%");
				}
			} else {
				System.out.println("Hiện tại danh sách giám đốc đang rỗng. Yêu cầu nhập thêm giám đốc");
			}
		} else {
			System.out.println("Danh sách nhân viên đang rỗng! yêu cầu nhập thêm nhân viên");
		}
	}

	public void tinhLoiNhuanCty() {
		this.tongLuongCongTy();
		this.loiNhuan = this.doanhThuThang - this.tongLuong;
	}

	public void tongThuNhapCuaTungGiamDoc() {
		if (this.dsNV.size() != 0) {
			List<NhanSu> dsgd = this.LayDSNV(GIAMDOC);
			if (dsgd.size() != 0) {
				this.tinhLoiNhuanCty();
				System.out.println(this.loiNhuan);
				System.out.println("Lợi nhuận của toàn cty là: " + this.loiNhuan);
				System.out.println("Đây là danh sách giám đốc đã đươc tính thu nhập.");
				for (NhanSu gd : dsgd) {
					float thuNhap = gd.luongThangNV + (((GiamDoc) gd).getCoPhan() / 100) * this.loiNhuan;
					((GiamDoc) gd).xuat(thuNhap);
				}
			} else {
				System.out.println("Hiện tại danh sách giám đốc đang rỗng. Yêu cầu nhập thêm giám đốc");
			}
		} else {
			System.out.println("Danh sách nhân vien đang rỗng! yêu cầu nhập thêm nhân viên");
		}
	}
}
