package com.nedstore.minhasfinancasapi;

import com.nedstore.minhasfinancasapi.exception.RegraNegocioException;
import com.nedstore.minhasfinancasapi.model.entity.Usuario;
import com.nedstore.minhasfinancasapi.model.repository.UsuarioRepository;
import com.nedstore.minhasfinancasapi.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    private static String EMAIL = "kingned@gmail.com";

    private static String NOME = "KING NED";

    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test()
    public void deveValidarEmail() {
        Assertions.assertDoesNotThrow(() -> {

            // cenario
            repository.deleteAll();

            // acao
            service.validarEmail(EMAIL);
        });
    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            //cenario
            Usuario usuario = Usuario.builder().nome(NOME).email(EMAIL).build();
            repository.save(usuario);

            //acao
            service.validarEmail(EMAIL);
        });
    }

}
