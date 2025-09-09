package com.training.expense_rec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloController {


  @GetMapping("/public/hello")
  public String publicHello() {
    return "Hello, world (public)";
  }
  
  @GetMapping("/public/sagar")
  public String publicSagar() {
    return "Good Afternoon, Sagar.";
  }

  @GetMapping("/private/hello")
  public String privateHello() {
    return "Hello, world (private)";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin/hello")
  public String adminHello() {
    return "Hello, Admin!";
  }
}
