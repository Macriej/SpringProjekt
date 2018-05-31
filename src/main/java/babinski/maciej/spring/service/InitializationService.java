package babinski.maciej.spring.service;

import babinski.maciej.spring.data.RoleRepository;
import babinski.maciej.spring.data.UserRepository;
import babinski.maciej.spring.data.objects.Role;
import babinski.maciej.spring.data.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InitializationService implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean alreadySetup = false;

    @Autowired
    public InitializationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    protected Role createRole(String name) {
        Optional<Role> role = roleRepository.findByName(name);

        return role.orElseGet(() ->
                roleRepository.save(new Role(name))
        );
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        createRole("ADMIN");
        Role userRole = createRole("USER");

        User user = new User("test", passwordEncoder.encode("test"), "macriej@gmail.pl");
        user.addRole(userRole);
        userRepository.save(user);

        alreadySetup = true;

    }
}
