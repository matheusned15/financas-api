package com.nedstore.minhasfinancasapi.exception;

public class ErroAutenticacao  extends RuntimeException{

    public ErroAutenticacao(String mensagem){
        super(mensagem);
    }
}
