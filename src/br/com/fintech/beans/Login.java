package br.com.fintech.beans;

public class Login {
    private final String nr_cpf;
    private String nm_email;
    private String vl_passhash;

    public Login(String nr_cpf, String nm_email, String vl_passhash) {
        this.nr_cpf = nr_cpf;
        this.nm_email = nm_email;
        this.vl_passhash = vl_passhash;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }

    public String getNm_email() {
        return nm_email;
    }

    public void setNm_email(String nm_email) {
        this.nm_email = nm_email;
    }

    public String getVl_passhash() {
        return vl_passhash;
    }

    public void setVl_passhash(String vl_passhash) {
        this.vl_passhash = vl_passhash;
    }
}