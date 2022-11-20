package br.com.fintech.beans;

public class Conta {
    private final int id_conta;
    private final String nr_cpf;
    private final int nr_agencia;
    private final int nr_conta;

    public Conta(int id_conta, String nr_cpf, int nr_agencia, int nr_conta) {
        this.id_conta = id_conta;
        this.nr_cpf = nr_cpf;
        this.nr_agencia = nr_agencia;
        this.nr_conta = nr_conta;
    }

    public int getId_conta() {
        return id_conta;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }

    public int getNr_agencia() {
        return nr_agencia;
    }

    public int getNr_conta() {
        return nr_conta;
    }
}
