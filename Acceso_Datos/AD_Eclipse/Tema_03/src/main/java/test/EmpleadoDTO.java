package test;

import java.sql.Date;

import entities.Empleado;

/**
 * DTO (Data Transfer Object) para la entidad Empleado.
 * No contiene anotaciones de persistencia ni referencias a otras entidades DTO.
 */
public class EmpleadoDTO {
    
    private Integer emp_no; // PRIMARY KEY
    private String apellido;
    private String oficio;
    private Integer dir; // Clave del director
    private Date fecha_alt;
    private Float salario;
    private Float comision;
    private Integer dept; // Clave del departamento

    // Constructor sin parámetros
    public EmpleadoDTO() { }

    // Constructor con parámetros (tipos primitivos)
    public EmpleadoDTO(Integer emp_no, String apellido, String oficio, Integer dir, Date fecha_alt, Float salario, Float comision, Integer dept) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept = dept;
    }

    // Constructor con objeto entidad (Empleado)
    public EmpleadoDTO(Empleado emp) {
        this.emp_no = emp.getEmp_no(); 
        this.apellido = emp.getApellido();
        this.oficio = emp.getOficio(); 
        
        // Manejo de la relación con el director (dir)
        if (emp.getDir() != null) {
            this.dir = emp.getDir().getEmp_no();
        } else {
            this.dir = null;
        }
        
        this.fecha_alt = emp.getFecha_alt(); 
        this.salario = emp.getSalario();
        this.comision = emp.getComision();
        
        // Manejo de la relación con el departamento (dept)
        if (emp.getDept() != null) {
            this.dept = emp.getDept().getDept_no();
        } else {
             // Esto no debería pasar si la FK es NOT NULL, pero se incluye por seguridad
            this.dept = null; 
        }
    }

    // Getters
    public Integer getEmp_no() { return emp_no; }
    public String getApellido() { return apellido; }
    public String getOficio() { return oficio; }
    public Integer getDir() { return dir; }
    public Date getFecha_alt() { return fecha_alt; }
    public Float getSalario() { return salario; }
    public Float getComision() { return comision; }
    public Integer getDept() { return dept; }
    
    // Setters
    public void setEmp_no(Integer emp_no) { this.emp_no = emp_no; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setOficio(String oficio) { this.oficio = oficio; }
    public void setDir(Integer dir) { this.dir = dir; }
    public void setFecha_alt(Date fecha_alt) { this.fecha_alt = fecha_alt; }
    public void setSalario(Float salario) { this.salario = salario; }
    public void setComision(Float comision) { this.comision = comision; }
    public void setDept(Integer dept) { this.dept = dept; }


    @Override
    public String toString() {
        return "EmpleadoDTO [" +
                "emp_no=" + emp_no +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", dir=" + (dir == null ? "null" : dir) +
                ", dept=" + (dept == null ? "null" : dept) +
                ", salario=" + salario +
                ']';
    }
}