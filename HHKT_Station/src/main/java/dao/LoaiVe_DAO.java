package dao;

import entity.LoaiVe;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class LoaiVe_DAO {
    private final EntityManager em;

    public LoaiVe_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<LoaiVe> getAllLoaiVe() {
        String sql = "Select * from LoaiVe";
        return (ArrayList<LoaiVe>) em.createNativeQuery(sql, LoaiVe.class).getResultList();
    }

    public boolean create(LoaiVe loaiVe) {
        try {
            em.persist(loaiVe);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean update(LoaiVe loaiVe) {
        try {
            em.merge(loaiVe);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean delete(String maLoaiVe) {
        try {
            LoaiVe loaiVe = getLoaiVeTheoMa(maLoaiVe);
            em.remove(loaiVe);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public LoaiVe getLoaiVeTheoTen(String tenLoai) {
        String sql = "Select * from LoaiVe where ten_loai_ve = ?";
        return (LoaiVe) em.createNativeQuery(sql, LoaiVe.class).setParameter(1, tenLoai).getSingleResult();
    }

    public LoaiVe getLoaiVeTheoMa(String maLoaiVe) {
        String sql = "Select * from LoaiVe where ma_loai_ve = ?";
        return (LoaiVe) em.createNativeQuery(sql, LoaiVe.class).setParameter(1, maLoaiVe).getSingleResult();
    }
}
