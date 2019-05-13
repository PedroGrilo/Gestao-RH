/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaorh;

/**
 *
 * @author Pedro Grilo
 */
public enum GestaoErro {
    DATA_EXISTENTE, DATA_INVALIDA, CAMPOS_VAZIOS, VENDA_JA_EXISTE, CODIGO_EXISTE;

    @Override
    public String toString() {

        switch (this) {
            case DATA_EXISTENTE:
                return "ERRO: A data já existe";
            case DATA_INVALIDA:
                return "ERRO: A data é invalida";
            case CAMPOS_VAZIOS:
                return "ERRO: Exitem campos vazios";
            case VENDA_JA_EXISTE:
                return "ERRO: Não pode existir vendas duplicadas";
            case CODIGO_EXISTE:
                return "ERRO: O código já existe, tente outro";
        }
        return "";
    }
}
