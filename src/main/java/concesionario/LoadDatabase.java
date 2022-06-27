package concesionario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(Repositorio repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Coche("1234 BCD", "Seat", "m1Seat")));
      log.info("Preloading " + repository.save(new Coche("4321 DCB", "Seat", "m1Seat")));
      log.info("Preloading " + repository.save(new Coche("4221 DCB", "Seat", "m2Seat")));
      log.info("Preloading " + repository.save(new Coche("5321 DCB", "Seat", "m3Seat")));
      log.info("Preloading " + repository.save(new Coche("6321 DCB", "Seat", "m3Seat")));
      
      
      log.info("Preloading " + repository.save(new Coche("1234 ACD", "Renault", "m1R")));
      log.info("Preloading " + repository.save(new Coche("4321 ACB", "Renault", "m1R")));
      log.info("Preloading " + repository.save(new Coche("4221 ACB", "Renault", "m2R")));
      log.info("Preloading " + repository.save(new Coche("5321 DDB", "Renault", "m3R")));
      log.info("Preloading " + repository.save(new Coche("6321 DCJ", "Renault", "m3R")));
      
      
      log.info("Preloading " + repository.save(new Coche("1234 ALD", "Citroen", "m1C")));
      log.info("Preloading " + repository.save(new Coche("4321 ALB", "Citroen", "m1C")));
      log.info("Preloading " + repository.save(new Coche("4221 ALB", "Citroen", "m2C")));
      log.info("Preloading " + repository.save(new Coche("5321 DLB", "Citroen", "m3C")));
    };
  }
}