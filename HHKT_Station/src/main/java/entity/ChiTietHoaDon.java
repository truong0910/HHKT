package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ChiTietHoaDon {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_hd", nullable = false)
    private entity.HoaDon hoaDon;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_ve", nullable = false)
    private entity.Ve ve;

    @Column(name = "gia_ve", nullable = false)
    private BigDecimal giaVe;

    @Column(name = "gia_giam", nullable = false)
    private BigDecimal giaGiam;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(HoaDon hoaDon, Ve ve) {
        this.hoaDon = hoaDon;
        this.ve = ve;
    }

    public ChiTietHoaDon(HoaDon hoaDon, Ve ve, BigDecimal giaVe, BigDecimal giaGiam) {
        this.hoaDon = hoaDon;
        this.ve = ve;
        this.giaVe = giaVe;
        this.giaGiam = giaGiam;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public BigDecimal getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(BigDecimal giaVe) {
        this.giaVe = giaVe;
    }

    public BigDecimal getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(BigDecimal giaGiam) {
        this.giaGiam = giaGiam;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(hoaDon, that.hoaDon) && Objects.equals(ve, that.ve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDon, ve);
    }
}