package org.example.infra;

import org.example.domain.INotification;

public class PainelNotification implements INotification {

    @Override
    public void notificar(String mensagem) {
        // Implementacao concreta de notificacao (detalhe de infraestrutura).
        System.out.println("\n------------------------------------");
        System.out.println("\nNOTIFICACAO: " + mensagem);
        System.out.println("\n------------------------------------");
    }
}
