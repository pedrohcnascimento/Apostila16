package br.com.fiap.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TipoImovelRequestDto {

    private int codigo;

    @NotBlank(message = "Nome é obrigatório" )
    @Size(max = 60, min = 3, message = "Tamanho válido é de 3 a 60 caracteres")
    private String nome;

    @Future(message = "A data deve estar no futuro")
    private LocalDateTime dataCadastro;

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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
