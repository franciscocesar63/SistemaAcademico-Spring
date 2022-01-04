/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Diretor;
import br.com.cesarfilho.sistemaacademico.model.Endereco;
import br.com.cesarfilho.sistemaacademico.model.Pessoa;
import br.com.cesarfilho.sistemaacademico.model.Usuario;
import br.com.cesarfilho.sistemaacademico.repository.DiretorRepository;
import br.com.cesarfilho.sistemaacademico.repository.EnderecoRepository;
import br.com.cesarfilho.sistemaacademico.repository.PessoaRepository;
import br.com.cesarfilho.sistemaacademico.repository.TelefoneRepository;
import br.com.cesarfilho.sistemaacademico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class DiretorController {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/cadastrarDiretor/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Diretor json) {
        Endereco endereco = json.getPessoa().getEndereco();
        enderecoRepository.save(endereco);

        json.getPessoa().getTelefones().forEach(t -> {
            telefoneRepository.save(t);
        });
        Usuario usuario = json.getPessoa().getUsuario();
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        
        usuarioRepository.save(json.getPessoa().getUsuario());

        Pessoa pessoa = json.getPessoa();
        pessoaRepository.save(pessoa);

        System.out.println(json);

        diretorRepository.save(json);
        System.out.println("cadastrado com sucesso");

    }
}
