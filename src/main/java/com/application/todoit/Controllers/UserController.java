//package com.application.todoit.Controllers;
//
//import com.application.todoit.DtoTask.TaskResponse;
//import com.application.todoit.Exceptions.NotFoundException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//
//
//
//
//    @GetMapping
//    public Object showUsers() {
//        return null;                  // список юзеров
//    }
//
//    @GetMapping("/{id}")
//    public Object showUser() {
//        return null;                  // id, login, name, lastSessionTime
//    }
//
//    @PostMapping
//    public Object signUp() {          // login, name, mail, pass
//        return null;                  // id & sessionGuid / error
//    }
//
//    @PostMapping
//    public Object signIn() {          // mail/login, pass
//        return null;                  // sessionGuid / error
//    }
//
//    @PutMapping("/signout")
//    public Object signOut() {         // sessionGuid???
//        return null;                  // 'success' / error
//    }
//
//    @PutMapping("/{id}")
//    public Object updateUser() {      // id, name, pass
//        return null;                  // 'success' / error
//    }
//
//    @DeleteMapping("/{id}")
//    public Object deleteUser() {
//        return null;                  // 'success' / error
//    }
//
//}
