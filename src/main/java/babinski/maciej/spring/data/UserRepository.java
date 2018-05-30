package babinski.maciej.spring.data;

import babinski.maciej.spring.data.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}