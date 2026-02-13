package org.example.br.com.sistema.domain;

public class Paciente {

    //Model do Paciente
    private int id;
    private String nomeCompleto;
    private String dataDeNacimento;
    private String cpf;
    private String nomeCompletoDaMãe;
    private String nomeCompletoDaPai;
    private String numeroDETElefone;
    private int CEP;

    public Paciente(int id, String nomeCompleto, String dataDeNacimento, String cpf, String nomeCompletoDaMãe, String nomeCompletoDaPai, String numeroDETElefone, int CEP) {
        this.nomeCompleto = nomeCompleto;
        this.dataDeNacimento = dataDeNacimento;
        this.cpf = cpf;
        this.nomeCompletoDaMãe = nomeCompletoDaMãe;
        this.nomeCompletoDaPai = nomeCompletoDaPai;
        this.numeroDETElefone = numeroDETElefone;
        this.CEP = CEP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataDeNacimento() {
        return dataDeNacimento;
    }

    public void setDataDeNacimento(String dataDeNacimento) {
        this.dataDeNacimento = dataDeNacimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompletoDaMãe() {
        return nomeCompletoDaMãe;
    }

    public void setNomeCompletoDaMãe(String nomeCompletoDaMãe) {
        this.nomeCompletoDaMãe = nomeCompletoDaMãe;
    }

    public String getNomeCompletoDaPai() {
        return nomeCompletoDaPai;
    }

    public void setNomeCompletoDaPai(String nomeCompletoDaPai) {
        this.nomeCompletoDaPai = nomeCompletoDaPai;
    }

    public String getNumeroDETElefone() {
        return numeroDETElefone;
    }

    public void setNumeroDETElefone(String numeroDETElefone) {
        this.numeroDETElefone = numeroDETElefone;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }
}
