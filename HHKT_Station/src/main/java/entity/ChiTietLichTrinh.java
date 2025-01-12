package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ChiTietLichTrinh {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_cho", nullable = false)
    private entity.ChoNgoi choNgoi;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_lich_trinh", nullable = false)
    private entity.LichTrinh lichTrinh;

    @ColumnDefault("1")
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    @Column(name = "gia_cho", nullable = false)
    private BigDecimal giaCho;

    public ChiTietLichTrinh() {
    }

    public ChiTietLichTrinh(ChoNgoi choNgoi, LichTrinh lichTrinh) {
        this.choNgoi = choNgoi;
        this.lichTrinh = lichTrinh;
    }

    public ChiTietLichTrinh(ChoNgoi choNgoi, LichTrinh lichTrinh, Boolean trangThai, BigDecimal giaCho) {
        this.choNgoi = choNgoi;
        this.lichTrinh = lichTrinh;
        this.trangThai = trangThai;
        this.giaCho = giaCho;
    }

    public ChoNgoi getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(ChoNgoi choNgoi) {
        this.choNgoi = choNgoi;
    }

    public LichTrinh getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(LichTrinh lichTrinh) {
        this.lichTrinh = lichTrinh;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getGiaCho() {
        return giaCho;
    }

    public void setGiaCho(BigDecimal giaCho) {
        this.giaCho = giaCho;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietLichTrinh that = (ChiTietLichTrinh) o;
        return Objects.equals(choNgoi, that.choNgoi) && Objects.equals(lichTrinh, that.lichTrinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choNgoi, lichTrinh);
    }
}