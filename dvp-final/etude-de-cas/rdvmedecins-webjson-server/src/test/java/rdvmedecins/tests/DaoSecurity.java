package rdvmedecins.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rdvmedecins.security.AppUserDetailsService;
import rdvmedecins.security.Role;
import rdvmedecins.security.User;
import rdvmedecins.security.UserRepository;
import rdvmedecins.web.config.AppConfig;

import com.google.common.collect.Lists;

@SpringApplicationConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoSecurity {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AppUserDetailsService appUserDetailsService;

  @Test
  public void findAllUsersWithTheirRoles() {
    Iterable<User> users = userRepository.findAll();
    for (User user : users) {
      System.out.println(user);
      for (Role role : userRepository.getRoles(user.getId())) {
        System.out.println(role);
      }
    }
  }

  @Test
  public void findUserByLogin() {
    // on récupère l'utilisateur [admin]
    User user = userRepository.findUserByLogin("admin");
    // on vérifie que son mot de passe est [admin]
    Assert.assertTrue(BCrypt.checkpw("admin", user.getPassword()));
    // on vérifie le rôle de admin / admin
    List<Role> roles = Lists.newArrayList(userRepository.getRoles(user.getId()));
    Assert.assertEquals(1L, roles.size());
    Assert.assertEquals("ROLE_ADMIN", roles.get(0).getName());
  }

  @Test
  public void getUserDetailsByLogin() {
    Assert.assertNotNull(appUserDetailsService.loadUserByUsername("admin"));
    Assert.assertNotNull(appUserDetailsService.loadUserByUsername("user"));
  }

  @Test(expected = UsernameNotFoundException.class)
  public void failedGetUserDetailsByLogin() {
    Assert.assertNotNull(appUserDetailsService.loadUserByUsername("x"));
  }

}
