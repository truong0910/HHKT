package dao;

import entity.ChiTietLichTrinh;
import entity.ChoNgoi;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class ChoNgoi_DAO {
    private final EntityManager em;

    public ChoNgoi_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<ChoNgoi> getAllChoNgoi() {
        String sql = "Select * from ChoNgoi";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).getResultList();
    }

    public ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) {
        String sql = "Select * from ChoNgoi where ma_toa = ? and stt_cho = ?";
        return (ChoNgoi) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).setParameter(2, sttCho).getSingleResult();
    }

    public ChoNgoi getChoNgoiTheoMa(String maCho) {
        String sql = "Select * from ChoNgoi where ma_cho = ?";
        return (ChoNgoi) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maCho).getSingleResult();
    }

    public ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        String sql = "Select * from ChoNgoi where ma_toa = ?";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).getResultList();
    }

    public ArrayList<ChoNgoi> getChoNgoiDaDat(ChiTietLichTrinh chiTietLichTrinh) {
        String sql = "Select * from ChoNgoi a join ChiTietLichTrinh b on a.ma_cho = b.ma_cho where trang_thai = 0";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).getResultList();
    }

    public boolean create(ChoNgoi choNgoi) {
        try {
            em.persist(choNgoi);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ChoNgoi choNgoi) {
        try {
            em.merge(choNgoi);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maChoNgoi) {
        try {
            ChoNgoi choNgoi = getChoNgoiTheoMa(maChoNgoi);
            em.remove(choNgoi);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<ChoNgoi> getDsChoNgoiTheoToa(String maToa) {
        String sql = "Select * from ChoNgoi where ma_toa = ?";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).getResultList();
    }
}
