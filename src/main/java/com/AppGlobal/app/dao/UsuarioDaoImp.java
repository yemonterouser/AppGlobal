package com.AppGlobal.app.dao;

import com.AppGlobal.app.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //hace referenia a la base de datos
@Transactional //la forma en que trata las consulas de la  base de datos
public class UsuarioDaoImp implements UsuarioDao{ //esta clase lo que hara es implrmrntar la interface

    @PersistenceContext
    EntityManager entityManager; //conexion a la bases de datos

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "From Usuario"; //aqui hace referencua ala case Usuario, mas no la tabla bdusuario
        return entityManager.createQuery(query).getResultList();

    }
  //eliminar usuario
    @Override
    public void eliminar(long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }
    //registrar usuarios
    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "From Usuario WHERE email = :email"; //aqui hace referencua ala case Usuario, mas no la tabla bdusuario
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()){
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }
        return null;
    }
}
