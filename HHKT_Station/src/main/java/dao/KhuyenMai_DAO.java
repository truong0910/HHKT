package dao;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhuyenMai_DAO {
    private final EntityManager em;

    public KhuyenMai_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<KhuyenMai> getAllKM() {
        String sql = "Select * from KhuyenMai";
        return (ArrayList<KhuyenMai>) em.createNativeQuery(sql, KhuyenMai.class).getResultList();
    }

    public ArrayList<KhuyenMai> getKMHienCo() {
        String sql = "Select * from KhuyenMai where trang_thai = 1";
        return (ArrayList<KhuyenMai>) em.createNativeQuery(sql, KhuyenMai.class).getResultList();
    }

    public KhuyenMai getKMTheoMa(String maKM) {
        String sql = "Select * from KhuyenMai where ma_km = ?";
        return (KhuyenMai) em.createNativeQuery(sql, KhuyenMai.class).setParameter(1, maKM).getSingleResult();
    }

    public KhuyenMai getKMGiamCaoNhat() {
        String sql = "Select * from KhuyenMai where muc_km = (Select max(muc_km) from KhuyenMai)";
        return (KhuyenMai) em.createNativeQuery(sql, KhuyenMai.class).getSingleResult();
    }

    public ArrayList<KhuyenMai> getKMTheoNgay(LocalDate ngay) {
        String sql = "Select * from KhuyenMai where ? between ngay_ap_dung and ngay_het_han";
        return (ArrayList<KhuyenMai>) em.createNativeQuery(sql, KhuyenMai.class).setParameter(1, Date.valueOf(ngay)).getResultList();
    }

    public boolean themKhuyenMai(KhuyenMai km) {
        try {
            em.persist(km);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean suaKhuyenMai(KhuyenMai km) {
        try {
            em.merge(km);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean xoaKhuyenMai(String maKM) {
        try {
            KhuyenMai km = getKMTheoMa(maKM);
            em.remove(km);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean kichHoatKhuyenMai() {
        String sql = "Select * from KhuyenMai where ngay_ap_dung <= ? and ngay_het_han >= ? and trang_thai = 0";
        em.createNativeQuery(sql, KhuyenMai.class)
                .setParameter(1, Date.valueOf(LocalDate.now()))
                .setParameter(2, Date.valueOf(LocalDate.now()))
                .getResultList();
        return true;
    }

    public boolean khoaKhuyenMai() {
        String sql = "Select * from KhuyenMai where ngay_het_han < ? and trang_thai = 1";
        em.createNativeQuery(sql, KhuyenMai.class)
                .setParameter(1, Date.valueOf(LocalDate.now()))
                .getResultList();
        return true;
    }

    public boolean capNhatTrangThaiKM(String maKM, boolean trangThai) {
        try {
            String sql = "UPDATE KhuyenMai SET trang_thai = ? WHERE ma_km = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, trangThai)
                    .setParameter(2, maKM)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
