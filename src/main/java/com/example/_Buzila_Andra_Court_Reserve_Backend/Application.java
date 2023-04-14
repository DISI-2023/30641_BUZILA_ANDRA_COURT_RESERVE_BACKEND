package com.example._Buzila_Andra_Court_Reserve_Backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//Mesaj de runtime:
		System.out.println("Project is running.");
	}
}
