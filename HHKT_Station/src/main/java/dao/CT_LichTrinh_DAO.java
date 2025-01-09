package dao;

import entity.*;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class CT_LichTrinh_DAO {
    private final EntityManager em;
    public CT_LichTrinh_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<ChiTietLichTrinh> getAllChiTietLichTrinh() {
        String sql = "Select * from ChiTietLichTrinh";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).getResultList();
    }

    public boolean create(ChiTietLichTrinh ctlt) {
        try {
            em.persist(ctlt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ChiTietLichTrinh ctlt) {
        try {
            em.merge(ctlt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateCTLT(ChiTietLichTrinh ctlt, boolean trangThai) {
        String sql = "Update ChiTietLichTrinh set trang_thai = ? where ma_cho = ? and ma_lich_trinh = ?";
        try {
            em.createNativeQuery(sql, ChiTietLichTrinh.class)
                    .setParameter(1, trangThai).setParameter(2, ctlt.getChoNgoi().getMaCho())
                    .setParameter(3, ctlt.getLichTrinh().getMaLichTrinh()).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maLT, String maCN) {
        try {
            ChiTietLichTrinh ctlt = getCTLTTheoCN(maLT, maCN);
            em.remove(ctlt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<ChiTietLichTrinh> getCtltTheoTrangThai(boolean trangThai) {
        String sql = "Select * from ChiTietLichTrinh where trang_thai = ?";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, trangThai).getResultList();
    }

    public ChiTietLichTrinh getCTLTTheoCN(String maLichTrinh, String maChoNgoi) {
        String sql = "Select * from ChiTietLichTrinh where ma_lich_trinh = ? and ma_cho = ?";
        return (ChiTietLichTrinh) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).setParameter(2, maChoNgoi).getSingleResult();
    }

    public boolean getTrangThaiCN(String maLichTrinh, String maCho) {
        String sql = "Select trang_thai from ChiTietLichTrinh where ma_lich_trinh = ? and ma_cho = ?";
        boolean trangThai = false;
        try {
            trangThai = (boolean) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).setParameter(2, maCho).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public void addChiTietLichTrinh(String maLichTrinh) {
        String sql = "INSERT INTO ChiTietLichTrinh (ma_cho, ma_lich_trinh, trang_thai, gia_cho) " +
                "SELECT cn.ma_cho, lt.ma_lich_trinh, lt.trang_thai, " +
                "CASE " +
                "    WHEN t.ma_loai_toa = 'NC' THEN CASE " +
                "        WHEN gaDen.khoang_cach = 0 THEN (\n" +
                "\t\t\t\t\tCASE \n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5\n" +
                "\t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2\n" +
                "\t\t\t\t\tEND)\n" +
                "                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1\n" +
                "                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25\n" +
                "                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5\n" +
                "                ELSE 500 * gaDen.khoang_cach * 2" +
                "    END " +
                "    WHEN t.ma_loai_toa = 'NM' THEN CASE " +
                "        WHEN gaDen.khoang_cach = 0 THEN (\n" +
                "\t\t\t\t\tCASE \n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.1\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.1\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.1\n" +
                "\t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.1\n" +
                "\t\t\t\t\tEND)\n" +
                "                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.1\n" +
                "                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.1\n" +
                "                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.1\n" +
                "                ELSE 500 * gaDen.khoang_cach * 2 * 1.1" +
                "    END " +
                "    WHEN t.ma_loai_toa = 'GNK6' THEN CASE " +
                "        WHEN gaDen.khoang_cach = 0 THEN (\n" +
                "\t\t\t\t\tCASE \n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.25\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.25\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.25\n" +
                "\t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.25\n" +
                "\t\t\t\t\tEND)\n" +
                "                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.25\n" +
                "                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.25\n" +
                "                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.25\n" +
                "                ELSE 500 * gaDen.khoang_cach * 2 * 1.25" +
                "    END " +
                "    WHEN t.ma_loai_toa = 'GNK4' THEN CASE " +
                "        WHEN gaDen.khoang_cach = 0 THEN (\n" +
                "\t\t\t\t\tCASE \n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.5\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.5\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.5\n" +
                "\t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.5\n" +
                "\t\t\t\t\tEND)\n" +
                "                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.5\n" +
                "                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.5\n" +
                "                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.5\n" +
                "                ELSE 500 * gaDen.khoang_cach * 2 * 1.5" +
                "    END " +
                "    WHEN t.ma_loai_toa = 'TVIP' THEN CASE " +
                "        WHEN gaDen.khoang_cach = 0 THEN (\n" +
                "\t\t\t\t\tCASE \n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 2\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 2\n" +
                "\t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 2\n" +
                "\t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 2\n" +
                "\t\t\t\t\tEND)\n" +
                "                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 2\n" +
                "                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 2\n" +
                "                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 2\n" +
                "                ELSE 500 * gaDen.khoang_cach * 2 * 2" +
                "    END " +
                "    ELSE 0 " +
                "END AS GiaCho " +
                "FROM ChoNgoi cn " +
                "JOIN LichTrinh lt ON lt.ma_lich_trinh = ? " +
                "JOIN Ga gaDen ON lt.ma_ga_den = gaDen.ma_ga " +
                "JOIN Toa t ON cn.ma_toa = t.ma_toa";
        try {
            em.createNativeQuery(sql)
                    .setParameter(1, maLichTrinh)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ChiTietLichTrinh> getCtltTheoMaLichTrinh(String maLichTrinh) {
        String sql = "Select * from ChiTietLichTrinh where ma_lich_trinh = ?";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).getResultList();
    }
}
