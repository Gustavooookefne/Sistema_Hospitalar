package org.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Schema(
        name = "IProtocoloAtendimento",
        description = "Interface que define a estratégia de priorização (Triagem) do paciente."
)
public interface IProtocoloAtendimento {

    /**
     * Aplica a regra de priorização baseada nos dados do paciente.
     * * @param paciente Objeto contendo nome e sinais vitais (BPM).
     * @return Uma String contendo a classificação de risco (Ex: Código Vermelho).
     */
    @Operation(
            summary = "Priorizar atendimento",
            description = "Analisa a frequência cardíaca do paciente e retorna a classificação de urgência."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Priorização calculada com sucesso",
            content = @Content(schema = @Schema(type = "string", example = "CODIGO VERMELHO! BPM: 150 Atendimento imediato para: João"))
    )
    String priorizar(
            @Schema(description = "Dados do paciente para análise do protocolo")
            Paciente paciente
    );

}