package dao;

import entity.LoaiTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class LoaiTau_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public LoaiTau_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<LoaiTau> getAllLoaiTau() {
        String sql = "Select * from LoaiTau";
        return (ArrayList<LoaiTau>) em.createNativeQuery(sql, LoaiTau.class).getResultList();
    }

    public boolean create(LoaiTau loaiTau) {
        return executeTransaction(() -> em.persist(loaiTau));
    }

    public boolean update(LoaiTau loaiTau) {
        return executeTransaction(() -> em.merge(loaiTau));
    }

    public boolean delete(String maLoaiTau) {
        return executeTransaction(() -> {
            LoaiTau loaiTau = getLoaiTauTheoMa(maLoaiTau);
            em.remove(loaiTau);
        });
    }

    public LoaiTau getLoaiTauTheoMa(String maLoaiTau) {
        String sql = "Select * from LoaiTau where ma_loai_tau = ?";
        return (LoaiTau) em.createNativeQuery(sql, LoaiTau.class).setParameter(1, maLoaiTau).getSingleResult();
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
