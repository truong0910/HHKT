package dao;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class CT_HoaDon_DAO {
    private final EntityManager em;

    public CT_HoaDon_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<ChiTietHoaDon> getAllCT_HoaDon() {
        String sql = "Select * from ChiTietHoaDon";
        return (ArrayList<ChiTietHoaDon>) em.createNativeQuery(sql, ChiTietHoaDon.class).getResultList();
    }

    public boolean create(ChiTietHoaDon cthd) {
        try {
            em.persist(cthd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ChiTietHoaDon cthd) {
        try {
            em.merge(cthd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(ChiTietHoaDon cthd) {
        try {
            em.remove(cthd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ChiTietHoaDon getCT_HoaDon(String maHD, String maVe) {
        String sql = "Select * from ChiTietHoaDon where ma_hd = ? and ma_ve = ?";
        return (ChiTietHoaDon) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD).setParameter(2, maVe).getSingleResult();
    }

    public ChiTietHoaDon getCT_HoaDonTheoMaVe(String maVe) {
        String sql = "Select * from ChiTietHoaDon where ma_ve = ?";
        return (ChiTietHoaDon) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maVe).getSingleResult();
    }

    public ArrayList<ChiTietHoaDon> getCT_HoaDon(String maHD) {
        String sql = "Select * from ChiTietHoaDon where ma_hd = ?";
        return (ArrayList<ChiTietHoaDon>) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD).getResultList();
    }
}

