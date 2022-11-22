package br.com.fintech.dao;

import br.com.fintech.beans.Conta;
import br.com.fintech.beans.Investimento;
import br.com.fintech.jdbc.FiapOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestimentosDao {
    private Connection conexao;

    public void novo(Conta conta, double valor) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("INSERT INTO T_FIN_Investimento(ID_CONTA, VL_INVESTIMENTO, NR_CPF) VALUES (?, ?, ?)");
            stmt.setInt(1, conta.getId_conta());
            stmt.setDouble(2, valor);
            stmt.setString(3, conta.getNr_cpf());

            stmt.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                conexao.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void remover(Investimento movimento, Conta conta) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();
            String sql = "DELETE FROM T_FIN_Investimento WHERE ID_INVESTIMENTO = ? AND ID_CONTA = ? AND ID_CPF = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,movimento.getId_investimento());
            stmt.setInt(2,movimento.getId_conta());
            stmt.setString(3, conta.getNr_cpf());
            stmt.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                conexao.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Investimento> listar(Conta conta) {
        conexao = FiapOracle.obterconexao();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Investimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FIN_INVESTIMENTO WHERE ID_CONTA = ? AND NR_CPF = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, conta.getId_conta());
            stmt.setString(2, conta.getNr_cpf());
            rs = stmt.executeQuery();

            while(rs.next()) {
                Investimento movimento = new Investimento(rs.getInt("ID_LANCAMENTO"),
                        rs.getInt("ID_CONTA"), rs.getDouble("VL_LANCAMENTO"), conta.getNr_cpf());
                lista.add(movimento);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                conexao.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public double saldo(Conta conta) {
        double saldo = 0;
        for (Investimento moviemnto : listar(conta)) {
            saldo += moviemnto.getVl_investimento();
            saldo += moviemnto.getVl_variacao();
        }
        return saldo;
    }

    public void variar(double variacao, Investimento movimento) {
        PreparedStatement stmt = null;
        ResultSet rs;

        try{
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("SELECT VL_VARIACAO FROM T_FIN_INVESTIMENTO WHERE " +
                    "ID_INVESTIMENTO = ? AND ID_CONTA = ? AND NR_CPF = ?");
            stmt.setInt(1, movimento.getId_investimento());
            stmt.setInt(2, movimento.getId_conta());
            stmt.setString(3, movimento.getNr_cpf());
            rs = stmt.executeQuery();
            rs.next();
            variacao += rs.getDouble("VL_VARIACAO");

            stmt = conexao.prepareStatement("UPDATE T_FIN_INVESTIMENTO SET VL_VARIACAO = ? WHERE " +
                    "ID_INVESTIMENTO = ? AND ID_CONTA = ? AND NR_CPF = ?");
            stmt.setInt(2, movimento.getId_investimento());
            stmt.setInt(3, movimento.getId_conta());
            stmt.setString(4, movimento.getNr_cpf());
            stmt.setDouble(1, variacao);
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                conexao.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
