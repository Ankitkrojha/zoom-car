package com.spring.main;

import com.spring.ui.UserInterfaceIntro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String args[])
    {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext(
                "Config.xml");

        UserInterfaceIntro userInterfaceIntro=(UserInterfaceIntro)context.getBean("userInterfaceIntro");
        userInterfaceIntro.userInterfaceIntro();



    }
}
