package net.thjang.blog.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "index";
    }
}
