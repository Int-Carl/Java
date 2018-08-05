package com.example.readinglist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReaderController {
    @RequestMapping("/login")
    public String ReaderLogin (){
        return "login";
    }
}
