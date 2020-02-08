package ga.creductive.resume.config.web

interface TemplateRenderer {
    fun render(data: Map<String, Object>)
}