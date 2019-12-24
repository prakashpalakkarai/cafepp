package hashpp.bills.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("boottest")
public class WebSecurityConfigurerForBootTest extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // TODO: disabling security for now as mocks are not working yet for boot test
            http.csrf()
                    .disable()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
        }

}
