package com.sda.spring.core.qualifier;

import com.sda.spring.core.autowired.AutowiredConfig;
import com.sda.spring.core.autowired.TextEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoQualifier {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(QualifierConfig.class);

        FormatterService formatterService = applicationContext.getBean("formatterService", FormatterService.class);

        formatterService.run();
    }

}
