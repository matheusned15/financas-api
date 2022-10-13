package com.nedstore.minhasfinancasapi.service.impl;

import com.nedstore.minhasfinancasapi.exception.RegraNegocioException;
import com.nedstore.minhasfinancasapi.model.entity.Usuario;
import com.nedstore.minhasfinancasapi.model.repository.UsuarioRepository;
import com.nedstore.minhasfinancasapi.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository){
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if(existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return Optional.empty();
    }
}