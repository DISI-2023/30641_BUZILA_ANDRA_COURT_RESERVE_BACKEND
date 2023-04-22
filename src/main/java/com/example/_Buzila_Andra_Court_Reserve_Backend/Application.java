package com.example._Buzila_Andra_Court_Reserve_Backend;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//Mesaj de runtime:
		System.out.println("Project is running.");

		//Test insert location in postman;
		//Test insert court in postman;
	}
}
