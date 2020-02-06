package ga.creductive.resume

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class ResumeApplication

fun main(args: Array<String>) {
    runApplication<ResumeApplication>(*args)
}
