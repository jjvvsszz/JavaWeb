package br.com.fintech.beans;

public class Lancamentos {
    private final int id_lancamento;
    private final int id_conta;
    private final double vl_lancamento;
    private final String nr_cpf;

    public Lancamentos(int id_lancamento, int id_conta, double vl_lancamento, String nr_cpf) {
        this.id_lancamento = id_lancamento;
        this.id_conta = id_conta;
        this.vl_lancamento = vl_lancamento;
        this.nr_cpf = nr_cpf;
    }

    public int getId_lancamento() {
        return id_lancamento;
    }

    public int getId_conta() {
        return id_conta;
    }

    public double getVl_lancamento() {
        return vl_lancamento;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }
}
