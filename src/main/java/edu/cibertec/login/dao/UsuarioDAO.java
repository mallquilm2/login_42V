package edu.cibertec.login.dao;

import edu.cibertec.login.dao.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository <UsuarioEntity, String> {

    public UsuarioEntity findByUsuarioAndClave(String usuario, String clave);

}
