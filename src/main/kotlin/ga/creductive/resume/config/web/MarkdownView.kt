package ga.creductive.resume.config.web

import ga.creductive.resume.view.TemplateRenderer
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import org.springframework.web.servlet.view.InternalResourceView
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MarkdownView : InternalResourceView() {

    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()
    // 의존성 오지게 박습니다 행님. TODO View Resolver 자체를 바꿔볼 생각을 한번 해보자
    private val templateRenderer: TemplateRenderer = TemplateRenderer.MustacheTemplateRenderer()

    override fun render(model: MutableMap<String, *>?, request: HttpServletRequest, response: HttpServletResponse) {
        val resource = super.getUrl()?.let { ClassPathResource(it) }
                ?: throw IOException("not found page")

        val markdown = parser.parseReader(resource.inputStream.reader())
        val renderedMarkdown = renderer.render(markdown)
        val renderedDocument = templateRenderer.render(mapOf("resume_body" to renderedMarkdown))

        FileCopyUtils.copy(renderedDocument.byteInputStream(), response.outputStream)
    }
}