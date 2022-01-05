/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.controller;

import br.com.cesarfilho.sistemaacademico.model.Curso;
import br.com.cesarfilho.sistemaacademico.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hello")
    public Curso hello() {
        Curso curso = new Curso();
        curso.setNome("Computacao");
        curso.setArea("Exatas");
        return curso;
    }

    @RequestMapping(value = "/cadastrarCurso/", method = RequestMethod.POST)
    public void cadastrar(@RequestBody Curso json) {
        Curso curso = new Curso();
        curso.setNome(json.getNome());
        curso.setArea(json.getArea());
        cursoRepository.save(curso);
    }
}
