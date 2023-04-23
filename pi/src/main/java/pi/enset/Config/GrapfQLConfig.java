package pi.enset.Config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

public class GrapfQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return wiringBuilder->wiringBuilder.scalar(ExtendedScalars.Date);
    }
}
