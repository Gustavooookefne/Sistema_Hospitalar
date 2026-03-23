package org.example.service.strategy;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

<<<<<<< HEAD
@Schema(
        name = "ProtocoloEmergencia",
        description = "Implementação da estratégia de atendimento para casos de Emergência (Risco de Vida)."
)
=======
/**
 * SRP (Princípio da Responsabilidade Única):
 * A única razão para esta classe mudar é se a regra médica de EMERGÊNCIA mudar.
 * * LSP (Princípio da Substituição de Liskov):
 * Como esta classe assina o contrato IProtocoloAtendimento, ela pode ser
 * injetada lá no TriagemService substituindo qualquer outro protocolo
 * (como o de Urgência) e o sistema vai continuar funcionando perfeitamente!
 */
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
public class ProtocoloEmergencia implements IProtocoloAtendimento {

    /**
     * Aplica a regra de prioridade máxima.
     * @return Mensagem formatada com 'CÓDIGO VERMELHO'.
     */
    @Override
    @Operation(
            summary = "Executar priorização de emergência",
            description = "Gera uma classificação de 'Código Vermelho' baseada na urgência vital do paciente."
    )
    public String priorizar(
            @Schema(description = "Paciente em estado crítico para triagem imediata")
            Paciente paciente
    ) {
        return "CODIGO VERMELHO! BPM: " + paciente.getFrequenciaCardica()
                + " Atendimento imediato para: " + paciente.getNome();
    }
}