package com.AppGlobal.app.controller;

import com.AppGlobal.app.dao.UsuarioDao;
import com.AppGlobal.app.models.Usuario;
import com.AppGlobal.app.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST) //requiest mapping valor de la url
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogin = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogin != null){

            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogin.getId()), usuarioLogin.getEmail());
            return tokenJwt;
        }
        return "FALSE";
    }
}
