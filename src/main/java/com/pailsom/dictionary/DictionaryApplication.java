package com.pailsom.dictionary;

import com.pailsom.dictionary.controller.DictionaryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.pailsom.dictionary.controller",
		"com.pailsom.dictionary.repository",
		"com.pailsom.dictionary.service"})
public class DictionaryApplication {

	public static void main(String[] args) {

		new File(DictionaryController.uploadDirectory).mkdir();
		SpringApplication.run(DictionaryApplication.class, args);
	}

}
