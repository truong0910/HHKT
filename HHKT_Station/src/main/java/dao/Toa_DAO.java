package dao;

import entity.ChuyenTau;
import entity.LoaiToa;
import entity.Toa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class Toa_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public Toa_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<Toa> getAllToa() {
        String sql = "Select * from Toa";
        return (ArrayList<Toa>) em.createNativeQuery(sql, Toa.class).getResultList();
    }

    public ArrayList<Toa> getAllToaTheoChuyenTau(String soHieuTau) {
        String sql = "Select * from Toa where so_hieu_tau = ? order by stt_toa";
        return (ArrayList<Toa>) em.createNativeQuery(sql, Toa.class).setParameter(1, soHieuTau).getResultList();
    }

    public Toa getToaTheoID(String maToa) {
        String sql = "Select * from Toa where ma_toa = ?";
        return (Toa) em.createNativeQuery(sql, Toa.class).setParameter(1, maToa).getSingleResult();
    }

    public boolean create(Toa toa) {
        return executeTransaction(() -> em.persist(toa));
    }

    public boolean update(Toa toa) {
        return executeTransaction(() -> em.merge(toa));
    }

    public boolean delete(String maToa) {
        return executeTransaction(() -> {
            Toa toa = getToaTheoID(maToa);
            em.remove(toa);
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
}
