package ga.creductive.resume.view

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import org.springframework.core.io.ClassPathResource
import java.io.StringWriter

interface TemplateRenderer {

    fun render(data: Map<String, *>): String

    class MustacheTemplateRenderer: TemplateRenderer {

        var defaultTemplate: String = "/static/default_template.html"

        override fun render(data: Map<String, *>): String {
            val mustacheFactory: MustacheFactory = DefaultMustacheFactory()
            val m: Mustache = mustacheFactory.compile(ClassPathResource(defaultTemplate).inputStream.reader(), "resume-page")
            val writer = StringWriter()

            return m.execute(writer, data).toString()
        }

    }
}