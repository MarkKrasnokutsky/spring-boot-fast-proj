package com.mark.spring_boot_fast_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

	@GetMapping("/coffees/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		for (Coffee c: coffeeList) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
}
