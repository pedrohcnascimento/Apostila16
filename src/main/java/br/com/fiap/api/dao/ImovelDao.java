package br.com.fiap.api.dao;

import br.com.fiap.api.exception.EntidadeNaoEncontradaException;
import br.com.fiap.api.model.Imovel;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImovelDao {

    private final DataSource dataSource;
    private static final String INSERT_SQL = "insert into t_api_imovel (cd_imovel, ds_imovel, nr_dimensao, vl_imovel, cd_tipo) values (sq_t_api_imovel.nextval,?,?,?,?)";
    private static final String SELECT_SQL = "select * from t_api_imovel";
    private static final String SELECT_BY_ID_SQL = "select * from t_api_imovel where cd_imovel = ?";
    private static final String UPDATE_SQL = "update t_api_imovel set ds_imovel = ?, nr_dimensao = ?, vl_imovel = ? where cd_imovel = ?";
    private static final String DELETE_SQL = "delete from t_api_imovel where cd_imovel = ?";
    private static final String SELECT_BY_TIPO_IMOVEL_SQL = "select * from t_api_imovel where cd_tipo = ?";

    public ImovelDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Imovel> buscarPorTipo(int idTipoImovel) throws SQLException {
        try (Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(SELECT_BY_TIPO_IMOVEL_SQL)){
            stmt.setInt(1, idTipoImovel);
            ResultSet resultSet = stmt.executeQuery();
            List<Imovel> lista = new ArrayList<>();
            while (resultSet.next())
                lista.add(getImovel(resultSet));
            return lista;
        }
    }

    public void deletar(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(DELETE_SQL)){
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            if (linhas == 0)
                throw new EntidadeNaoEncontradaException("Não tem imovel para apagar");
        }
    }

    public void atualizar(Imovel imovel) throws SQLException, EntidadeNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(UPDATE_SQL)){
            stmt.setString(1, imovel.getDescricao());
            stmt.setDouble(2, imovel.getMetros());
            stmt.setDouble(3, imovel.getValor());
            stmt.setInt(4, imovel.getCodigo());
            int linhas = stmt.executeUpdate();
            if (linhas == 0)
                throw new EntidadeNaoEncontradaException("Imovel não encontrado para atualizar");
        }
    }

    public Imovel pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(SELECT_BY_ID_SQL)){
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next())
                throw new EntidadeNaoEncontradaException("Imóvel não encontrado");
            return getImovel(resultSet);
        }
    }

    public List<Imovel> listar() throws SQLException {
        try (Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(SELECT_SQL)){
            ResultSet resultSet = stmt.executeQuery();
            List<Imovel> lista = new ArrayList<>();
            while (resultSet.next())
                lista.add(getImovel(resultSet));
            return lista;
        }
    }

    private Imovel getImovel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("cd_imovel");
        String descricao = resultSet.getString("ds_imovel");
        double dimensao = resultSet.getDouble("nr_dimensao");
        double valor = resultSet.getDouble("vl_imovel");
        return new Imovel(id, descricao, dimensao, valor);
    }

    public void cadastrar(Imovel imovel) throws SQLException {
        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERT_SQL, new String[]{"cd_imovel"})){
             stmt.setString(1, imovel.getDescricao());
             stmt.setDouble(2, imovel.getMetros());
             stmt.setDouble(3, imovel.getValor());
             stmt.setInt(4, imovel.getTipo().getCodigo());
             stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next())
                imovel.setCodigo(resultSet.getInt(1));
        }
    }

}
