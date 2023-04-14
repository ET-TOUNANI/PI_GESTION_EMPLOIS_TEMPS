package pi.enset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pi.enset.entities.Enseignant;
import pi.enset.repository.EnseignantRepository;

@SpringBootApplication
public class PiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiApplication.class, args);
	}
	@Bean
	CommandLineRunner lineRunner(EnseignantRepository userRepository){
		return args->{
			Enseignant enseignant=new Enseignant();
			enseignant.setNom("ELYoussfi");
			enseignant.setPrenom("Mohammed");
			enseignant.setEmail("abo@gmail.com");
			enseignant.setCivilite("Mr");
			enseignant.setPassword("1111");
			enseignant.setTel("08282993");
			enseignant.setLogin("admin");
			enseignant.setSpecialite("Info Java");
			userRepository.save(enseignant);
		};
	}

}
