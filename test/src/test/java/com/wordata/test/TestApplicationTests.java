package com.wordata.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.wordata.test.domain.User;
import com.wordata.test.mapper.UserMapper;
import com.wordata.test.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "property.value=propertyTest",
                "value=test"
        },
        classes = {TestApplication.class}
)
public class TestApplicationTests {
	
	@Autowired UserMapper userMapper;
	@Autowired private UserService userService;

    @Value("${value}")
    private String value;

    @Value("${property.value}")
    private String propertyValue;

    private User user1;
    
    @Test
    public void contextLoads() {
        assertThat(value, is("test"));
        assertThat(propertyValue, is("propertyTest"));
        
        user1 = new User();
    	user1.setUsername("wordata");
        user1.setPassword("wordata123");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("wordata");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("ADMIN"));
        
        assertThat("wordata", is(user1.getUsername()));
        
        userService.deleteUser(user1.getUsername());
        userService.createUser(user1);
        User user = userService.readUser(user1.getUsername());
        assertThat(user.getUsername(), is(user1.getUsername()));
       
        PasswordEncoder passwordEncoder = userService.passwordEncoder();
        assertThat(passwordEncoder.matches("wordata123", user.getPassword()), is(true));

        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
        while (it.hasNext()) {
             GrantedAuthority authority = it.next();
             assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
        }
    }

}
