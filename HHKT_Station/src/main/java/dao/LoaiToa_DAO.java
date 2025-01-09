package dao;

import entity.LoaiToa;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class LoaiToa_DAO {
    private final EntityManager em;

    public LoaiToa_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<LoaiToa> getAllLoaiToa() {
        String sql = "Select * from LoaiToa";
        return (ArrayList<LoaiToa>) em.createNativeQuery(sql, LoaiToa.class).getResultList();
    }

    public boolean create(LoaiToa loaiToa) {
        try {
            em.persist(loaiToa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(LoaiToa loaiToa) {
        try {
            em.merge(loaiToa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maLoaiToa) {
        try {
            LoaiToa loaiToa = getLoaiToaTheoMa(maLoaiToa);
            em.remove(loaiToa);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public LoaiToa getLoaiToaTheoMa(String maLoaiToa) {
        String sql = "Select * from LoaiToa where ma_loai_toa = ?";
        return (LoaiToa) em.createNativeQuery(sql, LoaiToa.class).setParameter(1, maLoaiToa).getSingleResult();
    }

    public boolean xoaLoaiToaTheoMa(String maLoaiToa) {
        try {
            String sql = "DELETE FROM LoaiToa WHERE ma_loai_toa = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, maLoaiToa)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
