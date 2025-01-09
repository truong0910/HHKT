package dao;

import entity.LoaiTau;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class LoaiTau_DAO {
    private final EntityManager em;

    public LoaiTau_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<LoaiTau> getAllLoaiTau() {
        String sql = "Select * from LoaiTau";
        return (ArrayList<LoaiTau>) em.createNativeQuery(sql, LoaiTau.class).getResultList();
    }

    public boolean create(LoaiTau loaiTau) {
        try {
            em.persist(loaiTau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(LoaiTau loaiTau) {
        try {
            em.merge(loaiTau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maLoaiTau) {
        try {
            LoaiTau loaiTau = getLoaiTauTheoMa(maLoaiTau);
            em.remove(loaiTau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public LoaiTau getLoaiTauTheoMa(String maLoaiTau) {
        String sql = "Select * from LoaiTau where ma_loai_tau = ?";
        return (LoaiTau) em.createNativeQuery(sql, LoaiTau.class).setParameter(1, maLoaiTau).getSingleResult();
    }
}
