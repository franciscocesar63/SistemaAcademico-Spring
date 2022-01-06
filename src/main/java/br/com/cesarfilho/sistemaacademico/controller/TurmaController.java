/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Curso;
import br.com.cesarfilho.sistemaacademico.model.Turma;
import br.com.cesarfilho.sistemaacademico.repository.CursoRepository;
import br.com.cesarfilho.sistemaacademico.repository.TurmaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/turmas")
    public List<Turma> getCursos() {
        List<Turma> turma = new ArrayList<>();
        turmaRepository.findAll().forEach(c -> {
            turma.add(c);
        });

        return turma;
    }

    @RequestMapping(value = "/cadastrarTurma/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Turma json) {
        Turma disciplina = json;

        Curso curso = cursoRepository.findById(json.getCurso().getId()).get();
        disciplina.setCurso(curso);

        turmaRepository.save(disciplina);
    }
}
