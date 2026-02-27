package org.example.service.strategy;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

// Strategy concreta para casos de emergencia.
public class ProtocoloEmergencia implements IProtocoloAtendimento {

    @Override
    public String priorizar(Paciente paciente) {
        return "CODIGO VERMELHO! BPM: " + paciente.getFrequenciaCardica()
                + " Atendimento imediato para: " + paciente.getNome();
    }
}
