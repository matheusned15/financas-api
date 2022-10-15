package com.nedstore.minhasfinancasapi;

import com.nedstore.minhasfinancasapi.exception.RegraNegocioException;
import com.nedstore.minhasfinancasapi.model.repository.UsuarioRepository;
import com.nedstore.minhasfinancasapi.service.UsuarioService;
import com.nedstore.minhasfinancasapi.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
    @BeforeEach
    public void setUp(){
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);

    }

    @Test()
    public void deveValidarEmail() {
        Assertions.assertDoesNotThrow(() -> {

            // cenario
              Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
            // acao
            service.validarEmail(EMAIL);
        });
    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        //cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        //acao
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
    }

}
