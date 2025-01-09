package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CaLamViec {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nv", nullable = false)
    private entity.NhanVien nhanVien;

    @Id
    @Column(name = "gio_mo_ca", nullable = false)
    private LocalDateTime gioMoCa;

    @Column(name = "gio_ket_ca")
    private LocalDateTime gioKetCa;

    @Column(name = "tien_dau_ca", nullable = false)
    private BigDecimal tienDauCa;

    @Column(name = "tien_ket_ca")
    private BigDecimal tienKetCa;

    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai_ca")
    private Boolean trangThaiCa;

    public CaLamViec() {
    }

    public CaLamViec(NhanVien nhanVien, LocalDateTime gioMoCa, LocalDateTime gioKetCa, BigDecimal tienDauCa, BigDecimal tienKetCa, String ghiChu, Boolean trangThaiCa) {
        this.nhanVien = nhanVien;
        this.gioMoCa = gioMoCa;
        this.gioKetCa = gioKetCa;
        this.tienDauCa = tienDauCa;
        this.tienKetCa = tienKetCa;
        this.ghiChu = ghiChu;
        this.trangThaiCa = trangThaiCa;
    }

    public LocalDateTime getGioMoCa() {
        return gioMoCa;
    }

    public void setGioMoCa(LocalDateTime gioMoCa) {
        this.gioMoCa = gioMoCa;
    }

    public LocalDateTime getGioKetCa() {
        return gioKetCa;
    }

    public void setGioKetCa(LocalDateTime gioKetCa) {
        this.gioKetCa = gioKetCa;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public BigDecimal getTienDauCa() {
        return tienDauCa;
    }

    public void setTienDauCa(BigDecimal tienDauCa) {
        this.tienDauCa = tienDauCa;
    }

    public BigDecimal getTienKetCa() {
        return tienKetCa;
    }

    public void setTienKetCa(BigDecimal tienKetCa) {
        this.tienKetCa = tienKetCa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean getTrangThaiCa() {
        return trangThaiCa;
    }

    public void setTrangThaiCa(Boolean trangThaiCa) {
        this.trangThaiCa = trangThaiCa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CaLamViec caLamViec = (CaLamViec) o;
        return Objects.equals(nhanVien, caLamViec.nhanVien) && Objects.equals(gioMoCa, caLamViec.gioMoCa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nhanVien, gioMoCa);
    }
}