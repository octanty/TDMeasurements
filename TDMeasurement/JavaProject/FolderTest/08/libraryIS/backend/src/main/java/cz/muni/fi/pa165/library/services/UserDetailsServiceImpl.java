package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.CustomerDao;
import cz.muni.fi.pa165.library.entities.Customer;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        Customer cust = customerDao.findCustomerWithUsername(username);
        if (cust == null) {
            throw new UsernameNotFoundException("user with name" + username + " not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        return new User(cust.getUserName(), cust.getPassword(), true, true, true, true, authorities);
    }
}