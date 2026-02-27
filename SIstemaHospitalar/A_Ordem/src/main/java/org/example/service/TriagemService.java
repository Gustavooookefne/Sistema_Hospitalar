package org.example.service;

import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

public class TriagemService {
    private final INotification notification;
    private final IPacienteRepository pacienteRepository;
    // Composicao da Strategy selecionada em tempo de execucao.
    private IProtocoloAtendimento protocolo;

    public TriagemService(INotification notification, IPacienteRepository pacienteRepository) {
        // DIP: service depende de abstracoes, nao de classes concretas da infra.
        this.notification = notification;
        this.pacienteRepository = pacienteRepository;
    }

    public void configurarProtocolo(IProtocoloAtendimento novoProtocolo) {
        this.protocolo = novoProtocolo;
    }

    public void realizarAtendimento(Paciente paciente) {
        if (protocolo == null) {
            System.out.println("Erro! Nenhum protocolo foi configurado!");
            return;
        }

        // Persiste o paciente antes de aplicar o protocolo de atendimento.
        pacienteRepository.salvar(paciente);
        String resultado = protocolo.priorizar(paciente);
        notification.notificar(resultado);
    }
}
