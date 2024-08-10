package pl.ginko.auth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ginko.auth.models.Application;
import pl.ginko.auth.models.MyUsers;
import pl.ginko.auth.services.AppService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/apps")
public class AppController {
    private AppService service;

    @GetMapping("/welcome")
    public String welcome (){
        return "Welcome tp unprotected page";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/all-app")
    public List<Application> allApplication(){
        return service.allAppications();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Application applicationById(@PathVariable int id){
        return service.applicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUsers users){
        service.addUser(users);
        return "User is saved";
    }
}
