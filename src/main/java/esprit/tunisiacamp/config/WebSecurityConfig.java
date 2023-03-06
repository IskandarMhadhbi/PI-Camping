package esprit.tunisiacamp.config;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.services.CustomOAuth2UserService;
import esprit.tunisiacamp.services.UserIService;
import esprit.tunisiacamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/rest/login/oauth/**","/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .defaultSuccessUrl("/rest/swagger-ui/index.html#")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        System.out.println("Authentication name: " + authentication.getName());
                       // User u = new User();
                        //u.setProdiver(Provider.GOOGLE);
                        //u.setUsername(authentication.getName());
                        //userService.addUser(u);
                        //System.out.println("Authentication name: " + authentication.getCredentials());

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        userIService.processOAuthPostLogin(oauthUser.getEmail());
                        response.sendRedirect("/rest/swagger-ui/index.html#");
                    }
                })
                .and()
                //.logout().logoutUrl("/rest/logout")
                //.logoutSuccessUrl("/rest/login")
                //.invalidateHttpSession(true)
                //.deleteCookies("JSESSIONID")
                //.permitAll();
                .logout().logoutSuccessUrl("/logout").permitAll()

        ;
                //.and()
                //.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    UserIService userIService;


}
