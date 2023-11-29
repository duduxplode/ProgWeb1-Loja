package br.ueg.loja;

import br.ueg.loja.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"br.ueg.loja.*",
				//Para funcionamento da Arquitetura
				"br.ueg.prog.webi.api.*", "br.ueg.prog.webi.*"}
)
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class },
		basePackages = {
				"br.ueg.loja.*",
				//Para funcionamento da Arquitetura
				"br.ueg.prog.webi.*"}
)
@EnableJpaRepositories(basePackages = {
		"br.ueg.loja.*"
})
@EnableConfigurationProperties(StorageProperties.class)

public class LojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}
