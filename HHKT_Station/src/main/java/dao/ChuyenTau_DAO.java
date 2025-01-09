package dao;

import entity.ChuyenTau;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class ChuyenTau_DAO {
    private final EntityManager em;

    public ChuyenTau_DAO(EntityManager em) {
        this.em = em;
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
        try {
            em.persist(chuyenTau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ChuyenTau chuyenTau) {
        try {
            em.merge(chuyenTau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
