package dao;

import entity.LoaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class LoaiVe_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public LoaiVe_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<LoaiVe> getAllLoaiVe() {
        String sql = "Select * from LoaiVe";
        return (ArrayList<LoaiVe>) em.createNativeQuery(sql, LoaiVe.class).getResultList();
    }

    public boolean create(LoaiVe loaiVe) {
        return executeTransaction(() -> em.persist(loaiVe));
    }


    public boolean update(LoaiVe loaiVe) {
        return executeTransaction(() -> em.merge(loaiVe));
    }


    public boolean delete(String maLoaiVe) {
        return executeTransaction(() -> {
            LoaiVe loaiVe = getLoaiVeTheoMa(maLoaiVe);
            em.remove(loaiVe);
        });
    }

    public LoaiVe getLoaiVeTheoTen(String tenLoai) {
        String sql = "Select * from LoaiVe where ten_loai_ve = ?";
        return (LoaiVe) em.createNativeQuery(sql, LoaiVe.class).setParameter(1, tenLoai).getSingleResult();
    }

    public LoaiVe getLoaiVeTheoMa(String maLoaiVe) {
        String sql = "Select * from LoaiVe where ma_loai_ve = ?";
        return (LoaiVe) em.createNativeQuery(sql, LoaiVe.class).setParameter(1, maLoaiVe).getSingleResult();
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
