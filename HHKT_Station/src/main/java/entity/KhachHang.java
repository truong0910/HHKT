package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public KhachHang(String maKH) {
        this.maKH = maKH;
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