package com.atguigu.funding.config;

import com.atguigu.funding.except.FundingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FundingSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // spring在真正调用getBCryptPasswordEncoder() 方法之前，先到IOC容器中检查是否已经有该对象，如果有就直接返回，不再新创建。
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
//        authBuilder.inMemoryAuthentication()
//                .withUser("lilei")
//                .password("123123")
//                .roles("king");
        //查询数据库中的用户
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //表示 首页是谁都可以访问的,对静态资源的放行
        security.
                authorizeRequests().
                antMatchers("/index.html","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**").
                permitAll().
                antMatchers("/admin/query/for/search.html").hasRole("董事长").
                anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedHandler(new FundingAccessDeniedHandler()).and()
                .formLogin().loginPage("/admin/to/login/page.html").permitAll()
                .loginProcessingUrl("/admin/security/do/login.html").permitAll()
                .usernameParameter("loginAcct").passwordParameter("userPswd")
                .defaultSuccessUrl("/admin/to/main/page.html")
                .and().logout().logoutUrl("/admin/security/do/logout.html").logoutSuccessUrl("/index.html").
                and().csrf().disable();
    }
}
