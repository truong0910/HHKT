package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "NhanVien", uniqueConstraints = {
        @UniqueConstraint(name = "UQ__socccd", columnNames = {"socccd"}),
        @UniqueConstraint(name = "UQ__sdt", columnNames = {"sdt"})
})
public class NhanVien {
    @Id
    @Column(name = "ma_nv", nullable = false, length = 11)
    private String maNV;

    @Nationalized
    @Column(name = "ten_nv", nullable = false, length = 50)
    private String tenNV;

    @Column(name = "socccd", nullable = false, length = 12)
    private String soCCCD;

    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh = false;

    @Column(name = "sdt", nullable = false, length = 10)
    private String sdt;

    @Column(name = "email", length = 100)
    private String email;

    @Nationalized
    @ColumnDefault("N'Nhân Viên'")
    @Column(name = "chuc_vu", nullable = false, length = 15)
    private String chucVu;

    @Nationalized
    @ColumnDefault("N'Đang làm'")
    @Column(name = "tinh_trang_cv", nullable = false, length = 15)
    private String tinhTrangCv;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String soCCCD, LocalDate ngaySinh, Boolean gioiTinh, String sdt, String email, String chucVu, String tinhTrangCv) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soCCCD = soCCCD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.chucVu = chucVu;
        this.tinhTrangCv = tinhTrangCv;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTinhTrangCv() {
        return tinhTrangCv;
    }

    public void setTinhTrangCv(String tinhTrangCv) {
        this.tinhTrangCv = tinhTrangCv;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(maNV, nhanVien.maNV);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maNV);
    }
}