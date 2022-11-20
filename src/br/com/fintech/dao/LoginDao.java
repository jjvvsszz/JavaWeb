package br.com.fintech.dao;

import br.com.fintech.beans.Login;
import br.com.fintech.beans.User;
import br.com.fintech.jdbc.FiapOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private Connection conexao;

    public User login(String nm_email, String vl_passhash) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nr_cpf = null;
        String nm_nome = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("SELECT NR_CPF FROM T_FIN_AUTH WHERE NM_EMAIL = ? AND VL_PASSHASH = ?");
            stmt.setString(1, nm_email);
            stmt.setString(2, vl_passhash);
            rs = stmt.executeQuery();
            rs.next();
            nr_cpf = rs.getString("NR_CPF");

            stmt = conexao.prepareStatement("SELECT NM_NOME FROM T_FIN_USER WHERE NR_CPF = ?");
            stmt.setString(1, nr_cpf);
            rs = stmt.executeQuery();
            rs.next();
            nm_nome = rs.getString("NM_NOME");
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
        return new User(nr_cpf, nm_nome, new Login(nr_cpf, nm_email, vl_passhash));
    }

    public void alterarSenha(User user, String vl_newpasshash) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("UPDATE T_FIN_AUTH SET VL_PASSHASH = ? WHERE NR_CPF = ?");
            stmt.setString(1, vl_newpasshash);
            stmt.setString(2, user.getNr_cpf());
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
