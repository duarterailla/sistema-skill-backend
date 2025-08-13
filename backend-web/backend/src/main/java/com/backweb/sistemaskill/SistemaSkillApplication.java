package com.backweb.sistemaskill;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaSkillApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaSkillApplication.class, args);
		System.out.println("Back rodando corretamente");
	}
	
	public void exemploJson() {
		String json = "{\n" +
				"  \"nome\": \"Java\",\n" +
				"  \"descricao\": \"Linguagem de programação\",\n" +
				"  \"imagem_url\": \"https://exemplo.com/java.png\"\n" +
				"}";
		System.out.println(json);
	}
}