package br.com.caelum.eats.distancia;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportRuntimeHints;

@EnableDiscoveryClient
@SpringBootApplication
@ImportRuntimeHints(EatsDistanciaServiceApplication.EatsDistanciaServiceApplicationRuntimeHints.class)
public class EatsDistanciaServiceApplication {

	public static void main(String[] args) {
			//new SpringApplicationBuilder(EatsDistanciaServiceApplication.class).web(WebApplicationType.NONE).initializers(new LoggingIntializer()).run(args);
		//new SpringApplicationBuilder(EatsDistanciaServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
		SpringApplication.run(EatsDistanciaServiceApplication.class, args);
	}
  static class EatsDistanciaServiceApplicationRuntimeHints implements RuntimeHintsRegistrar {

      @Override
      public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    	  //org.springframework.cloud.client.discovery.DiscoveryClient;
          hints.reflection()
                  .registerConstructor(
                		  org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClient.class.getConstructors()[0], ExecutableMode.INVOKE);
                  //.registerMethod(ReflectionUtils.findMethod(
                	//	  org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClient.class, "save", Restaurante.class), ExecutableMode.INVOKE);
          //hints.resources().registerPattern("db/changelog/db.changelog-master.xml");
          //hints.resources().registerPattern("db/changelog/db.changelog-1.0.xml");
      }
  }
	
}


