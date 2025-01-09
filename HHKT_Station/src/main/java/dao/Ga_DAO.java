package dao;

import entity.Ga;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class Ga_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public Ga_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<Ga> getAllGa() {
        String sql = "Select * from Ga";
        return (ArrayList<Ga>) em.createNativeQuery(sql, Ga.class).getResultList();
    }

    public boolean create(Ga ga) {
        return executeTransaction(() -> em.persist(ga));
    }

    public boolean update(Ga ga) {
        return executeTransaction(() -> em.merge(ga));
    }

    public boolean delete(String maGa) {
        return executeTransaction(() -> {
            Ga ga = getGaTheoMaGa(maGa);
            em.remove(ga);
        });
    }

    public Ga getGaTheoMaGa(String maGa) {
        String sql = "Select * from Ga where ma_ga = ?";
        return (Ga) em.createNativeQuery(sql, Ga.class).setParameter(1, maGa).getSingleResult();
    }

    public Ga getGaTheoTenGa(String tenGa) {
        String sql = "Select * from Ga where ten_ga = ?";
        return (Ga) em.createNativeQuery(sql, Ga.class).setParameter(1, tenGa).getSingleResult();
    }

    public double KhoangCach(String maGa){
        String sql = "Select khoang_cach from Ga where ma_ga = ?";
        return (double) em.createNativeQuery(sql).setParameter(1, maGa).getSingleResult();
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

