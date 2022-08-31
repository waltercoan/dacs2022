package br.univille.apidacs2022.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univille.coredacs2022.entity.Usuario;
import br.univille.coredacs2022.repository.UsuarioRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        var optional = repository.findByUsuario(nomeUsuario);
        if(optional.isPresent()){
            var usuario = optional.get();
            return new User(usuario.getUsuario(),usuario.getSenha(), new ArrayList<>());
        }
        return null;
    }
    
}