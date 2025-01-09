package dao;

import entity.Ga;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class Ga_DAO {
    private final EntityManager em;

    public Ga_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<Ga> getAllGa() {
        String sql = "Select * from Ga";
        return (ArrayList<Ga>) em.createNativeQuery(sql, Ga.class).getResultList();
    }

    public boolean create(Ga ga) {
        try {
            em.persist(ga);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Ga ga) {
        try {
            em.merge(ga);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maGa) {
        try {
            Ga ga = getGaTheoMaGa(maGa);
            em.remove(ga);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
}   

