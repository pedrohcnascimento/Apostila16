package br.com.fiap.api.controller;

import br.com.fiap.api.dao.ImovelDao;
import br.com.fiap.api.dto.ImovelRequestDto;
import br.com.fiap.api.dto.ImovelResponseDto;
import br.com.fiap.api.exception.EntidadeNaoEncontradaException;
import br.com.fiap.api.model.Imovel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.DocFlavor;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("imoveis")
public class ImovelController {

    private ImovelDao dao;
    private ModelMapper mapper;

    public ImovelController(ImovelDao dao, ModelMapper mapper){
        this.mapper = mapper;
        this.dao = dao;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) throws SQLException, EntidadeNaoEncontradaException {
        dao.deletar(id);
        return ResponseEntity.noContent().build(); //Retorna o status 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable int id, @Valid @RequestBody ImovelRequestDto dto) throws EntidadeNaoEncontradaException, SQLException {
        Imovel imovel = mapper.map(dto, Imovel.class);
        imovel.setCodigo(id);
        dao.atualizar(imovel);
        return ResponseEntity.ok().build(); //Retorna o Status 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelResponseDto> buscar(@PathVariable int id) throws EntidadeNaoEncontradaException, SQLException {
        Imovel imovel = dao.pesquisarPorId(id);
        return ResponseEntity.ok(mapper.map(imovel, ImovelResponseDto.class)); //Retorna o imovel com o status HTTP 200 OK
    }

    @GetMapping
    public List<ImovelResponseDto> listar() throws SQLException {
        //Converte a lista de Imovel em uma lista de ImovelResponseDto
        return dao.listar().stream().map(
                churros -> mapper.map(churros, ImovelResponseDto.class)).toList();
    }

    @PostMapping
    public ResponseEntity<ImovelResponseDto> cadastrar(@Valid @RequestBody ImovelRequestDto dto,
                                                       UriComponentsBuilder builder) throws SQLException {

        Imovel imovel = mapper.map(dto, Imovel.class);
        dao.cadastrar(imovel);

        URI uri = builder.path("/imoveis/{id}")
                .buildAndExpand(imovel.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(imovel, ImovelResponseDto.class));
    }

}
