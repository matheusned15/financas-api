package com.nedstore.minhasfinancasapi.model.repository;

import com.nedstore.minhasfinancasapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
