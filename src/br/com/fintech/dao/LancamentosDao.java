package br.com.fintech.dao;

import br.com.fintech.beans.Conta;
import br.com.fintech.beans.Lancamentos;
import br.com.fintech.jdbc.FiapOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LancamentosDao{
    private Connection conexao;

    public void novo(Conta conta, double valor) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("INSERT INTO T_FIN_LANCAMENTOS(ID_CONTA, VL_LANCAMENTO, NR_CPF) VALUES (?, ?, ?)");
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

    public void remover(Lancamentos movimento, Conta conta) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();
            String sql = "DELETE FROM T_FIN_LANCAMENTOS WHERE ID_LANCAMENTO = ? AND ID_CONTA = ? AND ID_CPF = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,movimento.getId_lancamento());
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

    public ArrayList<Lancamentos> listar(Conta conta) {
        conexao = FiapOracle.obterconexao();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Lancamentos> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FIN_LANCAMENTOS WHERE ID_CONTA = ? AND NR_CPF = ?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, conta.getId_conta());
            stmt.setString(2, conta.getNr_cpf());
            rs = stmt.executeQuery();

            while(rs.next()) {
                Lancamentos movimento = new Lancamentos(rs.getInt("ID_LANCAMENTO"),
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
        for (Lancamentos moviemnto : listar(conta)) {
            saldo += moviemnto.getVl_lancamento();
        }
        return saldo;
    }
}
