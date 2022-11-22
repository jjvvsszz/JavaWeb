package br.com.fintech.dao;

import br.com.fintech.beans.Conta;
import br.com.fintech.beans.User;
import br.com.fintech.jdbc.FiapOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContaDao {
    private Connection conexao;

    public void novaConta(User user, int nr_agencia, int nr_conta) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("INSERT INTO T_FIN_CONTA(NR_CPF, NR_AGENCIA, NR_CONTA) VALUES (?, ?, ?)");
            stmt.setString(1, user.getNr_cpf());
            stmt.setInt(2, nr_agencia);
            stmt.setInt(3, nr_conta);

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

    public ArrayList<Conta> listarContas(User user) {
        conexao = FiapOracle.obterconexao();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Conta> lista = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM T_FIN_CONTA WHERE NR_CPF = ?");
            stmt.setString(1, user.getNr_cpf());
            rs = stmt.executeQuery();

            while(rs.next()) {
                Conta conta = new Conta(rs.getInt("ID_CONTA"), rs.getString("NR_CPF"),
                        rs.getInt("NR_AGENCIA"), rs.getInt("NR_CONTA"));
                lista.add(conta);
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

    public void removerConta(User user, Conta conta) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("DELETE FROM T_FIN_CONTA WHERE NR_CPF = ? AND ID_CONTA = ?");
            stmt.setString(1,user.getNr_cpf());
            stmt.setInt(2,conta.getId_conta());
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
}
