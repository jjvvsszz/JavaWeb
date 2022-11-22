package br.com.fintech.dao;

import br.com.fintech.app.Senha;
import br.com.fintech.beans.Login;
import br.com.fintech.beans.User;
import br.com.fintech.jdbc.FiapOracle;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private Connection conexao;

    public User login(String nr_cpf, String senhaDigitada) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PreparedStatement stmt = null;
        ResultSet rs;
        String nm_email = null;
        String nm_nome = null;
        String vl_passhash = Senha.converterParaHash(senhaDigitada);

        if (Senha.verificarSenha(senhaDigitada, nr_cpf)){
            try {
                conexao = FiapOracle.obterconexao();

                stmt = conexao.prepareStatement("SELECT NM_EMAIL FROM T_FIN_AUTH WHERE NR_CPF = ? AND VL_PASSHASH = ?");
                stmt.setString(1, nr_cpf);
                stmt.setString(2, vl_passhash);
                rs = stmt.executeQuery();
                rs.next();
                nm_email = rs.getString("NM_EMAIL");

                stmt = conexao.prepareStatement("SELECT NM_NOME FROM T_FIN_USER WHERE NR_CPF = ?");
                stmt.setString(1, nm_email);
                rs = stmt.executeQuery();
                rs.next();
                nm_nome = rs.getString("NM_NOME");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert stmt != null;
                    stmt.close();
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return new User(nr_cpf,nm_nome, new Login(nr_cpf,nm_email,vl_passhash));
        } else return null;
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

    public String retornarHash(String nr_cpf) {
        PreparedStatement stmt = null;
        ResultSet rs;
        String hash = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("SELECT VL_PASSHASH FROM T_FIN_AUTH WHERE NR_CPF = ?");
            stmt.setString(1, nr_cpf);
            rs = stmt.executeQuery();
            rs.next();
            hash = rs.getString("VL_PASSHASH");

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
        return hash;
    }
}
