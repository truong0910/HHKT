package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhanVien", uniqueConstraints = {
        @UniqueConstraint(name = "UQ__socccd", columnNames = {"socccd"}),
        @UniqueConstraint(name = "UQ__sdt", columnNames = {"sdt"})
})
public class NhanVien {
    @Id
    @Column(name = "ma_nv", nullable = false, length = 11)
    private String maNV;

    @Nationalized
    @Column(name = "ten_nv", nullable = false, length = 50)
    private String tenNV;

    @Column(name = "socccd", nullable = false, length = 12)
    private String soCCCD;

    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh = false;

    @Column(name = "sdt", nullable = false, length = 10)
    private String sdt;

    @Column(name = "email", length = 100)
    private String email;

    @Nationalized
    @ColumnDefault("N'Nhân Viên'")
    @Column(name = "chuc_vu", nullable = false, length = 15)
    private String chucVu;

    @Nationalized
    @ColumnDefault("N'Đang làm'")
    @Column(name = "tinh_trang_cv", nullable = false, length = 15)
    private String tinhTrangCv;

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(maNV, nhanVien.maNV);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maNV);
    }
}