package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class LichTrinh {
    @Id
    @Column(name = "ma_lich_trinh", nullable = false, length = 20)
    private String maLichTrinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_hieu_tau", nullable = false)
    private ChuyenTau soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_ga_di", nullable = false)
    private Ga gaDi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_ga_den", nullable = false)
    private Ga gaDen;

    @Column(name = "thoi_gian_khoi_hanh", nullable = false)
    private LocalDateTime thoiGianKhoiHanh;

    @Column(name = "thoi_gian_du_kien_den", nullable = false)
    private LocalDateTime thoiGianDuKienDen;

    @ColumnDefault("1")
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    public LichTrinh() {
    }

    public LichTrinh(String maLichTrinh) {
        this.maLichTrinh = maLichTrinh;
    }

    public LichTrinh(String maLichTrinh, ChuyenTau soHieuTau, Ga gaDi, Ga gaDen, LocalDateTime thoiGianKhoiHanh, LocalDateTime thoiGianDuKienDen, Boolean trangThai) {
        this.maLichTrinh = maLichTrinh;
        this.soHieuTau = soHieuTau;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.thoiGianDuKienDen = thoiGianDuKienDen;
        this.trangThai = trangThai;
    }

    public String getMaLichTrinh() {
        return maLichTrinh;
    }

    public void setMaLichTrinh(String maLichTrinh) {
        this.maLichTrinh = maLichTrinh;
    }

    public ChuyenTau getSoHieuTau() {
        return soHieuTau;
    }

    public void setSoHieuTau(ChuyenTau soHieuTau) {
        this.soHieuTau = soHieuTau;
    }

    public Ga getGaDi() {
        return gaDi;
    }

    public void setGaDi(Ga gaDi) {
        this.gaDi = gaDi;
    }

    public Ga getGaDen() {
        return gaDen;
    }

    public void setGaDen(Ga gaDen) {
        this.gaDen = gaDen;
    }

    public LocalDateTime getThoiGianDuKienDen() {
        return thoiGianDuKienDen;
    }

    public void setThoiGianDuKienDen(LocalDateTime thoiGianDuKienDen) {
        this.thoiGianDuKienDen = thoiGianDuKienDen;
    }

    public LocalDateTime getThoiGianKhoiHanh() {
        return thoiGianKhoiHanh;
    }

    public void setThoiGianKhoiHanh(LocalDateTime thoiGianKhoiHanh) {
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LichTrinh lichTrinh = (LichTrinh) o;
        return Objects.equals(maLichTrinh, lichTrinh.maLichTrinh);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLichTrinh);
    }
}