package mic3;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(ServiceEndpoints.class);
    }
}
