package com.nedstore.minhasfinancasapi;

import com.nedstore.minhasfinancasapi.exception.ErroAutenticacao;
import com.nedstore.minhasfinancasapi.exception.RegraNegocioException;
import com.nedstore.minhasfinancasapi.model.entity.Usuario;
import com.nedstore.minhasfinancasapi.model.repository.UsuarioRepository;
import com.nedstore.minhasfinancasapi.service.UsuarioService;
import com.nedstore.minhasfinancasapi.service.impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    private static final String EMAIL = "kingned@gmail.com";
    @SpyBean
    UsuarioService service;
    @MockBean
    UsuarioRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);

    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        //cenário
        String email = "email@email.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        //acao
        Usuario result = service.autenticar(email, senha);

        //verificacao
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    public void deveLancarErroQUandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {

        //cenário
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        //acao
        Throwable exception = Assertions.catchThrowable( () -> service.autenticar("email@email.com", "senha") );

        //verificacao
        Assertions.assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class)
                .hasMessage("Usuário não encontrado para o email informado.");
    }

    @Test
    public void deveLancarErroQuandoSenhaNaoBater() {
        //cenario
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        //acao
        Throwable exception = Assertions.catchThrowable( () ->  service.autenticar("email@email.com", "123") );
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida.");

    }

    @Test
    public void deveValidarEmail() {
        // cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //acao
        service.validarEmail("email@email.com");
    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        //cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        //acao
        org.junit.jupiter.api.Assertions
                .assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
    }

}
