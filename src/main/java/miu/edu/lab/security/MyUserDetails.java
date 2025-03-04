package miu.edu.lab.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import miu.edu.lab.model.Role;
import miu.edu.lab.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {
  private String username;
  @JsonIgnore private String password;
  private List<Role> roles = new ArrayList<>();

  public MyUserDetails(User user) {
    this.username = user.getEmail();
    this.password = user.getPassword();
    this.roles = user.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var result =
        roles.stream()
            .map(
                role ->
                    new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase(Locale.ROOT)))
            .collect(Collectors.toList());
    return result;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
