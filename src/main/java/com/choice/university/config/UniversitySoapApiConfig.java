package com.choice.university.config;

import com.choice.university.soap.UniversitySoapApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class UniversitySoapApiConfig {

  // TODO: Check why pulling the two strings below from properties does not work

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // This must be the same as the <generatePackage> in the pom's plugin
    marshaller.setContextPath("com.choice.university.dto");
    return marshaller;
  }

  @Bean
  public UniversitySoapApiClient UniversityClient(Jaxb2Marshaller marshaller) {
    var client = new UniversitySoapApiClient();
    client.setDefaultUri("http://localhost:8081/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}