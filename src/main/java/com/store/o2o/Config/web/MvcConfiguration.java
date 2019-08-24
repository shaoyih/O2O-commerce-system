package com.store.o2o.Config.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         //registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/baidu/work/image/upload/");
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "viewResolver")
    public ViewResolver createViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 设置Spring 容器
        viewResolver.setApplicationContext(this.applicationContext);
        // 取消缓存
        viewResolver.setCache(false);
        // 设置解析的前缀
        viewResolver.setPrefix("/html/");
        // 设置试图解析的后缀
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        // 1024 * 1024 * 20 = 20M
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }
    @Value("${kaptcha.border}")
    private String border;

    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;

    @Value("${kaptcha.image.width}")
    private String width;

    @Value("${kaptcha.textproducer.char.string}")
    private String cString;

    @Value("${kaptcha.image.height}")
    private String height;

    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;

    @Value("${kaptcha.noise.color}")
    private String nColor;

    @Value("${kaptcha.textproducer.char.length}")
    private String clength;

    @Value("${kaptcha.textproducer.font.names}")
    private String fnames;

    /**
     * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
     */
    @Bean
    public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() throws ServletException {
        ServletRegistrationBean<KaptchaServlet> servlet = new ServletRegistrationBean<KaptchaServlet>(new KaptchaServlet(), "/Kaptcha");
        servlet.addInitParameter("kaptcha.border", border);
        servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
        servlet.addInitParameter("kaptcha.image.width", width);
        servlet.addInitParameter("kaptcha.textproducer.char.string", cString);
        servlet.addInitParameter("kaptcha.image.height", height);
        servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
        servlet.addInitParameter("kaptcha.noise.color", nColor);
        servlet.addInitParameter("kaptcha.textproducer.char.length", clength);
        servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);
        return servlet;
    }
}
