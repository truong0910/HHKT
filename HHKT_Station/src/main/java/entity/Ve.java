package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Ve {
    @Id
    @ColumnDefault("[dbo].[auto_idve]()")
    @Column(name = "ma_ve", nullable = false, length = 14)
    private String maVe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_kh", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChiTietLichTrinh chiTietLichTrinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_ve", nullable = false)
    private LoaiVe loaiVe;

    @Nationalized
    @Column(name = "ten_kh", length = 30)
    private String tenKH;

    @Column(name = "socccd", length = 12)
    private String soCCCD;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Nationalized
    @ColumnDefault("'DaBan'")
    @Column(name = "tinh_trang_ve", length = 50)
    private String tinhTrangVe;

    @ColumnDefault("0")
    @Column(name = "khu_hoi", nullable = false)
    private Boolean khuHoi = false;

    public Ve() {
    }

    public Ve(String maVe) {
        this.maVe = maVe;
    }

    public Ve(String maVe, KhachHang khachHang, ChiTietLichTrinh chiTietLichTrinh, LoaiVe loaiVe, String tenKH, String soCCCD, LocalDate ngaySinh, String tinhTrangVe, Boolean khuHoi) {
        this.maVe = maVe;
        this.khachHang = khachHang;
        this.chiTietLichTrinh = chiTietLichTrinh;
        this.loaiVe = loaiVe;
        this.tenKH = tenKH;
        this.soCCCD = soCCCD;
        this.ngaySinh = ngaySinh;
        this.tinhTrangVe = tinhTrangVe;
        this.khuHoi = khuHoi;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LoaiVe getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(LoaiVe loaiVe) {
        this.loaiVe = loaiVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public ChiTietLichTrinh getChiTietLichTrinh() {
        return chiTietLichTrinh;
    }

    public void setChiTietLichTrinh(ChiTietLichTrinh chiTietLichTrinh) {
        this.chiTietLichTrinh = chiTietLichTrinh;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTinhTrangVe() {
        return tinhTrangVe;
    }

    public void setTinhTrangVe(String tinhTrangVe) {
        this.tinhTrangVe = tinhTrangVe;
    }

    public Boolean getKhuHoi() {
        return khuHoi;
    }

    public void setKhuHoi(Boolean khuHoi) {
        this.khuHoi = khuHoi;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ve ve = (Ve) o;
        return Objects.equals(maVe, ve.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }
}