package com.alura_challenge.foro;

import com.alura_challenge.foro.models.User;
import com.alura_challenge.foro.models.authorization.Permission;
import com.alura_challenge.foro.models.authorization.Role;
import com.alura_challenge.foro.repositories.PermissionRepository;
import com.alura_challenge.foro.repositories.RoleRepository;
import com.alura_challenge.foro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ForoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init(){
		return args -> {
			System.out.println("Cargando datos para iniciar la aplicacion...");

			List.of(
					"Create_role","Update_role","Delete_role","Read_role",
					"Create_permission","Update_permission","Delete_permission","Read_permission",
					"Create_topic","Update_topic","Delete_topic","Read_topic","All_topic"
			).forEach(permission -> {
				permissionRepository.save(Permission.builder()
						.name(permission)
						.build());
			});

			List<Permission> permissions = List.of(
					"Create_topic","Update_topic","Delete_topic","Read_topic"
			).stream().map(permission ->
				permissionRepository.findByName(permission)
			).toList();

			Role user = roleRepository.save(Role.builder()
					.name("USER")
					.permissions(permissions)
					.build());

			Role root = roleRepository.save(Role.builder()
					.name("ROOT")
					.permissions(permissionRepository.findAll())
					.build());

			userRepository.save(User.builder()
							.username("admin")
							.password(passwordEncoder.encode("admin"))
							.role(root)
							.enable(true)
					.build());

			userRepository.save(User.builder()
							.username("user")
							.password(passwordEncoder.encode("user"))
							.role(user)
							.enable(true)
					.build());
		};
	}

}
