package br.com.fintech.beans;

public class User{
    private final String nr_cpf;
    private String nm_nome;
    private final Login auth;

    public User(String nr_cpf, String nm_nome, Login auth) {
        this.nr_cpf = nr_cpf;
        this.nm_nome = nm_nome;
        this.auth = auth;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }

    public String getNm_nome() {
        return nm_nome;
    }

    public void setNm_nome(String nm_nome) {
        this.nm_nome = nm_nome;
    }

    public Login getAuth() {
        return auth;
    }
}
