package org.example.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.domain.INotification;

@Schema(
        name = "PainelNotification",
        description = "Implementação concreta de notificação que exibe mensagens diretamente no console do sistema."
)
public class PainelNotification implements INotification {

    /**
     * Exibe a mensagem de notificação formatada no console.
     * * @param mensagem Texto a ser impresso entre molduras decorativas.
     */
    @Override
    @Schema(description = "Formata e imprime a mensagem no System.out")
    public void notificar(String mensagem) {
        // Implementacao concreta de notificacao (detalhe de infraestrutura).
        System.out.println("\n------------------------------------");
        System.out.println("\nNOTIFICACAO: " + mensagem);
        System.out.println("\n------------------------------------");
    }
}