package ga.creductive.resume.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class MainController {


    @GetMapping
    fun main(): String {
        return "main"
    }
}