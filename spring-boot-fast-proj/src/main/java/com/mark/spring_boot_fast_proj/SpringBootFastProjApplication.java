package com.mark.spring_boot_fast_proj;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

@Entity(name = "coffee")
class Coffee {
	@Id
	private String id;
	private String name;

	public Coffee() {

	}
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

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
}

@Repository
interface CoffeeRepository extends CrudRepository<Coffee, String> {

}

@RestController
@RequestMapping("/coffees")
class RestApiController {
	private final CoffeeRepository coffeeRepository;

	public RestApiController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

	@GetMapping()
	Iterable<Coffee> getCoffees() {
		return coffeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		return coffeeRepository.findById(id);
	}

	@PostMapping()
	Coffee postCoffee(@RequestBody Coffee coffee) {
		return coffeeRepository.save(coffee);
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id,
									 @RequestBody Coffee coffee) {
		return (!coffeeRepository.existsById(id)) ?
				new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED) :
				new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		coffeeRepository.deleteById(id);
	}
}
