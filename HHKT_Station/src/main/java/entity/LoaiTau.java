package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
public class LoaiTau {
    @Id
    @Column(name = "ma_loai_tau", nullable = false, length = 5)
    private String maLoaiTau;

    @Nationalized
    @Column(name = "ten_loai_tau", nullable = false, length = 30)
    private String tenLoaiTau;

    public LoaiTau() {
    }

    public LoaiTau(String maLoaiTau) {
        this.maLoaiTau = maLoaiTau;
    }

    public LoaiTau(String maLoaiTau, String tenLoaiTau) {
        this.maLoaiTau = maLoaiTau;
        this.tenLoaiTau = tenLoaiTau;
    }

    public String getMaLoaiTau() {
        return maLoaiTau;
    }

    public void setMaLoaiTau(String maLoaiTau) {
        this.maLoaiTau = maLoaiTau;
    }

    public String getTenLoaiTau() {
        return tenLoaiTau;
    }

    public void setTenLoaiTau(String tenLoaiTau) {
        this.tenLoaiTau = tenLoaiTau;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiTau loaiTau = (LoaiTau) o;
        return Objects.equals(maLoaiTau, loaiTau.maLoaiTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiTau);
    }
}