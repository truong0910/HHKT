package dao;

import entity.ChuyenTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ChuyenTau_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public ChuyenTau_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<ChuyenTau> getAll() {
        String sql = "Select * from ChuyenTau";
        return (ArrayList<ChuyenTau>) em.createNativeQuery(sql, ChuyenTau.class).getResultList();
    }

    public ChuyenTau getChuyenTauTheoID(String soHieuTau) {
        String sql = "Select * from ChuyenTau where so_hieu_tau = ?";
        return (ChuyenTau) em.createNativeQuery(sql, ChuyenTau.class).setParameter(1, soHieuTau).getSingleResult();
    }



    public boolean create(ChuyenTau chuyenTau) {
        return executeTransaction(() -> em.persist(chuyenTau));
    }

    public boolean update(ChuyenTau chuyenTau) {
        return executeTransaction(() -> em.merge(chuyenTau));
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
