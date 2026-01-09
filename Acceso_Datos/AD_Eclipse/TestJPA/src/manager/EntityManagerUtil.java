package manager;

import javax.persistence.*;
import java.util.*;
import entities.*;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestJPA");
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) em = emf.createEntityManager();
        return em;
    }

    public static void release() {
        if (em != null && em.isOpen()) em.close();
        if (emf != null && emf.isOpen()) emf.close();
    }

    public static boolean persist(Object obj) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    public static boolean delete(Object obj) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(obj) ? obj : em.merge(obj));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return false;
        }
    }

    public static List<Integer> idEmpleadosPorProyectosSQL(int proyecto_no) {
        String sql = "SELECT emp_no FROM trabaja WHERE proyecto_no = ?1";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, proyecto_no);
        return query.getResultList();
    }

    public static HashMap<Integer, Double> departamentosConSueldos() {
        String jpql = "SELECT d.dept_no, SUM(COALESCE(e.salario, 0)) as sumasueldo " +
                      "FROM Departamento d LEFT JOIN d.empleados e " +
                      "GROUP BY d.dept_no ORDER BY sumasueldo DESC";
        List<Object[]> res = getEntityManager().createQuery(jpql).getResultList();
        HashMap<Integer, Double> map = new LinkedHashMap<>();
        for (Object[] r : res) map.put(((Number)r[0]).intValue(), ((Number)r[1]).doubleValue());
        return map;
    }

    public static List<Empleado> empleadosPorDepartamento(int dept_no) {
        TypedQuery<Empleado> q = getEntityManager().createNamedQuery("Empleado.empleadosPorDepto", Empleado.class);
        q.setParameter("dept_no", dept_no);
        return q.getResultList();
    }

    public static List<Departamento> getDepartamentosImplicados(int proyecto_no) {
        TypedQuery<Departamento> q = getEntityManager().createNamedQuery("Departamento.getDepartamentosImplicados", Departamento.class);
        q.setParameter("proyecto_no", proyecto_no);
        return q.getResultList();
    }
}