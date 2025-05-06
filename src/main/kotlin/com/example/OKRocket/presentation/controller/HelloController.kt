package com.example.OKRocket.presentation.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/")
    fun index(): String {
        return "index" // src/main/resources/templates/index.mustache を表示
    }
}
