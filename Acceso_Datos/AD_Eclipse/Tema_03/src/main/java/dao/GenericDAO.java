package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Departamento;
import entities.Empleado;
import test.EmpleadoDTO;
import test.HibernateUtils;

public class GenericDAO<T> {
	Class<T> type;

    @SuppressWarnings("deprecation")
	public GenericDAO(Class<T> obj) {
		this.type = obj;
	}

	public T get(int id) {
		Session session = HibernateUtils.openSession();
		T obj = session.get(type, id);
		session.close();
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<T> objList = (List<T>) session.createCriteria(type).list();
		session.getTransaction().commit();
		session.close();
		return objList;
	}

	public void save(T obj) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	public void update(T obj) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(T obj) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}
    
    // --- FUNCIÓN ADICIONAL 1: IDs de Empleados por Proyecto (SQL Nativa) ---
    @SuppressWarnings("unchecked")
    public List<Integer> idEmpleadosPorProyectosSQL(int proyecto_no) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            String sql = "SELECT emp_no FROM trabaja WHERE proyecto_no = :id";
            
            Query<Integer> query = session.createNativeQuery(sql, Integer.class);
            query.setParameter("id", proyecto_no);
            
            List<Integer> ids = query.getResultList();
            transaction.commit();
            
            return ids;
                          
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error ejecutando consulta SQL nativa: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    // --- FUNCIÓN ADICIONAL 2: Suma de Sueldos por Departamento (SQL Nativa) ---
    @SuppressWarnings("unchecked")
    public Map<Integer, Double> departamentosConSueldos() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        Map<Integer, Double> resultados = new LinkedHashMap<>();
        
        try {
            transaction = session.beginTransaction();
            
            String sql = "SELECT d.dept_no, COALESCE(SUM(e.salario), 0) AS suma_sueldo " +
                         "FROM departamento d " +
                         "LEFT JOIN empleado e ON d.dept_no = e.dept_no " +
                         "GROUP BY d.dept_no " +
                         "ORDER BY suma_sueldo DESC";
            
            Query<Object[]> query = session.createNativeQuery(sql);
            
            List<Object[]> listaResultados = query.getResultList();
            transaction.commit();

            for (Object[] fila : listaResultados) {
                Integer deptNo = ((Number) fila[0]).intValue();
                Double sumaSueldo = ((Number) fila[1]).doubleValue();
                resultados.put(deptNo, sumaSueldo);
            }
            
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al ejecutar departamentosConSueldos(): " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resultados;
    }
    
    // --- FUNCIÓN ADICIONAL 3: Empleados por Departamento (Acceso a relación) ---
    
    /**
     * Retorna una lista de EmpleadoDTOs de un departamento específico 
     * accediendo a través de la relación bidireccional de la entidad Departamento.
     * @param dept_no ID del departamento.
     * @return Lista de EmpleadoDTOs.
     */
    public List<EmpleadoDTO> empleadosPorDepartamento(int dept_no) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        List<EmpleadoDTO> dtoList = new ArrayList<>();
        
        try {
            transaction = session.beginTransaction();
            
            // 1. Obtener el Departamento
            Departamento dept = session.get(Departamento.class, dept_no);
            
            if (dept != null) {
                // 2. Acceder a la lista de empleados (Lazy Loading se activa aquí)
                // Usamos el .stream().map() para convertir la Set<Empleado> a List<EmpleadoDTO>
                dtoList = dept.getEmpleados().stream() 
                                .map(Empleado::toDTO)
                                .collect(Collectors.toList());
            }
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error al acceder a empleadosPorDepartamento: " + e.getMessage());
        } finally {
            // Es vital cerrar la sesión DESPUÉS de acceder a la colección si no se usa EAGER
            // Si la colección se intenta acceder después de session.close(), lanzará LazyInitializationException
            if (session != null) {
                session.close();
            }
        }
        return dtoList;
    }
}