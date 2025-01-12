package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Toa {
    @Id
    @Column(name = "ma_toa", nullable = false, length = 10)
    private String maToa;

    @Column(name = "stt_toa", columnDefinition = "tinyint not null")
    private Short sttToa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_hieu_tau", nullable = false)
    private ChuyenTau soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_toa", nullable = false)
    private LoaiToa loaiToa;

    public Toa() {
    }

    public Toa(String maToa) {
        this.maToa = maToa;
    }

    public Toa(String maToa, Short sttToa, ChuyenTau soHieuTau, LoaiToa loaiToa) {
        this.maToa = maToa;
        this.sttToa = sttToa;
        this.soHieuTau = soHieuTau;
        this.loaiToa = loaiToa;
    }

    public String getMaToa() {
        return maToa;
    }

    public void setMaToa(String maToa) {
        this.maToa = maToa;
    }

    public Short getSttToa() {
        return sttToa;
    }

    public void setSttToa(Short sttToa) {
        this.sttToa = sttToa;
    }

    public ChuyenTau getSoHieuTau() {
        return soHieuTau;
    }

    public void setSoHieuTau(ChuyenTau soHieuTau) {
        this.soHieuTau = soHieuTau;
    }

    public LoaiToa getLoaiToa() {
        return loaiToa;
    }

    public void setLoaiToa(LoaiToa loaiToa) {
        this.loaiToa = loaiToa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Toa toa = (Toa) o;
        return Objects.equals(maToa, toa.maToa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maToa);
    }
}