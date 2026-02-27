package org.example.service.strategy;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

// Strategy concreta para casos de urgencia.
public class ProtocoloUrgencia implements IProtocoloAtendimento {

    @Override
    public String priorizar(Paciente paciente) {
        return "CODIGO AMARELO! BPM: " + paciente.getFrequenciaCardica()
                + " Atendimento em ate 1 hora para: " + paciente.getNome();
    }
}
