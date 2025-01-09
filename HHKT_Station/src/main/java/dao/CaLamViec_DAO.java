package dao;

import entity.CaLamViec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CaLamViec_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public CaLamViec_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<CaLamViec> getAllCaLamViec() {
        String sql = "Select * from CaLamViec";
        return (ArrayList<CaLamViec>) em.createNativeQuery(sql, CaLamViec.class).getResultList();
    }

    public CaLamViec getCaLamViec(String maNV, LocalDateTime gioMoCa) {
        String sql = "Select * from CaLamViec where ma_nv = ? and gio_mo_ca = ?";
        return (CaLamViec) em.createNativeQuery(sql, CaLamViec.class).setParameter(1, maNV).setParameter(2, gioMoCa).getSingleResult();
    }

    public boolean create(CaLamViec caLamViec) {
        return executeTransaction(() -> em.persist(caLamViec));
    }

    public boolean update(CaLamViec caLamViec) {
        return executeTransaction(() -> em.merge(caLamViec));
    }

    public boolean delete(String maNV, LocalDateTime gioMoCa) {
        return executeTransaction(() -> {
            CaLamViec caLamViec = getCaLamViec(maNV, gioMoCa);
            em.remove(caLamViec);
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