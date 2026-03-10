package edu.cibertec.login.service;

import edu.cibertec.login.dao.UsuarioDAO;
import edu.cibertec.login.dao.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioEntity validarLogin(UsuarioEntity usuario){
        UsuarioEntity rpta = usuarioDAO.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave());
        return rpta;
    }

    public void insertarUsuario(UsuarioEntity usuario){
        usuarioDAO.save(usuario);
    }

    public List<UsuarioEntity> getListaUsuario(){
        return usuarioDAO.findAll();
    }

    public void usuarioEliminar(String codigoUsuario){
         usuarioDAO.deleteById(codigoUsuario);
    }

    public UsuarioEntity getUsuario(String codigo){
        UsuarioEntity rpta = null;
        Optional<UsuarioEntity> busqueda = usuarioDAO.findById(codigo);
        if(busqueda.isPresent())
            rpta=busqueda.get();
        return rpta;
    }

}
