package dao;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class Ve_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public Ve_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<Ve> getAllVe() {
        String sql = "Select * from Ve";
        return (ArrayList<Ve>) em.createNativeQuery(sql, Ve.class).getResultList();
    }

    public Ve getVeTheoID(String maVe) {
        String sql = "Select * from Ve where ma_ve = ?";
        return (Ve) em.createNativeQuery(sql, Ve.class).setParameter(1, maVe).getSingleResult();
    }

    public Ve getLaiVe(Ve ve) {
        String sql = "Select TOP 1 * from Ve where tinh_trang_ve = 'DaBan' ORDER BY ma_ve DESC";   // Lấy vé cuối cùng đã bán
        return (Ve) em.createNativeQuery(sql, Ve.class).getSingleResult();
    }

    public boolean create(Ve ve) {
        try {
            em.persist(ve);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Ve ve) {
        return executeTransaction(() -> em.merge(ve));
    }

    public boolean updateTinhTrangVe(String maVe, String tinhTrangVe) {
        return executeTransaction(() -> {
            Ve ve = getVeTheoID(maVe);
            ve.setTinhTrangVe(tinhTrangVe);
            em.merge(ve);
        });
    }

    public ArrayList<Ve> getDSVeTheoMaKH(String maKH) {
        String sql = "Select * from Ve where ma_kh = ?";
        return (ArrayList<Ve>) em.createNativeQuery(sql, Ve.class).setParameter(1, maKH).getResultList();
    }

    public ArrayList<Ve> getVeTheoTinhTrang(String tinhTrangVe) {
        String sql = "Select * from Ve where tinh_trang_ve = ?";
        return (ArrayList<Ve>) em.createNativeQuery(sql, Ve.class).setParameter(1, tinhTrangVe).getResultList();
    }

    private boolean executeTransaction(Runnable action) {
        try {
            transaction.begin();
            action.run();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
