/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Curso;
import br.com.cesarfilho.sistemaacademico.model.Disciplina;
import br.com.cesarfilho.sistemaacademico.repository.CursoRepository;
import br.com.cesarfilho.sistemaacademico.repository.DisciplinaRepository;
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
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/disciplinas")
    public List<Disciplina> getCursos() {
        List<Disciplina> cursos = new ArrayList<>();
        disciplinaRepository.findAll().forEach(c -> {
            cursos.add(c);
        });

        return cursos;
    }

    @RequestMapping(value = "/cadastrarDisciplina/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Disciplina json) {
        Disciplina disciplina = json;
        
        Curso curso = cursoRepository.findById(json.getCurso().getId()).get();
        disciplina.setCurso(curso);

        disciplinaRepository.save(disciplina);
    }
}
