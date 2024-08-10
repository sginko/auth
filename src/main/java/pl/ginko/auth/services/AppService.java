package pl.ginko.auth.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ginko.auth.models.Application;
import pl.ginko.auth.models.MyUsers;
import pl.ginko.auth.repository.UserRepository;

import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class AppService {
    private List<Application> application;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct //гарантирует вызов метода сразу после инициализации компонентов
    public void loadAppInDb() {
        Faker faker = new Faker();
        application = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allAppications() {
        return application;
    }

    public Application applicationById(int id) {
        return application.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUsers user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
