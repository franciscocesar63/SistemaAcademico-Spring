/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Diretor;
import br.com.cesarfilho.sistemaacademico.model.Endereco;
import br.com.cesarfilho.sistemaacademico.model.Perfil;
import br.com.cesarfilho.sistemaacademico.model.Pessoa;
import br.com.cesarfilho.sistemaacademico.model.Usuario;
import br.com.cesarfilho.sistemaacademico.repository.DiretorRepository;
import br.com.cesarfilho.sistemaacademico.repository.EnderecoRepository;
import br.com.cesarfilho.sistemaacademico.repository.PerfilRepository;
import br.com.cesarfilho.sistemaacademico.repository.PessoaRepository;
import br.com.cesarfilho.sistemaacademico.repository.TelefoneRepository;
import br.com.cesarfilho.sistemaacademico.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private PerfilRepository perfilRepository;

    @RequestMapping(value = "/cadastrarDiretor/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Diretor json) {
        //salvando endereço
        Endereco endereco = json.getPessoa().getEndereco();
        enderecoRepository.save(endereco);

        json.getPessoa().getTelefones().forEach(t -> {
            System.out.println("numero: " + t.getNumero());
            telefoneRepository.save(t);
        });

        Usuario usuario = json.getPessoa().getUsuario();
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        //Perfil pré-definido no banco 1-admin 2-diretor 3-coordenador 4-professor 5-aluno 6-user
        Perfil perfilAluno = perfilRepository.findById(6l).get();
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(perfilAluno);

        usuario.setPerfis(perfis);

        //salvando usuário
        usuarioRepository.save(json.getPessoa().getUsuario());

        //salvando pessoa
        Pessoa pessoa = json.getPessoa();
        pessoaRepository.save(pessoa);

        diretorRepository.save(json);
        System.out.println("cadastrado com sucesso");

    }

    @PostMapping("/diretores")
    public List<Diretor> getDiretor() {
        List<Diretor> diretores = new ArrayList<>();
        diretorRepository.findAll().forEach(c -> {
            diretores.add(c);
        });

        return diretores;
    }

    @DeleteMapping("/deletarDiretor/{id}")
    public void deleteDiretor(@PathVariable Long id) {
        Diretor diretor = new Diretor();
        diretor.setID(id);
        diretorRepository.delete(diretor);

    }

    @PutMapping("/atualizarDiretor/")
    public void updateDiretor(@RequestBody Diretor json) {
        String criptografada = new BCryptPasswordEncoder().encode(json.getPessoa().getUsuario().getSenha());
        json.getPessoa().getUsuario().setSenha(criptografada);
        Diretor diretor = json;
        diretorRepository.save(diretor);
    }
}
