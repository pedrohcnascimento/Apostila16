package br.com.fiap.api.dto;

import br.com.fiap.api.model.TipoImovel;

public class ImovelRequestDto {

    private String descricao;
    private double metros;
    private double valor;
    private TipoImovelRequestDto tipo;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMetros() {
        return metros;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoImovelRequestDto getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovelRequestDto tipo) {
        this.tipo = tipo;
    }
}
