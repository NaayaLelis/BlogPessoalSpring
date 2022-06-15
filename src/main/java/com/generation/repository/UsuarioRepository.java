package com.generation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional <Usuario> findByUsuario(String usuario);

	public List<Usuario> findAllByNomeContainingIgnoreCase(String string);

}

