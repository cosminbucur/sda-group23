package com.sda.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoAutowired {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AutowiredConfig.class);

        TextEditor textEditor = applicationContext.getBean("textEditor", TextEditor.class);

        textEditor.spellCheck();
        textEditor.format();
        textEditor.convertImage();
    }

    // create classes
    // annotate components
    // create config
    // create dependencies (@Autowired)

}
