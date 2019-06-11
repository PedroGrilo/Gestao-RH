/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaorh.exceptions;

/**
 *
 * @author Pedro Grilo
 */
public class GestaoException extends RuntimeException {

    private GestaoErro codigoErro;

    /**
     *
     * @param codigoErro
     */
    public GestaoException(GestaoErro codigoErro) {
        super(codigoErro.toString());
        this.codigoErro = codigoErro;
    }

}
