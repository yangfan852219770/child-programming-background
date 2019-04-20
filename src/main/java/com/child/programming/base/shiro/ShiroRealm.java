package com.child.programming.base.shiro;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ShiroDto;
import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.mapper.TbTeacherDoMapper;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zdp
 * @description: TODO
 */
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private ITeacherService iTeacherService;


    /***
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        LoginedUserInfoDto userInfoPojo = (LoginedUserInfoDto)SecurityUtils.getSubject().getSession().getAttribute(ConstDataUtil.CURRENT_USER);
        /*Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(userInfoPojo.getCurrentAuthority());
        //设置该用户拥有的角色
        info.setRoles(set);*/
        info.addStringPermission(userInfoPojo.getCurrentAuthority());//给当前用户授权url为hello的权限码
        System.out.println("经试验：并不是每次调用接口就会执行，而是调用需要操作码（permission）的接口就会执行");
        return info;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        LoginedUserInfoDto loginedUserInfoDto =new LoginedUserInfoDto();
        System.out.println("————身份认证方法————");
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面subject.login(token)传过来的
        UsernamePasswordToken  token = (UsernamePasswordToken) authenticationToken;
        ShiroDto shiroDto = iTeacherService.getTeacherByLoginId(token.getUsername());
        if(EmptyUtils.objectIsEmpty(shiroDto))
            return null;

        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
		/*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
		 * 第二个参数必须放密码，
		 * 第三个参数放 当前realm的名字，因为可能有多个realm
		 * */
        SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getPrincipal(),shiroDto.getPassword(), getName());

        //清之前的授权信息
        super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());

        //设置用户session
        BeanUtils.copyProperties(shiroDto,loginedUserInfoDto);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ConstDataUtil.CURRENT_USER, loginedUserInfoDto);

        //返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        //如果有问题，向上抛异常，一直抛到控制器
        return authcInfo;
    }
}
