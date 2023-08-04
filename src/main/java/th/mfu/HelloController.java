package th.mfu;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    String hello() {

        return "Hello World!";
    }

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {

        return "Hello World!"+ name;
    }

   @GetMapping("/sum/{num1}/{num2}")
   int sum(@PathVariable int num1 ,@PathVariable int num2){
        return num1+num2;
   }

   
}
