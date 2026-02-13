package org.example.service;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;
import org.example.infra.PainelNotification;

public class TriagemService {
    private IProtocoloAtendimento protocolo;
    private PainelNotification painel = new PainelNotification();

    public void configurarProtocolo(IProtocoloAtendimento novoProtocolo) {
        this.protocolo = novoProtocolo;
    }

    public void realizarAtendimento(Paciente paciente) {
        if (protocolo == null) {
            System.out.println("Erro! Nenhum protocolo foi configurado!");
            return;
        }

        String resultado = protocolo.priorizar(paciente);

        painel.chamarNoPainel(resultado);
    }
}
