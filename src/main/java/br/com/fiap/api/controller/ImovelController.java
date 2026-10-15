package br.com.fiap.api.controller;

import br.com.fiap.api.dao.ImovelDao;
import br.com.fiap.api.dto.ImovelRequestDto;
import br.com.fiap.api.dto.ImovelResponseDto;
import br.com.fiap.api.exception.EntidadeNaoEncontradaException;
import br.com.fiap.api.model.Imovel;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
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
    public ResponseEntity<ImovelResponseDto> atualizar(@PathVariable int id, @RequestBody ImovelRequestDto dto) throws EntidadeNaoEncontradaException, SQLException {
        Imovel imovel = mapper.map(dto, Imovel.class);
        imovel.setCodigo(id);
        dao.atualizar(imovel);
        return ResponseEntity.ok(mapper.map(imovel, ImovelResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelResponseDto> buscar(@PathVariable int id) throws EntidadeNaoEncontradaException, SQLException {
        Imovel imovel = dao.pesquisarPorId(id);
        return ResponseEntity.ok(mapper.map(imovel, ImovelResponseDto.class)); //Retorna o imovel com o status HTTP 200 OK
    }

    @GetMapping
    public List<ImovelResponseDto> listar() throws SQLException {
        return dao.listar().stream()
                .map(imovel -> mapper.map(imovel, ImovelResponseDto.class))
                .toList();
    }

    @PostMapping
    public ResponseEntity<ImovelResponseDto> cadastrar(@RequestBody ImovelRequestDto dto,
                                                       UriComponentsBuilder builder) throws SQLException {

        Imovel imovel = mapper.map(dto, Imovel.class);
        dao.cadastrar(imovel);

        URI uri = builder.path("/imoveis/{id}")
                .buildAndExpand(imovel.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(imovel, ImovelResponseDto.class));
    }

}
