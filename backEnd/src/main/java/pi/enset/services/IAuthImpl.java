package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.AuthRespense;
import pi.enset.entities.Person;
import pi.enset.repository.UserRepository;


@Service
@AllArgsConstructor
public class IAuthImpl implements IAuth {
    private UserRepository userRepository;
    @Override
    public AuthRespense login(String login, String password) {
        System.out.println("login "+login+" password "+password);
     Person person= userRepository.findByLoginAndPassword(login, password);
        //System.out.println("person "+person);
        if(person!=null){
            person.setAuthentificated(true);
            System.out.println("person "+person);
            userRepository.save(person);
            return new AuthRespense("token",person.getRole().equals("ADMIN"),person.getNom(),person.getId(),person.getPrenom(),person.getRole().equals("PROF"),true);
        }
        return new AuthRespense();
    }

    @Override
    public void logout(Long id) {
        Person person= userRepository.findById(id).orElseThrow(()->new RuntimeException("Person not found"));
        person.setAuthentificated(false);
        userRepository.save(person);
    }
}
