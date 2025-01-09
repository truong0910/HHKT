package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
public class LoaiVe {
    @Id
    @Column(name = "ma_loai_ve", nullable = false, length = 10)
    private String maLoaiVe;

    @Nationalized
    @Column(name = "ten_loai_ve", nullable = false, length = 50)
    private String tenLoaiVe;

    @Column(name = "muc_giam_gia", nullable = false)
    private Double mucGiamGia;

    public LoaiVe() {
    }

    public LoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    public LoaiVe(String maLoaiVe, String tenLoaiVe, Double mucGiamGia) {
        this.maLoaiVe = maLoaiVe;
        this.tenLoaiVe = tenLoaiVe;
        this.mucGiamGia = mucGiamGia;
    }
    
    public String getMaLoaiVe() {
        return maLoaiVe;
    }

    public void setMaLoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    public String getTenLoaiVe() {
        return tenLoaiVe;
    }

    public void setTenLoaiVe(String tenLoaiVe) {
        this.tenLoaiVe = tenLoaiVe;
    }

    public Double getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(Double mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiVe loaiVe = (LoaiVe) o;
        return Objects.equals(maLoaiVe, loaiVe.maLoaiVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiVe);
    }
}