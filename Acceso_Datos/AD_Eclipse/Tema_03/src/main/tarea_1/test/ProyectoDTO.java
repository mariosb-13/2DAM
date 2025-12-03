package test;

import java.util.ArrayList;
import java.util.List;

import entities.Proyecto;


public class ProyectoDTO {
    
    private Integer proyecto_no;
    private String pnombre;

    // Lista de empleados que trabajan en este proyecto
    private List<EmpleadoDTO> empleados = new ArrayList<>();
    
    // Lista de horas dedicadas por cada empleado (ordenada según la lista de 'empleados')
    private List<Integer> horasDedicadas = new ArrayList<>();

    // Constructor sin parámetros
    public ProyectoDTO() {
    }

    // Constructor con objeto entidad (Proyecto)
    public ProyectoDTO(Proyecto proyecto) {
        this.proyecto_no = proyecto.getProyecto_no();
        this.pnombre = proyecto.getPnombre();
    }
    
    // Getters y Setters
    public Integer getProyecto_no() { return proyecto_no; }
    public void setProyecto_no(Integer proyecto_no) { this.proyecto_no = proyecto_no; }
    public String getPnombre() { return pnombre; }
    public void setPnombre(String pnombre) { this.pnombre = pnombre; }
    public List<EmpleadoDTO> getEmpleados() { return empleados; }
    public void setEmpleados(List<EmpleadoDTO> empleados) { this.empleados = empleados; }
    public List<Integer> getHorasDedicadas() { return horasDedicadas; }
    public void setHorasDedicadas(List<Integer> horasDedicadas) { this.horasDedicadas = horasDedicadas; }

    @Override
    public String toString() {
        // Versión simplificada de toString
        String result = String.format("ProyectoDTO [ID=%d, Nombre='%s', Total Empleados=%d]\n",
                                      proyecto_no, pnombre, empleados.size());
        
        for (int i = 0; i < empleados.size(); i++) {
            EmpleadoDTO emp = empleados.get(i);
            Integer horas = (i < horasDedicadas.size()) ? horasDedicadas.get(i) : 0;
            
            result += String.format("    - Empleado [%d: %s] | Horas: %d\n", 
                                    emp.getEmp_no(), 
                                    emp.getApellido(), 
                                    horas);
        }
        return result;
    }
}