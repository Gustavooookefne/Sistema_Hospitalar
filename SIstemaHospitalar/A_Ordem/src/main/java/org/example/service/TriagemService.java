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

<<<<<<< HEAD
@Tag(name = "Serviço de Triagem", description = "Orquestrador do fluxo de atendimento e persistência")
@Schema(description = "Classe responsável por coordenar a triagem, aplicando protocolos e disparando notificações.")
public class TriagemService {

    private final INotification notification;
    private final IPacienteRepository pacienteRepository;

    @Schema(description = "Estratégia de atendimento (Strategy) definida para o atendimento atual.")
=======
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
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    private IProtocoloAtendimento protocolo;

    // Construtor: Exige que entreguem as ferramentas prontas para ele.
    public TriagemService(INotification notification, IPacienteRepository pacienteRepository) {
        this.notification = notification;
        this.pacienteRepository = pacienteRepository;
    }

<<<<<<< HEAD
    /**
     * Define qual estratégia de priorização será usada.
     */
    @Operation(summary = "Configurar protocolo", description = "Define a Strategy de atendimento (Emergência ou Urgência) antes de realizar o atendimento.")
    public void configurarProtocolo(
            @Parameter(description = "Instância da classe de protocolo escolhida")
            IProtocoloAtendimento novoProtocolo
    ) {
=======
    // Aberto para expansão: Podemos injetar qualquer protocolo novo aqui
    // sem nunca precisar alterar as regras desta classe.
    public void configurarProtocolo(IProtocoloAtendimento novoProtocolo) {
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
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

<<<<<<< HEAD
=======
        // Delegação de tarefas (Mantendo o SRP):
        // 1. Pede para o banco salvar
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
        pacienteRepository.salvar(paciente);

        // 2. Pede para a estratégia priorizar
        String resultado = protocolo.priorizar(paciente);

        // 3. Pede para o painel notificar
        notification.notificar(resultado);
    }
}