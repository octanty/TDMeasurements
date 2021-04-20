package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
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
    private EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {        
        Employee cust = employeeDao.findEmployeeByEmail(email);
        if (cust == null) {
            throw new UsernameNotFoundException("Employee with email" + email + " not found");
        }
        //String password  = cust.getPassword();
        //additional information on the security object
       // boolean enabled = cust.isIsActive().equal
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        
        return new User(cust.getEmail(), cust.getPassword(), true, true, true, true, authorities);
    }
}