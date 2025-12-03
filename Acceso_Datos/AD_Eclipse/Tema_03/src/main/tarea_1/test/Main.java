package test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import dao.GenericDAO;
import entities.Departamento;
import entities.Empleado;
import entities.Proyecto;
import entities.Trabaja; 

public class Main {
    
    // Función de utilidad para convertir Strings a java.sql.Date
    private static java.sql.Date toSqlDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date utilDate = sdf.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }
    
    // --- 1. CONFIGURACIÓN Y CARGA DE DATOS ---
    public static void setupInitialData() {
        System.out.println("--- 1. INSERTANDO DATOS DE PRUEBA ---");
        
        // Instanciamos los DAOs necesarios
        GenericDAO<Departamento> deptDAO = new GenericDAO<>(Departamento.class);
        GenericDAO<Empleado> empDAO = new GenericDAO<>(Empleado.class);
        GenericDAO<Proyecto> proyDAO = new GenericDAO<>(Proyecto.class);
        GenericDAO<Trabaja> trabajaDAO = new GenericDAO<>(Trabaja.class);

        // 1. Crear Departamentos
        Departamento d10 = new Departamento(10, "CONTABILIDAD", "SEVILLA");
        Departamento d20 = new Departamento(20, "INVESTIGACIÓN", "MADRID");
        Departamento d30 = new Departamento(30, "VENTAS", "BARCELONA");
        Departamento d40 = new Departamento(40, "PRODUCCIÓN", "BILBAO");
        
        deptDAO.save(d10);
        deptDAO.save(d20);
        deptDAO.save(d30);
        deptDAO.save(d40); 

        // 2. Crear Empleados (Ojo al orden por las claves foráneas de los jefes)
        // Presidente
        Empleado e7839 = new Empleado(7839, "REY", "PRESIDENTE", null, toSqlDate("1991/11/17"), 4100.00f, null, d10);
        empDAO.save(e7839);

        // Directores / Analistas
        Empleado e7566 = new Empleado(7566, "JIMÉNEZ", "DIRECTOR", e7839, toSqlDate("1991/04/02"), 2900.00f, null, d20);
        Empleado e7698 = new Empleado(7698, "NEGRO", "DIRECTOR", e7839, toSqlDate("1991/05/01"), 3005.00f, null, d30);
        empDAO.save(e7566);
        empDAO.save(e7698);

        // Resto de empleados (Vendedores, etc.)
        Empleado e7499 = new Empleado(7499, "ARROYO", "VENDEDOR", e7698, toSqlDate("1990/02/20"), 1500.00f, 390.00f, d30);
        Empleado e7521 = new Empleado(7521, "SALA", "VENDEDOR", e7698, toSqlDate("1991/02/22"), 1625.00f, 650.00f, d30);
        Empleado e7654 = new Empleado(7654, "MARTÍN", "VENDEDOR", e7698, toSqlDate("1991/09/29"), 1600.00f, 1020.00f, d30);
        Empleado e7844 = new Empleado(7844, "TOVAR", "VENDEDOR", e7698, toSqlDate("1991/09/08"), 1350.00f, 0f, d30);
        
        empDAO.save(e7499);
        empDAO.save(e7521);
        empDAO.save(e7654);
        empDAO.save(e7844);

        // 3. Crear Proyectos
        Proyecto p1 = new Proyecto(1, "Comparadores");
        Proyecto p2 = new Proyecto(2, "Proyecto Grow");
        proyDAO.save(p1);
        proyDAO.save(p2);

        // 4. Crear Relación Trabaja (N:M)
        // Usamos el constructor especial que creamos en Trabaja.java para pasar objetos directamente
        trabajaDAO.save(new Trabaja(e7521, p2, 55));  // Sala trabaja en Grow
        trabajaDAO.save(new Trabaja(e7698, p2, 30));  // Negro trabaja en Grow
        trabajaDAO.save(new Trabaja(e7698, p1, 300)); // Negro trabaja en Comparadores

        System.out.println("--- Datos insertados correctamente. ---");
    }

    // --- 2. EJECUCIÓN DE PRUEBAS DE TUS MÉTODOS ---
    public static void testDAO() {
        System.out.println("\n--- 2. PRUEBAS DE MÉTODOS DEL DAO ---");
        
        GenericDAO<Departamento> deptDAO = new GenericDAO<>(Departamento.class);
        GenericDAO<Trabaja> trabajaDAO = new GenericDAO<>(Trabaja.class);

        // --- PRUEBA 1: empleadosPorDepartamento (Lazy Loading) ---
        System.out.println("\n[1] Empleados en Departamento 30 (VENTAS) - Vía relación:");
        List<EmpleadoDTO> empleadosVentas = deptDAO.empleadosPorDepartamento(30);
        
        System.out.printf("  Total encontrados: %d\n", empleadosVentas.size());
        for(EmpleadoDTO dto : empleadosVentas) {
             System.out.println("  -> " + dto.toString());
        }
        
        System.out.println("\n[2] Suma de Salarios por Departamento:");
        Map<Integer, Double> sueldos = deptDAO.departamentosConSueldos();
        
        for (Map.Entry<Integer, Double> entry : sueldos.entrySet()) {
            System.out.printf("  -> Dept %d: %,.2f€\n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("\n[3] IDs de Empleados en Proyecto 2 (ID=2):");
        List<Integer> idsProyecto2 = trabajaDAO.idEmpleadosPorProyectosSQL(2);
        System.out.println("  -> IDs encontrados: " + idsProyecto2);
    }

    public static void main(String[] args) {
        try {
            // 1. Preparar la BD
            setupInitialData();
            
            // 2. Ejecutar las consultas
            testDAO();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerramos la factoría de sesiones al terminar todo
            HibernateUtils.closeSessionFactory();
        }
    }
}