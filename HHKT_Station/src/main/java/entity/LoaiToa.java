package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
public class LoaiToa {
    @Id
    @Column(name = "ma_loai_toa", nullable = false, length = 5)
    private String maLoaiToa;

    @Nationalized
    @Column(name = "ten_loai_toa", nullable = false, length = 30)
    private String tenLoaiToa;

    public LoaiToa() {
    }

    public LoaiToa(String maLoaiToa) {
        this.maLoaiToa = maLoaiToa;
    }

    public LoaiToa(String maLoaiToa, String tenLoaiToa) {
        this.maLoaiToa = maLoaiToa;
        this.tenLoaiToa = tenLoaiToa;
    }

    public String getMaLoaiToa() {
        return maLoaiToa;
    }

    public void setMaLoaiToa(String maLoaiToa) {
        this.maLoaiToa = maLoaiToa;
    }

    public String getTenLoaiToa() {
        return tenLoaiToa;
    }

    public void setTenLoaiToa(String tenLoaiToa) {
        this.tenLoaiToa = tenLoaiToa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiToa loaiToa = (LoaiToa) o;
        return Objects.equals(maLoaiToa, loaiToa.maLoaiToa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiToa);
    }
}