package org.example.service.strategy;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

@Schema(
        name = "ProtocoloUrgencia",
        description = "Implementação da estratégia de atendimento para casos de Urgência (Risco Moderado)."
)
public class ProtocoloUrgencia implements IProtocoloAtendimento {

    /**
     * Aplica a regra de prioridade moderada.
     * @return Mensagem formatada com 'CÓDIGO AMARELO' e previsão de 1 hora.
     */
    @Override
    @Operation(
            summary = "Executar priorização de urgência",
            description = "Gera uma classificação de 'Código Amarelo' para pacientes que necessitam de atendimento, mas não correm risco imediato de vida."
    )
    public String priorizar(
            @Schema(description = "Paciente com quadro clínico estável para triagem de urgência")
            Paciente paciente
    ) {
        return "CODIGO AMARELO! BPM: " + paciente.getFrequenciaCardica()
                + " Atendimento em ate 1 hora para: " + paciente.getNome();
    }
}