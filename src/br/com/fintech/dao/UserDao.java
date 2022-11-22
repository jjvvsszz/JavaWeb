package br.com.fintech.dao;

import br.com.fintech.beans.Login;
import br.com.fintech.beans.User;
import br.com.fintech.jdbc.FiapOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conexao;

    public boolean cpfExistente(String nr_cpf) {
        PreparedStatement stmt = null;
        ResultSet rs;
        int result;
        boolean existeste = true;
        try {
            conexao = FiapOracle.obterconexao();
            stmt = conexao.prepareStatement("SELECT * FROM T_FIN_USER WHERE NR_CPF = ?");
            stmt.setString(1, nr_cpf);
            rs = stmt.executeQuery();

            result = rs.getFetchSize();

            existeste = result != 0;
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
        return existeste;
    }

    public boolean emailExistente(String nm_email) {
        PreparedStatement stmt = null;
        ResultSet rs;
        int result;
        boolean existeste = true;
        try {
            conexao = FiapOracle.obterconexao();
            stmt = conexao.prepareStatement("SELECT * FROM T_FIN_AUTH WHERE NM_EMAIL = ?");
            stmt.setString(1, nm_email);
            rs = stmt.executeQuery();

            result = rs.getFetchSize();

            existeste = result != 0;
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
        return existeste;
    }

    public User criarUser(String nr_cpf, String nm_nome, String vl_passhash, String nm_email) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("INSERT INTO T_FIN_USER(NR_CPF, NM_NOME) VALUES (?, ?)");
            stmt.setString(1, nr_cpf);
            stmt.setString(2, nm_nome);

            stmt = conexao.prepareStatement("INSERT INTO T_FIN_AUTH(NR_CPF, VL_PASSHASH, NM_EMAIL) VALUES (?, ?, ?)");
            stmt.setString(1, nr_cpf);
            stmt.setString(2, vl_passhash);
            stmt.setString(3, nm_email);

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
        return new User(nr_cpf, nm_nome, new Login(nr_cpf, nm_email, vl_passhash));
    }

    public void deletarUser(User user) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("DELETE FROM T_FIN_USER WHERE NR_CPF = ?");
            stmt.setString(1,user.getNr_cpf());
            stmt.executeQuery();

            stmt = conexao.prepareStatement("DELETE FROM T_FIN_AUTH WHERE NR_CPF = ?");
            stmt.setString(1,user.getNr_cpf());
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

    public void atualizarCadastro(User user) {
        PreparedStatement stmt = null;

        try {
            conexao = FiapOracle.obterconexao();

            stmt = conexao.prepareStatement("UPDATE T_FIN_USER SET NM_NOME = ? WHERE ?");
            stmt.setString(1, user.getNm_nome());
            stmt.setString(2, user.getNr_cpf());
            stmt.executeQuery();

            stmt = conexao.prepareStatement("UPDATE T_FIN_AUTH SET NM_EMAIL = ? WHERE ?");
            stmt.setString(1, user.getAuth().getNm_email());
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
