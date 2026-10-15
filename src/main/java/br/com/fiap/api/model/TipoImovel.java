package br.com.fiap.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TipoImovel {

    private int codigo;
    private String nome;
    private LocalDateTime dataCadastro;

    public TipoImovel(){}

    public TipoImovel(String nome, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public TipoImovel(int codigo, String nome, LocalDateTime dataCadastro) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
