package hashpp.bills.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("default")
public class WebSecurityConfigurerDefault extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
              .antMatchers("/index.html", "/", "/home", "/login").permitAll()
              .antMatchers("/api/**")
              .authenticated()
              .and()
              .formLogin()
              .and()
              .httpBasic()
              .and()
              .csrf().disable();
        }

}
