package br.com.fintech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class FiapOracle {

    public static Connection obterconexao() {
        Connection conexao = null;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexao;
    }

}
