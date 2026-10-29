package br.com.fiap.api.dto;

import br.com.fiap.api.model.TipoImovel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ImovelRequestDto {

    @NotBlank
    @Size(max = 255, min = 3)
    private String descricao;

    @Positive
    private double metros;

    @Positive
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
