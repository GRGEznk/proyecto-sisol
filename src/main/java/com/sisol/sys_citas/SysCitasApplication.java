package com.sisol.sys_citas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;


@SpringBootApplication
public class SysCitasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SysCitasApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {

		// TEST: Imprimir todos los datos de la tabla usuario
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_hospital_v3", "root", "");
			 java.sql.Statement stmt = conn.createStatement();
			 java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM usuario")) {

			while (rs.next()) {
				System.out.println(
					"ID: " + rs.getLong("id") +
					", Rol: " + rs.getString("rol") +
					", Fecha Registro: " + rs.getString("fecha_registro") +
					", Fecha Actualización: " + rs.getString("fecha_actualizacion") +
					", Activo: " + rs.getInt("activo") +
					", Correo: " + rs.getString("correo") +
					", Contraseña: " + rs.getString("contrasenia")
				);
			}
		} catch (Exception e) {
			System.err.println("Error al consultar usuario: " + e.getMessage());
		}
	}

}