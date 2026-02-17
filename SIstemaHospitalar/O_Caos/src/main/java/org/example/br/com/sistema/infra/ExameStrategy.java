package org.example.br.com.sistema.infra;

import org.example.br.com.sistema.domain.IAtendimentoStrategy;

public class ExameStrategy implements IAtendimentoStrategy {
    public void verificarUrgencia() { /* Vazio - Violação de ISP */ }
    public void solicitarExameLaboratorial() { System.out.println("Coletando sangue para exame..."); }
    public void executar() { System.out.println("Realizando procedimento de EXAME."); }
}
