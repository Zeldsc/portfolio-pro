package github.zeldsc.portfoliopro.config;

import github.zeldsc.portfoliopro.converter.StringToPessoaConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToPessoaConverter stringToPessoaConverter;

    public WebConfig(StringToPessoaConverter stringToPessoaConverter) {
        this.stringToPessoaConverter = stringToPessoaConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToPessoaConverter);
    }
}