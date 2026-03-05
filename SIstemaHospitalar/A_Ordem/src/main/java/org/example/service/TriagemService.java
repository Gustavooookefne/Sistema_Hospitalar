package org.example.service;

import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

/**
 * SRP (Princípio da Responsabilidade Única):
 * O TriagemService não salva no banco, não imprime na tela e não calcula risco.
 * A única responsabilidade dele é ORQUESTRAR (coordenar) o atendimento usando suas ferramentas.
 */
public class TriagemService {

    // DIP (Princípio da Inversão de Dependência):
    // Repare que não usamos classes concretas aqui (como PainelNotification).
    // O serviço depende apenas das ABSTRAÇÕES (Interfaces).
    private final INotification notification;
    private final IPacienteRepository pacienteRepository;

    // OCP (Princípio do Aberto/Fechado) usando o Padrão Strategy:
    // Deixamos a estratégia "solta" para ser injetada em tempo de execução.
    private IProtocoloAtendimento protocolo;

    // Construtor: Exige que entreguem as ferramentas prontas para ele.
    public TriagemService(INotification notification, IPacienteRepository pacienteRepository) {
        this.notification = notification;
        this.pacienteRepository = pacienteRepository;
    }

    // Aberto para expansão: Podemos injetar qualquer protocolo novo aqui
    // sem nunca precisar alterar as regras desta classe.
    public void configurarProtocolo(IProtocoloAtendimento novoProtocolo) {
        this.protocolo = novoProtocolo;
    }

    public void realizarAtendimento(Paciente paciente) {
        if (protocolo == null) {
            System.out.println("Erro! Nenhum protocolo foi configurado!");
            return;
        }

        // Delegação de tarefas (Mantendo o SRP):
        // 1. Pede para o banco salvar
        pacienteRepository.salvar(paciente);

        // 2. Pede para a estratégia priorizar
        String resultado = protocolo.priorizar(paciente);

        // 3. Pede para o painel notificar
        notification.notificar(resultado);
    }
}