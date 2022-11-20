package br.com.fintech.beans;

import java.util.Calendar;

public class Lancamentos {
    private final int id_lancamento;
    private final int id_conta;
    private final double vl_lancamento;
    private final Calendar dt_data;

    public Lancamentos(int id_lancamento, int id_conta, double vl_lancamento, Calendar dt_data) {
        this.id_lancamento = id_lancamento;
        this.id_conta = id_conta;
        this.vl_lancamento = vl_lancamento;
        this.dt_data = dt_data;
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

    public Calendar getDt_data() {
        return dt_data;
    }
}
