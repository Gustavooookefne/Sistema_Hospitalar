package org.example.infra;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

public class ProtocoloEmergencia implements IProtocoloAtendimento {

    @Override
    public String priorizar(Paciente paciente) {

        return "VERMELHO! Atendimento imediato para: " + paciente.nome;
    }

}
