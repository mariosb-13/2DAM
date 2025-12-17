package main;

import entities.Departamento;
import manager.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {
		//READ
		Departamento dep =EntityManagerUtil.getEntityManager().find(Departamento.class, 30);
		System.out.println(dep);
	}
}
