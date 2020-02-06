package ga.creductive.resume.config.web

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import org.springframework.web.servlet.view.InternalResourceView
import java.io.IOException
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


        FileCopyUtils.copy(renderedDocument.byteInputStream(), response.outputStream)
    }
}