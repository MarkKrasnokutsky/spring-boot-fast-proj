package com.mark.spring_boot_fast_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

@RestController
class RestApiController {
	private List<Coffee> coffeeList = new ArrayList<>();

	public void RestApiDemoController() {
		coffeeList.addAll(List.of(
				new Coffee("Café Cereza"),
				new Coffee("Café Ganador"),
				new Coffee("Café Lareño"),
				new Coffee("Café Três Pontas")
		));
	}

	@GetMapping("/coffees")
	Iterable<Coffee> getCoffees() {
		return coffeeList;
	}
}
