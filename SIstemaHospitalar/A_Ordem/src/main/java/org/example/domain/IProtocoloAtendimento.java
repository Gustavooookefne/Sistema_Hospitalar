package org.example.domain;

public interface IProtocoloAtendimento {

    // Contrato da Strategy de priorizacao de atendimento.
    String priorizar(Paciente paciente);

}

