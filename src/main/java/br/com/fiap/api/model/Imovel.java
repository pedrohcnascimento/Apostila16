package br.com.fiap.api.model;

public class Imovel {

    private int codigo;
    private String descricao;
    private double metros;
    private double valor;
    private TipoImovel tipo;

    public Imovel(){}

    public Imovel(String descricao, double metros, double valor) {
        this.descricao = descricao;
        this.metros = metros;
        this.valor = valor;
    }

    public Imovel(int codigo, String descricao, double metros, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.metros = metros;
        this.valor = valor;
    }

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

    public TipoImovel getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo;
    }
}
