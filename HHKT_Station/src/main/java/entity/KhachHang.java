package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Entity
@Table(name = "KhachHang", uniqueConstraints = {
        @UniqueConstraint(name = "UQ__socccd", columnNames = {"socccd"}),
        @UniqueConstraint(name = "UQ__sdt", columnNames = {"sdt"})
})
public class KhachHang {
    @Id
    @ColumnDefault("[dbo].[auto_idkh]()")
    @Column(name = "ma_kh", nullable = false, length = 10)
    private String maKH;

    @Nationalized
    @Column(name = "ten_kh", nullable = false, length = 50)
    private String tenKH;

    @Column(name = "socccd", nullable = false, length = 12)
    private String soCCCD;

    @Column(name = "sdt", nullable = false, length = 10)
    private String sdt;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public KhachHang() {
    }

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang(String email, String sdt, String soCCCD, String tenKH, String maKH) {
        this.email = email;
        this.sdt = sdt;
        this.soCCCD = soCCCD;
        this.tenKH = tenKH;
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(maKH, khachHang.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKH);
    }
}