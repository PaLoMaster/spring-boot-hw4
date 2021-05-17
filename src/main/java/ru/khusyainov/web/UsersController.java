package ru.khusyainov.web;

import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.khusyainov.model.User;
import ru.khusyainov.repository.UserRepository;

import java.util.stream.Collectors;

@Controller
@Secured({"ROLE_ADMIN", "ROLE_SUPERUSER"})
@RequestMapping("/users")
public class UsersController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UsersController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model uiModel) {
        uiModel.addAttribute("usersList", userRepository.findAll());
        return "users/users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddUser(Model uiModel){
        User user = new User();
        uiModel.addAttribute("user", user);
        uiModel.addAttribute("changed", false);
        return "users/add-user";
    }

    @Secured({"ROLE_SUPERUSER"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(Model uiModel, @NonNull User user, @NonNull String userRoles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        userRepository.save(user);
        uiModel.addAttribute("changed", true);
        uiModel.addAttribute("user", user);
        return "users/add-user";
    }

    @Secured({"ROLE_SUPERUSER"})
    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public String deleteProduct(Model uiModel, @PathVariable @NonNull String username) {
        userRepository.delete(userRepository.getOne(username));
        return getAll(uiModel);
    }

    @Secured({"ROLE_SUPERUSER"})
    @RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
    public String getEditProduct(Model uiModel, @PathVariable @NonNull String username){
        User user = userRepository.getOne(username);
        user.setPassword("Enter password for change it");
        String userRoles = user.getRoles().stream().map(role -> role.getName().replace("ROLE_", ""))
                .collect(Collectors.joining(",", "", ""));
        uiModel.addAttribute("changed", false);
        uiModel.addAttribute("userRoles", userRoles);
        uiModel.addAttribute("user", user);
        return "users/edit-user";
    }

    @Secured({"ROLE_SUPERUSER"})
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProduct(Model uiModel, @NonNull User user, @NonNull String userRoles) {
        if (user.getPassword() != null && !user.getPassword().isEmpty() && "Enter password for change it".equals(user.getPassword())) {
            user.setPassword(userRepository.getOne(user.getUsername()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        String oldRoles = user.getRoles().stream().map(role -> role.getName().replace("ROLE_", ""))
                .collect(Collectors.joining(",", "", ""));
        if (!oldRoles.equals(userRoles)) {
            user.setRoles(userRoles);
        }
        userRepository.save(user);
        uiModel.addAttribute("changed", true);
        uiModel.addAttribute("user", user);
        return "users/edit-user";
    }
}