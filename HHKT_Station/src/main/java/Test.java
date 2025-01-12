import entity.LoaiTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManager emf = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        EntityTransaction transaction = emf.getTransaction();
        transaction.begin();

        try {
            // Create a new Toa
            LoaiTau toa = new LoaiTau("LT002", "Loại tàu 2");
            emf.persist(toa);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
