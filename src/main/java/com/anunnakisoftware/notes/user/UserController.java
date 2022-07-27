package com.anunnakisoftware.notes.user;

import com.anunnakisoftware.notes.notebook.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;
    private final NotebookService notebookService;

    @Autowired
    public UserController(UserService userService, NotebookService notebookService) {
        this.userService = userService;
        this.notebookService = notebookService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(path = "registration")
    public void addNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        notebookService.deleteNotebookByUserId(id);
        userService.deleteUser(id);
    }
}
