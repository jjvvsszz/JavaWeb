package br.com.fintech.beans;

public class Investimento {
    private final int id_investimento;
    private final int id_conta;
    private final double vl_investimento;
    private double vl_variacao;
    private final String nr_cpf;

    public Investimento(int id_investimento, int id_conta, double vl_investimento, String nr_cpf) {
        this.id_investimento = id_investimento;
        this.id_conta = id_conta;
        this.vl_investimento = vl_investimento;
        this.nr_cpf = nr_cpf;
        this.vl_variacao = 0;
    }

    public double getVl_variacao() {
        return vl_variacao;
    }

    public void setVl_variacao(double vl_variacao) {
        this.vl_variacao = vl_variacao;
    }

    public int getId_investimento() {
        return id_investimento;
    }

    public int getId_conta() {
        return id_conta;
    }

    public double getVl_investimento() {
        return vl_investimento;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }
}
