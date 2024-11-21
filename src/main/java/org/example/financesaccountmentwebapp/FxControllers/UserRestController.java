package org.example.financesaccountmentwebapp.FxControllers;

import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.example.financesaccountmentwebapp.services.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Пользователь уже существует", HttpStatus.CONFLICT);
        }

        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка регистрации пользователя", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User currentUser = userRepository.findByUsername(user.getUsername());

        if (currentUser == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        } else if (!PasswordUtil.isCorrect(user.getPassword(), currentUser.getPassword())) {
            return new ResponseEntity<>("Неверный пароль", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Успешный вход", HttpStatus.OK);
    }

}


