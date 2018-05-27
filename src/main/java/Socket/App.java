package Socket;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Artur Kuzmik on 18.27.5
 */
@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(App.class);
        Server server = context.getBean(Server.class);

        server.serverRun();


    }
}
