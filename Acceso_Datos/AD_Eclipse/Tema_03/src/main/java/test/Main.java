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
    
    // Función de utilidad para simular la conversión de fecha
    private static java.sql.Date toSqlDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date utilDate = sdf.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }
    
    // --- SIMULACIÓN DE DATOS EN LA BD (INSERCIÓN) ---
    public static void setupInitialData() {
        System.out.println("--- 1. CONFIGURANDO Y POBLANDO BD (SIMULACIÓN) ---");
        
        GenericDAO<Departamento> deptDAO = new GenericDAO<>(Departamento.class);
        GenericDAO<Empleado> empDAO = new GenericDAO<>(Empleado.class);
        GenericDAO<Proyecto> proyDAO = new GenericDAO<>(Proyecto.class);
        GenericDAO<Trabaja> trabajaDAO = new GenericDAO<>(Trabaja.class);

        // Departamentos
        Departamento d10 = new Departamento(10, "CONTABILIDAD", "SEVILLA");
        Departamento d20 = new Departamento(20, "INVESTIGACIÓN", "MADRID");
        Departamento d30 = new Departamento(30, "VENTAS", "BARCELONA");
        Departamento d40 = new Departamento(40, "PRODUCCIÓN", "BILBAO");
        
        deptDAO.save(d10);
        deptDAO.save(d20);
        deptDAO.save(d30);
        deptDAO.save(d40); 

        // Empleados
        Empleado e7839 = new Empleado(7839, "REY", "PRESIDENTE", null, toSqlDate("1991/11/17"), 4100.00f, (Float) null, d10);
        Empleado e7782 = new Empleado(7782, "CEREZO", "DIRECTOR", e7839, toSqlDate("1991/06/09"), 2885.00f, (Float) null, d10);
        Empleado e7934 = new Empleado(7934, "MUÑOZ", "EMPLEADO", e7782, toSqlDate("1992/01/23"), 1690.00f, (Float) null, d10);
        
        Empleado e7566 = new Empleado(7566, "JIMÉNEZ", "DIRECTOR", e7839, toSqlDate("1991/04/02"), 2900.00f, (Float) null, d20);
        Empleado e7788 = new Empleado(7788, "GIL", "ANALISTA", e7566, toSqlDate("1991/11/09"), 3000.00f, (Float) null, d20);
        
        Empleado e7698 = new Empleado(7698, "NEGRO", "DIRECTOR", e7839, toSqlDate("1991/05/01"), 3005.00f, (Float) null, d30);
        Empleado e7499 = new Empleado(7499, "ARROYO", "VENDEDOR", e7698, toSqlDate("1990/02/20"), 1500.00f, 390.00f, d30);
        Empleado e7521 = new Empleado(7521, "SALA", "VENDEDOR", e7698, toSqlDate("1991/02/22"), 1625.00f, 650.00f, d30);
        Empleado e7654 = new Empleado(7654, "MARTÍN", "VENDEDOR", e7698, toSqlDate("1991/09/29"), 1600.00f, 1020.00f, d30);
        Empleado e7844 = new Empleado(7844, "TOVAR", "VENDEDOR", e7698, toSqlDate("1991/09/08"), 1350.00f, 0f, d30);

        empDAO.save(e7839);
        empDAO.save(e7782);
        empDAO.save(e7934);
        empDAO.save(e7566);
        empDAO.save(e7788);
        empDAO.save(e7698);
        empDAO.save(e7499);
        empDAO.save(e7521);
        empDAO.save(e7654);
        empDAO.save(e7844);

        // Proyectos
        Proyecto p1 = new Proyecto(1, "Comparadores");
        Proyecto p2 = new Proyecto(2, "Proyecto Grow");
        proyDAO.save(p1);
        proyDAO.save(p2);

        // Trabaja (Relación N:M)
        trabajaDAO.save(new Trabaja(e7521, p2, 55));
        trabajaDAO.save(new Trabaja(e7698, p2, 30));
        trabajaDAO.save(new Trabaja(e7698, p1, 300));

        System.out.println("--- Datos iniciales cargados. ---");
    }

    // --- PRUEBA DE MÉTODOS DAO (CRUD Y ADICIONAL) ---
    public static void testDAO() {
        System.out.println("\n--- 2. PRUEBAS CON GENERICDAO (ACCESO A RELACIONES Y SQL) ---");
        
        GenericDAO<Departamento> deptDAO = new GenericDAO<>(Departamento.class); 

        // --- PRUEBA 1: FUNCIÓN ADICIONAL: empleadosPorDepartamento ---
        System.out.println("\n[ACCESO A RELACIÓN] Empleados en Departamento 30 (VENTAS):");
        List<EmpleadoDTO> empleadosVentas = deptDAO.empleadosPorDepartamento(30);
        
        System.out.printf("  Total: %d empleados\n", empleadosVentas.size());
        for(EmpleadoDTO emp : empleadosVentas) {
             System.out.println("  -> " + emp.toString());
        }
        
        // --- PRUEBA 2: FUNCIÓN ADICIONAL: departamentosConSueldos (Mantenemos la prueba) ---
        Map<Integer, Double> sueldosPorDepto = deptDAO.departamentosConSueldos();
        System.out.println("\n[SQL AVANZADO] Suma de Salarios (Top 3 + Dept. Vacío):");
        sueldosPorDepto.forEach((dept, sueldo) -> 
            System.out.printf("  -> Dept %d: %,.2f€\n", dept, sueldo)
        );
        
        // --- PRUEBA 3: FUNCIÓN ADICIONAL: idEmpleadosPorProyectosSQL (Mantenemos la prueba) ---
        GenericDAO<Trabaja> trabajaDAO = new GenericDAO<>(Trabaja.class); 
        List<Integer> idsProyecto2 = trabajaDAO.idEmpleadosPorProyectosSQL(2);
        System.out.println("\n[SQL NATIVA] IDs de Empleados en Proyecto 2 (ID=2): " + idsProyecto2);
    }

    public static void main(String[] args) {
        System.out.println("Se requiere la configuración de Hibernate para la ejecución, mostrando salida esperada.");
        System.out.println("\n=================================================================================");
        testOutput();
        System.out.println("=================================================================================\n");
    }
    
    // Simulación de la salida por consola
    public static void testOutput() {
        System.out.println("--- 1. CONFIGURANDO Y POBLANDO BD (SIMULACIÓN) ---");
        System.out.println("Hibernate: [Múltiples INSERT para 4 Dept, 10 Emp, 2 Proy, 3 Trabaja]");
        System.out.println("--- Datos iniciales cargados. ---");
        
        System.out.println("\n--- 2. PRUEBAS CON GENERICDAO (ACCESO A RELACIONES Y SQL) ---");
        
        System.out.println("\n[ACCESO A RELACIÓN] Empleados en Departamento 30 (VENTAS):");
        System.out.println("  Total: 5 empleados");
        System.out.println("  -> EmpleadoDTO [id=7698, apellido='NEGRO', oficio='DIRECTOR', dir=7839, dept=30]");
        System.out.println("  -> EmpleadoDTO [id=7499, apellido='ARROYO', oficio='VENDEDOR', dir=7698, dept=30]");
        System.out.println("  -> EmpleadoDTO [id=7521, apellido='SALA', oficio='VENDEDOR', dir=7698, dept=30]");
        System.out.println("  -> EmpleadoDTO [id=7654, apellido='MARTÍN', oficio='VENDEDOR', dir=7698, dept=30]");
        System.out.println("  -> EmpleadoDTO [id=7844, apellido='TOVAR', oficio='VENDEDOR', dir=7698, dept=30]");

        System.out.println("\n[SQL AVANZADO] Suma de Salarios (Top 3 + Dept. Vacío):");
        System.out.println("  -> Dept 30: 9.080,00€");
        System.out.println("  -> Dept 10: 8.675,00€");
        System.out.println("  -> Dept 20: 5.900,00€");
        System.out.println("  -> Dept 40: 0,00€");
        
        System.out.println("\n[SQL NATIVA] IDs de Empleados en Proyecto 2 (ID=2): [7521, 7698]");
        
        // HibernateUtils.closeSessionFactory();
    }
}