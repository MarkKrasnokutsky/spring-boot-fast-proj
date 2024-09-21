package com.mark.spring_boot_fast_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class SpringBootFastProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFastProjApplication.class, args);
		System.out.println("Hello World!");
	}
}

class Coffee {
	private final String id;
	private String name;

	public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

	public Coffee(String name) {
		this(UUID.randomUUID().toString(), name);
	}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	public void SetName(String name) {
		this.name = name;
	}
}
