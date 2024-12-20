package org.example.spring_2;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class AuthController {
    private final UserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if(appUserRepository.findByUsername(username).isPresent()) {
            System.out.println("Użytkownik już istnieje!");
            return "redirect:/register?error";
        }
        else {
            User user = new User(username, passwordEncoder.encode(password), "ROLE_USER");
            System.out.println("Otrzymano dane użytkownika: " + user.getUsername());
            appUserRepository.save(user);
            System.out.println("Zapisano dane użytkownika: " + user.getUsername());
            return "redirect:/login";
        }


    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        Optional<User> user = appUserRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {

            User loggedInUser = user.get();
            // Create authentication token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loggedInUser.getUsername(),
                    null, // Credentials are not stored in the token for security reasons
                    List.of(new SimpleGrantedAuthority(loggedInUser.getRole())) // Authorities granted to the user
            );

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(token);

            HttpSession session = request.getSession(true); // Create session if not exists
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("User Authorities: " + auth.getAuthorities());


            System.out.println("Zalogowano użytkownika: " + username + ", Authority: " + user.get().getRole());
            return "redirect:/cart";
        }

        System.out.println("Nieudane logowanie dla: " + username);
        return "redirect:/login?error";
    }

    @PostConstruct
    public void encryptPasswordOnStartup() {
        String plainPassword = "pass";
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        System.out.println("Plain password: " + plainPassword);
        System.out.println("Encrypted password: " + encryptedPassword);
//        $2a$10$ylsYyvnabBTb/9z8Khugx.kCEaZUQolAZ0vStnQBiWrmOhuqVH6xe
    }



}
