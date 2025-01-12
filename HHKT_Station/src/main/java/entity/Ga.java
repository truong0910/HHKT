package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
@Table(name = "Ga", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ten_ga", columnNames = {"ten_ga"})
})
public class Ga {
    @Id
    @Column(name = "ma_ga", nullable = false, length = 10)
    private String maGa;

    @Nationalized
    @Column(name = "ten_ga", nullable = false, length = 50)
    private String tenGa;

    @Nationalized
    @Column(name = "vi_tri", length = 20)
    private String viTri;

    @Column(name = "khoang_cach", nullable = false)
    private Short khoangCach;

    public Ga() {
    }

    public Ga(String maGa) {
        this.maGa = maGa;
    }

    public Ga(String maGa, String tenGa, String viTri, Short khoangCach) {
        this.maGa = maGa;
        this.tenGa = tenGa;
        this.viTri = viTri;
        this.khoangCach = khoangCach;
    }

    public String getMaGa() {
        return maGa;
    }

    public void setMaGa(String maGa) {
        this.maGa = maGa;
    }

    public String getTenGa() {
        return tenGa;
    }

    public void setTenGa(String tenGa) {
        this.tenGa = tenGa;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Short getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(Short khoangCach) {
        this.khoangCach = khoangCach;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ga ga = (Ga) o;
        return Objects.equals(maGa, ga.maGa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maGa);
    }
}