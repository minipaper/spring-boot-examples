package sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
  @GetMapping("/")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello Spring Boot!");
  }
}
