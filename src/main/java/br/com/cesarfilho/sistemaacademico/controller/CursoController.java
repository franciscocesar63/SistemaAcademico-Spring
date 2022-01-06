/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Curso;
import br.com.cesarfilho.sistemaacademico.repository.CursoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/cursos")
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        cursoRepository.findAll().forEach(c -> {
            cursos.add(c);
        });

        return cursos;
    }

    @DeleteMapping("/deletarCurso/{id}")
    public void deleteCursos(@PathVariable Long id) {
        Curso curso = new Curso();
        curso.setId(id);
        cursoRepository.delete(curso);

    }

    @RequestMapping(value = "/cadastrarCurso/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Curso json) {
        Curso curso = new Curso();
        curso.setNome(json.getNome());
        curso.setArea(json.getArea());
        cursoRepository.save(curso);
    }

    @PutMapping("/atualizarCurso/")
    public void updateCursos(@RequestBody Curso json) {
        Curso curso = json;
        cursoRepository.save(curso);
    }
}
