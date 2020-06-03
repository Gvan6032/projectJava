package bookProject.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

     @Override
       public void onStartup(ServletContext container) throws ServletException {

           AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
           rootContext.register(AppContextConfig.class, WebSecurityConfig.class, RootConfig.class);
           container.addListener(new ContextLoaderListener(rootContext));
           AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
           dispatcherContext.register(WebConfig.class);
           ServletRegistration.Dynamic dispatcher =
                   container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
           dispatcher.setLoadOnStartup(1);
           dispatcher.addMapping("/");
           dispatcher.addMapping("/index");
           container.setInitParameter("defaultHtmlEscape", "true");
           FilterRegistration charEncodingFilterReg =
                   container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
           charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
           charEncodingFilterReg.setInitParameter("forceEncoding", "true");
           charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");
       }

        @Override
        protected Class<?>[] getRootConfigClasses() {
                return new Class[]{AppContextConfig.class,RootConfig.class,WebSecurityConfig.class};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
             return new Class[]{WebConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }
}