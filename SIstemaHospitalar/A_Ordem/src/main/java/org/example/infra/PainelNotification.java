package org.example.infra;

import org.example.domain.INotification;

/**
 * Camada de Infraestrutura (Infra):
 * Esta camada lida com os "detalhes técnicos" (I/O - Entrada e Saída de dados).
 * O nosso Domínio não faz ideia de que a mensagem vai aparecer num console preta e branca.
 */
public class PainelNotification implements INotification {

    @Override
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