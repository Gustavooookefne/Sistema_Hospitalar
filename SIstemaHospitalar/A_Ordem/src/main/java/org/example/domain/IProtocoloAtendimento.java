package org.example.domain;

/**
 * ISP (Princípio da Segregação de Interfaces):
 * Um contrato minúsculo, limpo e direto ao ponto.
 * Qualquer classe que for um "Protocolo" só precisa se preocupar em saber priorizar.
 * Ela não é obrigada a saber salvar no banco ou mandar e-mail.
 */
public interface IProtocoloAtendimento {

    // Contrato principal do Padrão Strategy.
    // Define a regra estrita de que todo protocolo DEVE retornar um texto com o resultado da priorização.
    String priorizar(Paciente paciente);

}