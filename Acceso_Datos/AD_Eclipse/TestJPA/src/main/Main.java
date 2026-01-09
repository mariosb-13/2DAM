package main;

import entities.Proyecto;
import manager.EntityManagerUtil;
import javax.persistence.EntityManager;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fecha de depuración: " + new Date());
        
        // ESCENARIO 1: Gestión Manual con el EntityManager
        ejecutarEscenarioManual();

        System.out.println("\n------------------------------------------\n");

        // ESCENARIO 2: Gestión mediante EntityManagerUtil (Rollback)
        ejecutarEscenarioUtilRollback();
        
        EntityManagerUtil.release();
    }

    private static void ejecutarEscenarioManual() {
        System.out.println(">>> INICIO ESCENARIO 1 (Manual)");
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("[DEBUG] Transacción iniciada.");

            // Crear objeto
            Proyecto nuevo = new Proyecto();
            nuevo.setProyectoNo(100);
            nuevo.setPnombre("nuevo");

            // Persistir inicial
            em.persist(nuevo);
            System.out.println("Proyecto nuevo persistido.");

            // Modificar
            nuevo.setPnombre("renovado");
            em.persist(nuevo); 
            System.out.println("Proyecto nuevo modificado a renovado.");

            // Eliminar
            em.remove(nuevo);
            System.out.println("Proyecto eliminado.");

            em.getTransaction().commit();
            System.out.println("[DEBUG] Commit realizado.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private static void ejecutarEscenarioUtilRollback() {
        System.out.println(">>> INICIO ESCENARIO 2 (Utility con Rollback simulation)");
        try {
            Proyecto p2 = new Proyecto();
            p2.setProyectoNo(100);
            p2.setPnombre("nuevo");

            // Usando los métodos del util
            EntityManagerUtil.persist(p2);
            System.out.println("Proyecto 100 persistido via Util.");

            // Para simular el rollback pedido sobre los cambios:
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            
            p2.setPnombre("renovado");
            em.merge(p2); // Sincroniza estado
            
            System.out.println("[DEBUG] Forzando Rollback...");
            em.getTransaction().rollback();
            System.out.println("Cambios deshechos.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}