package org.example.service.strategy;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

@Schema(
        name = "ProtocoloEmergencia",
        description = "Implementação da estratégia de atendimento para casos de Emergência (Risco de Vida)."
)
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