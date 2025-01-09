package dao;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVien_DAO {
    private final EntityManager em;

    public NhanVien_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<NhanVien> getAll() {
        String sql = "Select * from NhanVien";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public boolean create(NhanVien nv) {
        em.persist(nv);
        return true;
    }

    public boolean updateTinhTrangCV(String maNV, String tinhTrangCV) {
        NhanVien nv = em.find(NhanVien.class, maNV);
        nv.setTinhTrangCv(tinhTrangCV);
        em.merge(nv);
        return true;
    }

    public ArrayList<NhanVien> getDSQuanLy() {
        String sql = "Select * from NhanVien where chuc_vu = 'Quản lý'";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public ArrayList<NhanVien> getDSNhanVien() {
        String sql = "Select * from NhanVien where chuc_vu = 'Nhân viên'";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public boolean updateInfo(NhanVien nv) {
        em.merge(nv);
        return true;
    }

    public NhanVien getNhanVien(String maNV) {
        return em.find(NhanVien.class, maNV);
    }
}
