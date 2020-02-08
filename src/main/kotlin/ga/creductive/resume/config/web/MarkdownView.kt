package ga.creductive.resume.config.web

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import org.springframework.web.servlet.view.InternalResourceView
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.nio.file.Path
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class MarkdownView : InternalResourceView() {

    private val parser = Parser.builder().build()
    private var renderer = HtmlRenderer.builder().build()

    override fun render(model: MutableMap<String, *>?, request: HttpServletRequest, response: HttpServletResponse) {
        val resource = super.getUrl()?.let { ClassPathResource(it) }
                ?: throw IOException("not found page")

        val document = parser.parseReader(resource.inputStream.reader())
        val renderedDocument = renderer.render(document)


        val mustacheFactory: MustacheFactory = DefaultMustacheFactory()
        val m: Mustache = mustacheFactory.compile(ClassPathResource("/static/default_template.html").inputStream.reader(), "resume-page")
        val writer = StringWriter()
        m.execute(writer, renderedDocument)

        FileCopyUtils.copy(renderedDocument.byteInputStream(), response.outputStream)
    }
}