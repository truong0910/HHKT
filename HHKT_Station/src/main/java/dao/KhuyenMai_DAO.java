package dao;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhuyenMai_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public KhuyenMai_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<KhuyenMai> getAllKM() {
        return executeQuery("Select * from KhuyenMai");
    }

    public ArrayList<KhuyenMai> getKMHienCo() {
        return executeQuery("Select * from KhuyenMai where trang_thai = 1");
    }

    public KhuyenMai getKMTheoMa(String maKM) {
        return executeSingleResultQuery("Select * from KhuyenMai where ma_km = ?", maKM);
    }

    public KhuyenMai getKMGiamCaoNhat() {
        return executeSingleResultQuery("Select * from KhuyenMai where muc_km = (Select max(muc_km) from KhuyenMai)");
    }

    public ArrayList<KhuyenMai> getKMTheoNgay(LocalDate ngay) {
        return executeQuery("Select * from KhuyenMai where ? between ngay_ap_dung and ngay_het_han", Date.valueOf(ngay));
    }

    public boolean themKhuyenMai(KhuyenMai km) {
        return executeTransaction(() -> em.persist(km));
    }

    public boolean suaKhuyenMai(KhuyenMai km) {
        return executeTransaction(() -> em.merge(km));
    }

    public boolean xoaKhuyenMai(String maKM) {
        return executeTransaction(() -> em.remove(getKMTheoMa(maKM)));
    }

    public boolean kichHoatKhuyenMai() {
        return executeTransaction(() -> {
            String sql = "Select * from KhuyenMai where ngay_ap_dung <= ? and ngay_het_han >= ? and trang_thai = 0";
            em.createNativeQuery(sql, KhuyenMai.class)
                    .setParameter(1, Date.valueOf(LocalDate.now()))
                    .setParameter(2, Date.valueOf(LocalDate.now()))
                    .getResultList();
        });
    }

    public boolean khoaKhuyenMai() {
        return executeTransaction(() -> {
            String sql = "Select * from KhuyenMai where ngay_het_han < ? and trang_thai = 1";
            em.createNativeQuery(sql, KhuyenMai.class)
                    .setParameter(1, Date.valueOf(LocalDate.now()))
                    .getResultList();
        });
    }

    public boolean capNhatTrangThaiKM(String maKM, boolean trangThai) {
        return executeTransaction(() -> {
            String sql = "UPDATE KhuyenMai SET trang_thai = ? WHERE ma_km = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, trangThai)
                    .setParameter(2, maKM)
                    .executeUpdate();
        });
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

    private ArrayList<KhuyenMai> executeQuery(String sql, Object... params) {
        var query = em.createNativeQuery(sql, KhuyenMai.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return (ArrayList<KhuyenMai>) query.getResultList();
    }

    private KhuyenMai executeSingleResultQuery(String sql, Object... params) {
        var query = em.createNativeQuery(sql, KhuyenMai.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return (KhuyenMai) query.getSingleResult();
    }
}