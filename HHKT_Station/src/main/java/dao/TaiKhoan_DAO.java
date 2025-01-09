package dao;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;

public class TaiKhoan_DAO {
    private final EntityManager em;

    public TaiKhoan_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        String sql = "Select * from TaiKhoan";
        return (ArrayList<TaiKhoan>) em.createNativeQuery(sql, TaiKhoan.class).getResultList();
    }

    public boolean create(TaiKhoan tk) {
        try {
            em.persist(tk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maNhanVien, String trangThaiTK) {
        TaiKhoan tk = em.find(TaiKhoan.class, maNhanVien);
        try {
            em.remove(tk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(TaiKhoan tk) {
        try {
            em.merge(tk);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean doiMatKhau(String maNhanVien, String matKhauMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET mat_khau = ? WHERE ma_nv = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, matKhauMoi)
                    .setParameter(2, maNhanVien)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) {
        return em.find(TaiKhoan.class, maNhanVien);
    }

}
