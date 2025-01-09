package dao;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class TaiKhoan_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public TaiKhoan_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        String sql = "Select * from TaiKhoan";
        return (ArrayList<TaiKhoan>) em.createNativeQuery(sql, TaiKhoan.class).getResultList();
    }

    public boolean create(TaiKhoan tk) {
        return executeTransaction(() -> em.persist(tk));
    }

    public boolean delete(String maNhanVien, String trangThaiTK) {
        return executeTransaction(() -> {
            TaiKhoan tk = getTaiKhoanTheoMaNV(maNhanVien);
            tk.setTrangThaiTK(trangThaiTK);
            em.merge(tk);
        });
    }

    public boolean update(TaiKhoan tk) {
        return executeTransaction(() -> em.merge(tk));
    }

    public boolean doiMatKhau(String maNhanVien, String matKhauMoi) {
        return executeTransaction(() -> {
            TaiKhoan tk = getTaiKhoanTheoMaNV(maNhanVien);
            tk.setMatKhau(matKhauMoi);
            em.merge(tk);
        });
    }

    public TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) {
        return em.find(TaiKhoan.class, maNhanVien);
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
