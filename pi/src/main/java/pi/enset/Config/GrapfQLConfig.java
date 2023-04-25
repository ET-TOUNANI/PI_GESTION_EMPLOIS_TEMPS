package pi.enset.Config;

import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalTime;

@Configuration
public class GrapfQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.Time);
    }

    @Bean
    public GraphQLScalarType timeScalar() {
        return GraphQLScalarType.newScalar()
                .name("Time")
                .description("Local time scalar")
                .coercing(new Coercing<LocalTime, String>() {
                    @Override
                    public String serialize(Object input) {
                        if (input instanceof LocalTime) {
                            return ((LocalTime) input).toString();
                        } else {
                            throw new CoercingSerializeException("Invalid value: " + input);
                        }
                    }

                    @Override
                    public LocalTime parseValue(Object input) {
                        if (input instanceof String) {
                            return LocalTime.parse((String) input);
                        } else {
                            throw new CoercingParseValueException("Invalid value: " + input);
                        }
                    }

                    @Override
                    public LocalTime parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            return LocalTime.parse(((StringValue) input).getValue());
                        } else {
                            throw new CoercingParseLiteralException("Invalid value: " + input);
                        }
                    }
                })
                .build();
    }
}
