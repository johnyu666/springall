package cn.johnyu.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 此配置生效的前提是：在web.xml中配置Filter: org.springframework.web.filter.DelegatingFilterProxy
 * 此配置将在容器中生成一个 springSecurityFilterChain bean,此对象将根据此类的规则利用不同的filter进行工作。
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 为用户授权（配置Authentication）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("john")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER");
    }

    /**
     * 配置Filter Chain的工作特性
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**","/*.html");
    }

    /**
     *配置受保护的web资源有哪些以及采用何种鉴权方式
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //此方式也会阻止对静态资源的访问（参见AppConfig的configureDefaultServletHandling（）方法）
        //必须在configure(WebSecurity web)方法中加以解除
        http.authorizeRequests()
                .anyRequest().authenticated()//所有的请求必须经过认证
                .and().formLogin()//使用form-login方式登录
                .and().logout()//logout方式退出
                .and().httpBasic();//from-login方式失效，采用httpBasic方式
    }
}
