package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class KhachHang_DAO {
    private final EntityManager em;

    public KhachHang_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        String sql = "Select * from KhachHang";
        return (ArrayList<KhachHang>) em.createNativeQuery(sql, KhachHang.class).getResultList();
    }

    public boolean create(KhachHang kh) {
        try {
            em.persist(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(KhachHang kh) {
        try {
            em.merge(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maKH) {
        try {
            KhachHang kh = getKhachHangTheoMaKH(maKH);
            em.remove(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public KhachHang getKhachHangTheoMaKH(String maKH) {
        String sql = "Select * from KhachHang where ma_kh = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, maKH).getSingleResult();
    }

    public KhachHang getKHTheoCCCD(String soCCCD) {
        String sql = "Select * from KhachHang where socccd = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, soCCCD).getSingleResult();
    }

    public KhachHang getKhachHangTheoSDT(String sdt) {
        String sql = "Select * from KhachHang where sdt = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, sdt).getSingleResult();
    }
}


