/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.dto.TokenDto;
import br.com.cesarfilho.sistemaacademico.model.Usuario;
import br.com.cesarfilho.sistemaacademico.repository.UsuarioRepository;
import br.com.cesarfilho.sistemaacademico.security.TokenServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author franc
 */
@RestController
@RequestMapping("api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenServiceUsuario tokenServiceUsuario;

    @RequestMapping(value = "/usuario/", method = RequestMethod.POST)
    public ResponseEntity<Usuario> cadastrar(@RequestBody TokenDto tokenDto) {
        String token = tokenDto.getToken().substring(7, tokenDto.getToken().length());
        Long idUsuario = tokenServiceUsuario.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if (usuario != null) {
            return ResponseEntity.ok(usuario);

        }
        return ResponseEntity.badRequest().build();
    }
}
