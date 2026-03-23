package org.example.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.domain.INotification;

<<<<<<< HEAD
@Schema(
        name = "PainelNotification",
        description = "Implementação concreta de notificação que exibe mensagens diretamente no console do sistema."
)
=======
/**
 * Camada de Infraestrutura (Infra):
 * Esta camada lida com os "detalhes técnicos" (I/O - Entrada e Saída de dados).
 * O nosso Domínio não faz ideia de que a mensagem vai aparecer num console preta e branca.
 */
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
public class PainelNotification implements INotification {

    /**
     * Exibe a mensagem de notificação formatada no console.
     * * @param mensagem Texto a ser impresso entre molduras decorativas.
     */
    @Override
    @Schema(description = "Formata e imprime a mensagem no System.out")
    public void notificar(String mensagem) {
        // Implementação concreta de notificação (detalhe de infraestrutura).
        // SRP (Responsabilidade Única): A única função desta classe é formatar
        // e exibir a mensagem visualmente para o usuário.
        // Se amanhã quisermos que o painel pisque ou faça barulho, alteramos apenas aqui.
        System.out.println("\n------------------------------------");
        System.out.println("\nNOTIFICACAO: " + mensagem);
        System.out.println("\n------------------------------------");
    }
}