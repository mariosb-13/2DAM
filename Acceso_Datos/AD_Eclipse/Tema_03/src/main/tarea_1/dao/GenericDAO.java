package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        session.saveOrUpdate(obj); 
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
    
    @SuppressWarnings("unchecked")
    public List<Integer> idEmpleadosPorProyectosSQL(int proyecto_no) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            // CORREGIDO: Usamos 'proyecto_no' que es el nombre real en la tabla
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
    
    
    public List<EmpleadoDTO> empleadosPorDepartamento(int dept_no) {
        Session session = HibernateUtils.openSession();
        List<EmpleadoDTO> dtoList = new ArrayList<>();
        
        try {
            session.beginTransaction();
            
            Departamento dept = session.get(Departamento.class, dept_no);
            
          
            if (dept != null && dept.empleados != null) {
                for (Empleado emp : dept.empleados) {
                    dtoList.add(emp.toDTO());
                }
            }
            
            session.getTransaction().commit();
            
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.err.println("Error obteniendo empleados por departamento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return dtoList;
    }
    
    /**
     * Retorna departamentos implicados en un proyecto.
     * NO usa SQL ni HQL para buscar departamentos.
     * Navega: Proyecto -> Trabaja -> (get Empleado) -> Departamento
     */
    public List<test.DepartamentoDTO> getDepartamentosImplicados(int proyecto_no) {
        Session session = HibernateUtils.openSession();
        // Usamos un Set para evitar departamentos duplicados automáticamente
        java.util.Set<test.DepartamentoDTO> deptSet = new java.util.HashSet<>();
        
        try {
            session.beginTransaction();
            
            // 1. Adquirimos SOLO el proyecto (punto de entrada)
            entities.Proyecto p = session.get(entities.Proyecto.class, proyecto_no);
            
            if (p != null) {
                // 2. Navegamos por la lista de trabajos (Lazy loading)
                // (Esto es posible gracias al cambio en Proyecto.java)
                for (entities.Trabaja t : p.getTrabajos()) {
                    
                    // 3. Obtenemos el empleado.
                    // Como 'Trabaja' solo tiene el ID (int), usamos session.get() para traer el objeto.
                    // Esto NO es una query de búsqueda, es una carga por PK.
                    entities.Empleado e = session.get(entities.Empleado.class, t.getEmp_no());
                    
                    // 4. Navegamos al departamento y lo convertimos a DTO
                    if (e != null && e.getDept() != null) {
                        deptSet.add(new test.DepartamentoDTO(e.getDept()));
                    }
                }
            }
            
            session.getTransaction().commit();
            
        } catch (Exception e) {
            System.err.println("Error en getDepartamentosImplicados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        
        // Convertimos el Set (sin duplicados) a la Lista que pide el enunciado
        return new ArrayList<>(deptSet);
    }
    
    
}