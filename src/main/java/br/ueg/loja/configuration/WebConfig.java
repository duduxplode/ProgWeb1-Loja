/*
 * WebConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.loja.configuration;

import br.ueg.prog.webi.api.config.ApiWebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de configuração referente aos recursos Web MVC da aplicação.
 * 
 * @author UEG
 */
@Configuration
public class WebConfig extends ApiWebConfig {


}
