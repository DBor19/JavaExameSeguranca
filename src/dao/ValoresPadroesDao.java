package dao;

import connections.ConnectionFactory;
import entities.ValoresPadroes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ValoresPadroesDao {

    private final Connection con;

    public ValoresPadroesDao() throws Exception {
        this.con = ConnectionFactory.getConnection();
    }

    public List<ValoresPadroes> listar() throws Exception {
        String sql = "SELECT * FROM valorespadroes";
        PreparedStatement stmt = this.con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<ValoresPadroes> valores = new ArrayList<>();
        while (rs.next()) {
        	ValoresPadroes valor = new ValoresPadroes();
            valor.setId(rs.getInt("id"));
            valor.setDescricao(rs.getString("descricao"));
            valor.setLimiteInferior(rs.getInt("limite_inferior"));
            valor.setLimiteSuperior(rs.getInt("limite_superior"));
            valor.setUnidade(rs.getString("unidade"));
            valores.add(valor);
        }
        stmt.close();
        rs.close();
        con.close();
        return valores;
    }
}
