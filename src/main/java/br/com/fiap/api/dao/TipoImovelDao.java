package br.com.fiap.api.dao;

import br.com.fiap.api.model.TipoImovel;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TipoImovelDao {

    private static final String INSERT_SQL = "insert into t_api_tipo_imovel (cd_tipo, nm_tipo, dt_cadastro) " +
            " values (sq_t_api_tipo_imovel.nextval, ?, ?)";

    private static final String SELECT_SQL = "select * from t_api_tipo_imovel";

    private DataSource dataSource;

    public TipoImovelDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TipoImovel> listar() throws SQLException {
        try(Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(SELECT_SQL)){
            ResultSet resultSet = stmt.executeQuery();
            List<TipoImovel> lista = new ArrayList<>();
            while (resultSet.next())
                lista.add(getTipoImovel(resultSet));
            return lista;
        }
    }

    private TipoImovel getTipoImovel(ResultSet resultSet) throws SQLException {
        int codigo = resultSet.getInt("cd_tipo");
        String nome = resultSet.getString("nm_tipo");
        LocalDateTime dataCadastro =
                resultSet.getObject("dt_cadastro", LocalDateTime.class);
        return new TipoImovel(codigo, nome, dataCadastro);
    }

    public void cadastrar(TipoImovel tipo) throws SQLException {
        try(Connection conexao = dataSource.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(INSERT_SQL, new String[] {"cd_tipo"})){
            stmt.setString(1, tipo.getNome());
            stmt.setObject(2, tipo.getDataCadastro());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                tipo.setCodigo(resultSet.getInt(1));
            }
        }
    }

}
