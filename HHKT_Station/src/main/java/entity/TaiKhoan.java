package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
public class TaiKhoan {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nv", nullable = false)
    private NhanVien nhanVien;

    @Id
    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Nationalized
    @ColumnDefault("N'Đang hoạt động'")
    @Column(name = "trang_thai_tk", length = 20)
    private String trangThaiTK;

    public TaiKhoan() {
    }

    public TaiKhoan(NhanVien nhanVien, String matKhau, String trangThaiTK) {
        this.nhanVien = nhanVien;
        this.matKhau = matKhau;
        this.trangThaiTK = trangThaiTK;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTrangThaiTK() {
        return trangThaiTK;
    }

    public void setTrangThaiTK(String trangThaiTK) {
        this.trangThaiTK = trangThaiTK;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return Objects.equals(nhanVien, taiKhoan.nhanVien) && Objects.equals(matKhau, taiKhoan.matKhau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nhanVien, matKhau);
    }
}