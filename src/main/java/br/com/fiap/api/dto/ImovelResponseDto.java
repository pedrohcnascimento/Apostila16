package br.com.fiap.api.dto;

public class ImovelResponseDto {

    private int codigo;
    private String descricao;
    private double metros;
    private double valor;
    private TipoImovelResponseDto tipo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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

    public TipoImovelResponseDto getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovelResponseDto tipo) {
        this.tipo = tipo;
    }
}
