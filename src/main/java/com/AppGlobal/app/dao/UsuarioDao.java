package com.AppGlobal.app.dao;

import com.AppGlobal.app.models.Usuario;

import java.util.List;

public interface UsuarioDao {    //la inferface en el que indicamos que funciones tinee una clase

    //listado de usuarios
    List<Usuario> getUsuarios();

    //eliminar usuario
    void eliminar(long id);

    //registrar usuario
    void registrar(Usuario usuario);

   //iniciar sesion, validar datos
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
