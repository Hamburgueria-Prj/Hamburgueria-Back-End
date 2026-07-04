package br.com.hamburgueria_local.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer  {
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**") // Libera todas as rotas da API (ex: /usuario, /pedido)
	                .allowedOrigins("http://localhost:3000", "http://localhost:5173") // Portas padrão do React/Vite
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Métodos HTTP permitidos
	                .allowedHeaders("*") // Permite todos os cabeçalhos (Headers) nas requisições
	                .allowCredentials(true); // Permite envio de cookies ou tokens de autenticação se necessário
	    }
}
