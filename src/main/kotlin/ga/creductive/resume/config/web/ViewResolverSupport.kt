package ga.creductive.resume.config.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
class ViewResolverSupport {

    @Value("\${spring.mvc.view.prefix}")
    var prefix: String = ""

    @Value("\${spring.mvc.view.suffix}")
    var suffix: String = ""

    @Bean
    fun viewResolver(): InternalResourceViewResolver {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setViewClass(MarkdownView::class.java)
        viewResolver.setPrefix(this.prefix)
        viewResolver.setSuffix(this.suffix)

        return viewResolver
    }
}