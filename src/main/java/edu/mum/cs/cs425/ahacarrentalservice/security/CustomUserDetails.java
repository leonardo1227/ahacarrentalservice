package edu.mum.cs.cs425.ahacarrentalservice.security;

import edu.mum.cs.cs425.ahacarrentalservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public CustomUserDetails(User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesList = new ArrayList<>();
        if(getCarOwnerProfile()!=null){
            rolesList.add("ROLE_CAR_OWNER");
        }
        if(getAdmin()){
            rolesList.add("ROLE_ADMIN");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString(rolesList));
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

    public String getUsername(){
        return super.getUsername();
    }
}
