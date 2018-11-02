package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)// 启用PreAuthorize注解
@ComponentScan("com.web")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 拦截配置
     * authorizeRequests 配置权限
     * <p>
     * antMatchers 配置一个request Mather 的 string数组，参数为 ant 路径格式， 直接匹配url。
     * mvcMatchers 同上，但是用于匹配 @RequestMapping 的value
     * regexMatchers 同上，但是为正则表达式
     * anyRequest 匹配任意url，无参。
     * <p>
     * hasAnyRole 是否有（参数数组中的）任一角色
     * hasRole 是否有某个角色
     * hasAuthority 是否有某个权限
     * hasAnyAuthority 是否有（参数数组中的）任一权限
     * hasIpAddress ip是否匹配参数
     * permitAll 允许所有情况，即相当于没做任何security限制
     * denyAll 拒绝所有情况。 这情况比较奇怪， 如果拒绝所有情况的话， 那的存在有什么意义？
     * anonymous 可以以匿名身份登录
     * authenticated 必须要进行身份验证
     * fullyAuthenticated 进行严格身份验证，即不能使用缓存/cookie之类的
     * rememberMe 可以cookie 登录？
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().permitAll().and()
                .logout().permitAll();

    }

}
