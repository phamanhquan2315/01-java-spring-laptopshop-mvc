package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class UserController {

    private UserService userService;
    // private final UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;

        
    }

    // MVC
    @RequestMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("eric", "test"); // (key ,value)
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";  // String
    }

    
    // @RequestMapping("/admin/user")
    // public String getUserPage(Model model, String email){
    //     List<User> arrUsers = this.userService.getAllUsersByEmail("phamanhquan2315@gmail.com");
    //     System.out.println(arrUsers);
    //     String test = userService.handleHello();
    //     model.addAttribute("newUser", new User());
    //     return "admin/user/create";  // String
    // }

    
    @RequestMapping("/admin/user") 
    public String getUserPage(Model model){
        
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users); // key (jsp): value 
        return "admin/user/table-user";
    }

        
    @RequestMapping("/admin/user/{id}") 
    public String getUserDetailPage(Model model, @PathVariable long id){
        User user = this.userService.getUserById(id);
        System.out.println("check path id " + id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);

        return "admin/user/show";
    }


    
    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST) // POST
    public String createUserPage(Model model, @ModelAttribute("newUser") User user){
        System.out.println("create new here" + user);
        this.userService.hanldleSaveUser(user);

        return "redirect:/admin/user"; // return getUserPage()
    }


    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id){
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        // model.addAttribute("id", id);
        return "admin/user/update";
    }
    
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User hoidanit){
        User currentUser = this.userService.getUserById(hoidanit.getId());
        if(currentUser != null){
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());

            this.userService.hanldleSaveUser(currentUser);
            System.out.println("run here");
        }
        return "redirect:/admin/user"; // return getUserPage()
    }   
    

    @GetMapping("/admin/user/delete/{id}") // GET
    public String getCreateUserPage(Model model, @PathVariable long id){
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }
    
    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User eric){
        System.out.println("delete here");
        this.userService.deleteUser(eric.getId());
        return "redirect:/admin/user"; // return getUserPage()
    }   
    
}

