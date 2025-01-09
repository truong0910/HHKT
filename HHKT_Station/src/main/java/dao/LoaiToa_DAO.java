package dao;

import entity.LoaiToa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class LoaiToa_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public LoaiToa_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<LoaiToa> getAllLoaiToa() {
        String sql = "Select * from LoaiToa";
        return (ArrayList<LoaiToa>) em.createNativeQuery(sql, LoaiToa.class).getResultList();
    }

    public boolean create(LoaiToa loaiToa) {
        return executeTransaction(() -> em.persist(loaiToa));
    }

    public boolean update(LoaiToa loaiToa) {
        return executeTransaction(() -> em.merge(loaiToa));
    }

    public boolean delete(String maLoaiToa) {
        return executeTransaction(() -> {
            LoaiToa loaiToa = getLoaiToaTheoMa(maLoaiToa);
            em.remove(loaiToa);
        });
    }

    public LoaiToa getLoaiToaTheoMa(String maLoaiToa) {
        String sql = "Select * from LoaiToa where ma_loai_toa = ?";
        return (LoaiToa) em.createNativeQuery(sql, LoaiToa.class).setParameter(1, maLoaiToa).getSingleResult();
    }

    public boolean xoaLoaiToaTheoMa(String maLoaiToa) {
        return executeTransaction(() -> {
            LoaiToa loaiToa = getLoaiToaTheoMa(maLoaiToa);
            em.remove(loaiToa);
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
