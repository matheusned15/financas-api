package com.nedstore.minhasfinancasapi.api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioResource {

    @GetMapping("/")
    public String hello(){
        return "hello world!";
    }
}
