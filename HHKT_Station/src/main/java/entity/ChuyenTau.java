package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ChuyenTau {
    @Id
    @Column(name = "so_hieu_tau", nullable = false, length = 5)
    private String soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_tau", nullable = false)
    private entity.LoaiTau loaiTau;

    public ChuyenTau() {
    }

    public ChuyenTau(String soHieuTau) {
        this.soHieuTau = soHieuTau;
    }

    public ChuyenTau(String soHieuTau, LoaiTau loaiTau) {
        this.soHieuTau = soHieuTau;
        this.loaiTau = loaiTau;
    }

    public String getSoHieuTau() {
        return soHieuTau;
    }

    public void setSoHieuTau(String soHieuTau) {
        this.soHieuTau = soHieuTau;
    }

    public LoaiTau getLoaiTau() {
        return loaiTau;
    }

    public void setLoaiTau(LoaiTau loaiTau) {
        this.loaiTau = loaiTau;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChuyenTau chuyenTau = (ChuyenTau) o;
        return Objects.equals(soHieuTau, chuyenTau.soHieuTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(soHieuTau);
    }
}