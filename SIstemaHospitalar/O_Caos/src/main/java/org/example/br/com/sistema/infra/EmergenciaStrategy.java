package org.example.br.com.sistema.infra;

import org.example.br.com.sistema.domain.IAtendimentoStrategy;

public class EmergenciaStrategy implements IAtendimentoStrategy {
    public void verificarUrgencia() { System.out.println("Verificando sinais vitais..."); }
    public void solicitarExameLaboratorial() { /* Vazio - Violação de ISP */ }
    public void executar() { System.out.println("Realizando atendimento de EMERGÊNCIA!"); }
}
