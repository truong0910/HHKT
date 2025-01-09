package dao;

import entity.ChuyenTau;
import entity.LoaiToa;
import entity.Toa;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class Toa_DAO {
    private final EntityManager em;

    public Toa_DAO(EntityManager em) {
        this.em = em;
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
        try {
            em.persist(toa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Toa toa) {
        try {
            em.merge(toa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maToa) {
        try {
            Toa toa = getToaTheoID(maToa);
            em.remove(toa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
