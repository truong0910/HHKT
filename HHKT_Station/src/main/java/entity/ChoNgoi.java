package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity
public class ChoNgoi {
    @Id
    @Column(name = "ma_cho", nullable = false, length = 15)
    private String maCho;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_toa", nullable = false)
    private entity.Toa toa;

    @Column(name = "stt_cho", columnDefinition = "tinyint not null")
    private Short sttCho;

    @ColumnDefault("1")
    @Column(name = "tang", columnDefinition = "tinyint")
    private Short tang;

    @ColumnDefault("1")
    @Column(name = "khoang", columnDefinition = "tinyint")
    private Short khoang;

    public ChoNgoi() {
    }

    public ChoNgoi(String maCho) {
        this.maCho = maCho;
    }

    public ChoNgoi(String maCho, entity.Toa toa, Short sttCho, Short tang, Short khoang) {
        this.maCho = maCho;
        this.toa = toa;
        this.sttCho = sttCho;
        this.tang = tang;
        this.khoang = khoang;
    }

    public String getMaCho() {
        return maCho;
    }

    public void setMaCho(String maCho) {
        this.maCho = maCho;
    }

    public entity.Toa getToa() {
        return toa;
    }

    public void setToa(entity.Toa toa) {
        this.toa = toa;
    }

    public Short getSttCho() {
        return sttCho;
    }

    public void setSttCho(Short sttCho) {
        this.sttCho = sttCho;
    }

    public Short getTang() {
        return tang;
    }

    public void setTang(Short tang) {
        this.tang = tang;
    }

    public Short getKhoang() {
        return khoang;
    }

    public void setKhoang(Short khoang) {
        this.khoang = khoang;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChoNgoi choNgoi = (ChoNgoi) o;
        return Objects.equals(maCho, choNgoi.maCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCho);
    }
}