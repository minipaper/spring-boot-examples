package net.minipaper;

import net.minipaper.users.User;
import net.minipaper.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AopApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(AopApplication.class);
  }

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) {

    // 사용자 100명 추가
    for (int i = 0; i < 100; i++) {
      userRepository.save(new User(i + "@email.com", "User " + i));
    }
  }
}
