package com.child.programming.config;
import com.child.programming.base.dto.RoleInfoDto;
import com.child.programming.base.model.TbRoleDo;
import com.child.programming.base.service.IRoleService;
import com.child.programming.base.shiro.ShiroRealm;
import com.child.programming.base.shiro.ShiroUserFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * @author zdp
 * @description: TODO
 */

@Configuration
public class ShiroConfig {

    @Autowired
    private IRoleService iRoleService;
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明 1
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 自定义拦截器的配置
        Map<String, Filter> filter = new HashMap<>();
        filter.put("custom", new ShiroUserFilter());
        shiroFilterFactoryBean.setFilters(filter);

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/web/login/account");

        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");

        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();



        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon"); //静态资源
        filterChainDefinitionMap.put("/app/web/**", "anon"); //小程序接口
        filterChainDefinitionMap.put("/app/manage/**", "anon"); //小程序接口
        //filterChainDefinitionMap.put("/web/login/account", "anon"); //登陆Url

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put(" /web/logout", "logout"); //退出url

        //配置某个url需要某个权限码
        List<RoleInfoDto> roleInfoDtos=iRoleService.getList("");
        for (RoleInfoDto roleInfoDto:roleInfoDtos
             ) {
            filterChainDefinitionMap.put("/**", "perms["+roleInfoDto.getRoleToken()+"]");
        }
        //游客，开发权限
        //filterChainDefinitionMap.put("/guest/**", "anon");
        //用户，需要角色权限 “user”
        //filterChainDefinitionMap.put("/user/**", "roles[user]");
        //管理员，需要角色权限 “admin”
        //filterChainDefinitionMap.put("/**", "roles[admin]");

        //设置userFilter 访问权限
        filterChainDefinitionMap.put("/**","custom");

        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }
    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }
    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }
}