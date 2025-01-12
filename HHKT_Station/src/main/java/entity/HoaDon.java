package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HoaDon {
    @Id
    @ColumnDefault("[dbo].[auto_idhd]()")
    @Column(name = "ma_hd", nullable = false, length = 14)
    private String maHD;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nv", nullable = false)
    private entity.NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_kh", nullable = false)
    private entity.KhachHang khachHang;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_lap_hoa_don", nullable = false)
    private LocalDateTime ngayLapHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_km")
    private entity.KhuyenMai khuyenMai;

    @Column(name = "tong_tien", nullable = false)
    private double tongTien;

    @Column(name = "tong_giam_gia", nullable = false)
    private double tongGiamGia;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(maHD, hoaDon.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHD);
    }
}