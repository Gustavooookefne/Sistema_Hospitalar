package org.example.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

@Tag(name = "Serviço de Triagem", description = "Orquestrador do fluxo de atendimento e persistência")
@Schema(description = "Classe responsável por coordenar a triagem, aplicando protocolos e disparando notificações.")
public class TriagemService {

    private final INotification notification;
    private final IPacienteRepository pacienteRepository;

    @Schema(description = "Estratégia de atendimento (Strategy) definida para o atendimento atual.")
    private IProtocoloAtendimento protocolo;

    public TriagemService(INotification notification, IPacienteRepository pacienteRepository) {
        this.notification = notification;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Define qual estratégia de priorização será usada.
     */
    @Operation(summary = "Configurar protocolo", description = "Define a Strategy de atendimento (Emergência ou Urgência) antes de realizar o atendimento.")
    public void configurarProtocolo(
            @Parameter(description = "Instância da classe de protocolo escolhida")
            IProtocoloAtendimento novoProtocolo
    ) {
        this.protocolo = novoProtocolo;
    }

    /**
     * Fluxo principal de atendimento.
     */
    @Operation(
            summary = "Realizar atendimento completo",
            description = "Fluxo: 1. Valida protocolo | 2. Salva no repositório | 3. Aplica regra de prioridade | 4. Notifica o painel."
    )
    @ApiResponse(responseCode = "200", description = "Atendimento processado, salvo e notificado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Falha: Nenhum protocolo foi configurado previamente.")
    public void realizarAtendimento(
            @Schema(description = "Dados do paciente que está passando pela triagem")
            Paciente paciente
    ) {
        if (protocolo == null) {
            System.out.println("Erro! Nenhum protocolo foi configurado!");
            return;
        }

        pacienteRepository.salvar(paciente);
        String resultado = protocolo.priorizar(paciente);
        notification.notificar(resultado);
    }
}