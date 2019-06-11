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
public enum GestaoErro {

    /**
     *
     */
    DATA_EXISTENTE,

    /**
     *
     */
    DATA_INVALIDA,

    /**
     *
     */
    CAMPOS_VAZIOS,

    /**
     *
     */
    VENDA_JA_EXISTE,

    /**
     *
     */
    CODIGO_EXISTE,

    /**
     *
     */
    ERRO_CATEGORIA,

    /**
     *
     */
    CATEGORIA_NOTFOUND,

    /**
     *
     */
    EMPREGADO_NOTFOUND,

    /**
     *
     */
    CATEGORIA_INVALIDA,

    /**
     *
     */
    DATA_INEXISTENTE,

    /**
     *
     */
    ERRO_ADICIONAR_EMP;

    @Override
    public String toString() {

        switch (this) {
            case DATA_EXISTENTE:
                return "\nERRO: A data já existe.";
            case DATA_INVALIDA:
                return "\nERRO: A data é inválida.";
            case CAMPOS_VAZIOS:
                return "\nERRO: Exitem campos vazios.";
            case VENDA_JA_EXISTE:
                return "\nERRO: Não pode existir vendas duplicadas.";
            case CODIGO_EXISTE:
                return "\nERRO: O código já existe.";
            case ERRO_CATEGORIA:
                return "\nERRO: O empregado não pertence a nenhuma das categorias.";
            case EMPREGADO_NOTFOUND:
                return "\nERRO: Empregado não encontrado.";
            case CATEGORIA_NOTFOUND:
                return "\nERRO: Categoria não existe.";
            case CATEGORIA_INVALIDA:
                return "\nERRO: O empregado não pertence a esta categoria.";
            case ERRO_ADICIONAR_EMP:
                return "\nERRO: Não foi possivel adicionar todos os empregados, existe códigos repetidos.";
        }
        return "";
    }
}
