package br.univille.apidacs2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.univille.coredacs2022.entity.Usuario;
import br.univille.coredacs2022.repository.UsuarioRepository;

@Component
public class Startup {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!usuarioRepository.findByUsuario("admin").isPresent()){
            var adminUsuario = new Usuario();
            adminUsuario.setUsuario("admin");
            adminUsuario.setSenha("admin");
            usuarioRepository.save(adminUsuario);
        }
    }
}
