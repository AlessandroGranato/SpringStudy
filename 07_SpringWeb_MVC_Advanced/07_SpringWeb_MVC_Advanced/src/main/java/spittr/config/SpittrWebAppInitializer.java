package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        /*
        MultipartConfigElement parameters:
        - Absolute path to a directory in the filesystem where the uploaded file will be written temporarily.
        - The maximum size (in bytes) of any file uploaded. By default there is no limit.
        - The maximum size (in bytes) of the entire multipart request, regardless of how many parts or how big any of the parts are. By default there is no limit.
        - The maximum size (in bytes) of a file that can be uploaded without being written to the temporary location. The default is 0, meaning that all uploaded files will be written to disk.
         */
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads",2097152, 4194304, 0));
    }
}
