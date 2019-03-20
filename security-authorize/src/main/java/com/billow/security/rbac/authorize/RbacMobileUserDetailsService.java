package com.billow.security.rbac.authorize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuyongtao
 * @create 2019-03-13 15:22
 */
@Service("rbacMobileUserDetailsService")
public class RbacMobileUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        logger.info("mobile:{}", mobile);

        if (!"13012345678".equals(mobile)) {
            throw new UsernameNotFoundException("没有查询到用户");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("admin"));


        return new User("billow", "billow", authorities);
    }
}
