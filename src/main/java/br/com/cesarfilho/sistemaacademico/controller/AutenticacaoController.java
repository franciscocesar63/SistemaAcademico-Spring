/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.dto.LoginForm;
import br.com.cesarfilho.sistemaacademico.dto.TokenDto;
import br.com.cesarfilho.sistemaacademico.security.TokenServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author franc
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServiceUsuario tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody LoginForm login) {
        UsernamePasswordAuthenticationToken dadosLogin = login.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authentication);
            System.out.println(token);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
