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
public class Ve {
    @Id
    @ColumnDefault("[dbo].[auto_idve]()")
    @Column(name = "ma_ve", nullable = false, length = 14)
    private String maVe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_kh", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChiTietLichTrinh chiTietLichTrinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_ve", nullable = false)
    private LoaiVe loaiVe;

    @Nationalized
    @Column(name = "ten_kh", length = 30)
    private String tenKH;

    @Column(name = "socccd", length = 12)
    private String soCCCD;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Nationalized
    @ColumnDefault("'DaBan'")
    @Column(name = "tinh_trang_ve", length = 50)
    private String tinhTrangVe;

    @ColumnDefault("0")
    @Column(name = "khu_hoi", nullable = false)
    private Boolean khuHoi = false;

    public Ve(String maVe) {
        this.maVe = maVe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ve ve = (Ve) o;
        return Objects.equals(maVe, ve.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }
}