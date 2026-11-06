package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 * @since 2026-11-06 上午 10:51
 */
@RestController
@RequestMapping("/test")
public class TestController {
   @GetMapping("/hello")
      public String hello(){
         return "hello world";
      }
}
