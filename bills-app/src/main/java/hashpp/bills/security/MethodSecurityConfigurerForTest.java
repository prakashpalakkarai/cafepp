package hashpp.bills.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@Profile("test")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfigurerForTest
  extends GlobalMethodSecurityConfiguration {
}
