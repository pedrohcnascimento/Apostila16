package br.com.fiap.api.controller;

import br.com.fiap.api.dao.ImovelDao;
import br.com.fiap.api.dao.TipoImovelDao;
import br.com.fiap.api.dto.ImovelResponseDto;
import br.com.fiap.api.dto.TipoImovelRequestDto;
import br.com.fiap.api.dto.TipoImovelResponseDto;
import br.com.fiap.api.model.Imovel;
import br.com.fiap.api.model.TipoImovel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("tipos-imoveis")
public class TipoImovelController {

    private TipoImovelDao dao;
    private ImovelDao imovelDao;
    private ModelMapper mapper;

    public TipoImovelController(TipoImovelDao dao, ImovelDao imovelDao, ModelMapper mapper) {
        this.imovelDao = imovelDao;
        this.dao = dao;
        this.mapper = mapper;
    }

    @GetMapping("/{id}/imoveis")
    public List<ImovelResponseDto> buscar(@PathVariable int id) throws SQLException {
        return imovelDao.buscarPorTipo(id).stream()
                .map(i -> mapper.map(i, ImovelResponseDto.class)).toList();
    }

    @GetMapping
    public List<TipoImovelResponseDto> listar() throws SQLException {
        return dao.listar().stream().map(
                t -> mapper.map(t, TipoImovelResponseDto.class)).toList();
    }

    @PostMapping
    public ResponseEntity<TipoImovelResponseDto> cadastrar(
           @Valid @RequestBody TipoImovelRequestDto dto,
            UriComponentsBuilder builder) throws SQLException {
        //Conveter o dto no model
        TipoImovel tipo = mapper.map(dto, TipoImovel.class);
        dao.cadastrar(tipo);
        URI uri = builder.path("tipos-imoveis/{id}").buildAndExpand(tipo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(tipo, TipoImovelResponseDto.class));
    }

}
