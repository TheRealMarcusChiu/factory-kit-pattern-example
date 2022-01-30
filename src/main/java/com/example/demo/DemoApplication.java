package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.*;
import java.util.logging.Filter;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, World");
		IntSupplier t = "hello"::length;
		Consumer<Integer> function = System.out::println;
		Consumer<Builder> c  = null;
		Map<String, Supplier<Double>> map = new HashMap<>();
		c.accept(map::put);
		log.info("my name is HAHAHAHAHHAHAHAHAHAHHAHAHAHA\n\t{} and {}", new Integer(123), "Hello, World");
	}

	public interface Builder {
		void register(String str, Supplier<Double> supplier);
	}
}
