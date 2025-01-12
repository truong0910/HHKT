package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class KhuyenMai {
    @Id
    @Column(name = "ma_km", nullable = false, length = 11)
    private String maKM;

    @Nationalized
    @Column(name = "mo_ta", nullable = false, length = 50)
    private String moTa;

    @Column(name = "ngay_ap_dung", nullable = false)
    private LocalDate ngayApDung;

    @Column(name = "ngay_het_han", nullable = false)
    private LocalDate ngayHetHan;

    @Column(name = "muc_km", nullable = false)
    private Double mucKM;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    public KhuyenMai() {
    }

    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }

    public KhuyenMai(String maKM, String moTa, LocalDate ngayApDung, LocalDate ngayHetHan, Double mucKM, Boolean trangThai) {
        this.maKM = maKM;
        this.moTa = moTa;
        this.ngayApDung = ngayApDung;
        this.ngayHetHan = ngayHetHan;
        this.mucKM = mucKM;
        this.trangThai = trangThai;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(LocalDate ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Double getMucKM() {
        return mucKM;
    }

    public void setMucKM(Double mucKM) {
        this.mucKM = mucKM;
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
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(maKM, khuyenMai.maKM);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKM);
    }
}