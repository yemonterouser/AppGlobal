package com.AppGlobal.app.controller;

import com.AppGlobal.app.dao.UsuarioDao;
import com.AppGlobal.app.models.Usuario;
import com.AppGlobal.app.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //aqui le indicamos que esta clase es controlador
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    //listado de usuarios
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET) //requiest mapping valor de la url
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){

        String usuarioId = jwtUtil.getKey(token);
        if (!validarToken (token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }
    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    //registrar usuario
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST) //requiest mapping valor de la url
    public void registrarUsuarios(@RequestBody Usuario usuario){

        //estas argon es para encriptar la contraseñas
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        String hash = argon2.hash(1,1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    //editar usuario
    @RequestMapping(value = "usuario123")
    public Usuario editar(){

        //declaramos el objeto
        Usuario usuario = new Usuario();

        //usuario.setId(id);
        usuario.setNombre("Yenia");
        usuario.setApellido("Montero");
        usuario.setEmail("email@gmail.com");
        usuario.setPassword("ola95");

        return usuario;

    }
    //eliminar usuario
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable long id) { //con autorization es para verificar que se tenga la sesion iniciada, ene ste casi aantes de eliminar un usuario d ela tabala

        if (!validarToken (token)){
            return;
        }
        usuarioDao.eliminar(id);
    }
}
