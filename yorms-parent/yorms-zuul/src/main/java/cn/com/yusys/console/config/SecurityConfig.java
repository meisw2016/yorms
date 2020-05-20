/*
 * package cn.com.yusys.console.config;
 * 
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.formLogin().and().httpBasic().and().csrf().disable();
 * http.sessionManagement().invalidSessionUrl("/login").maximumSessions(5).
 * maxSessionsPreventsLogin(true).expiredUrl("/login");
 * http.logout().permitAll();
 * http.authorizeRequests().antMatchers("/approve/**").hasAuthority("系统管理员").
 * anyRequest().authenticated();
 * 
 * }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception {
 * 
 * auth.inMemoryAuthentication().passwordEncoder(new
 * BCryptPasswordEncoder()).withUser("user") .password(new
 * BCryptPasswordEncoder().encode("123456")).roles("USER").and().withUser(
 * "admin") .password(new
 * BCryptPasswordEncoder().encode("123456")).roles("USER", "ADMIN");
 * 
 * 
 * }
 * 
 * }
 */