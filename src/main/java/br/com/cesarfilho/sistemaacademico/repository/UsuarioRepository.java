/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cesarfilho.sistemaacademico.repository;

import br.com.cesarfilho.sistemaacademico.model.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franc
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String usuario);

}