package io.authentication.exception;

import java.io.Serializable;

/**
 * Classe que representar o formato padrão do sistema {mensagem: 'mensagem'}
 * 
 * @author anderson
 */
public class MessageError implements Serializable {

    private static final long serialVersionUID = 1632852814749630704L;

    private String mensagem;

    /**
     * Construtor Padrão
     */
    public MessageError() {

    }

    /**
     * Construtor com a Mensagem de Erro
     * 
     * @param mensagem
     */
    public MessageError(String mensagem) {
	this.mensagem = mensagem;
    }

    /**
     * Recupera mensagem
     * 
     * @return
     */
    public String getMensagem() {
	return mensagem;
    }

    /**
     * Atribui um mensagem ao objeto
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }
}
