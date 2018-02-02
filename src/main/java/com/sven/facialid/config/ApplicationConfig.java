package com.sven.facialid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class ApplicationConfig
{

    // @Bean
    // public WebMvcConfigurer webMvcConfigurer()
    // {
    // return new WebMvcConfigurerAdapter()
    // {
    // @Override
    // public void addViewControllers(final ViewControllerRegistry registry)
    // {
    // // redirect / to swagger page
    // registry.addViewController("/").setViewName(
    // "redirect:/swagger/index.html");
    // registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    // super.addViewControllers(registry);
    // }
    // };
    // }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

    @Bean
    public RestTemplate getRestTemplate()
    {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

}
