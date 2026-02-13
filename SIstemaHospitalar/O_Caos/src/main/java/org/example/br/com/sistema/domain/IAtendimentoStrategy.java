package org.example.br.com.sistema.domain;

public interface IAtendimentoStrategy {
    void verificarUrgencia();
    void solicitarExameLaboratorial();
    void executar();
}
