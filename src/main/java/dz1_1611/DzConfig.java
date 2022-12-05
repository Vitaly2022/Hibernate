package dz1_1611;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DzConfig {

    @Bean
    public Language English() {
        return new English();
    }

    @Bean
    public Language Daugh() {
        return new Daught();
    }
}

