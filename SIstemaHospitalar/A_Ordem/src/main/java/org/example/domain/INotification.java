package org.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

@Schema(
        name = "INotification",
        description = "Contrato para serviços de notificação do sistema (Painel, SMS, E-mail, etc)"
)
public interface INotification {

    /**
     * Envia uma mensagem de notificação para o destino configurado.
     * * @param mensagem Texto descritivo da notificação (ex: Código de prioridade)
     */
    void notificar(
            @Schema(description = "Conteúdo da mensagem a ser notificada", example = "CODIGO VERMELHO! Atendimento imediato.")
            String mensagem
    );
}