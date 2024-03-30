package org.example.assignment1;

import model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.UserService;

import java.util.List;

@SpringBootApplication
@ComponentScan({"service", "controller","model", "repository", "service"})
@EntityScan("model")
@EnableJpaRepositories("repository")
public class Assignment1Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment1Application.class, args);
    }

   /* @Bean
    public CommandLineRunner printAllUsers(UserService userService) {
        return args -> {
            List<User> users = userService.getAllUsers();
            StringBuilder usernames = new StringBuilder();
            if (users != null) {
                for (User user : users) {
                    usernames.append(user.getUsername()).append(", ");
                }
            } else {
                usernames.append("No users found.");
            }
            System.out.println("All Users: " + usernames);
        };
    }
*/
}
