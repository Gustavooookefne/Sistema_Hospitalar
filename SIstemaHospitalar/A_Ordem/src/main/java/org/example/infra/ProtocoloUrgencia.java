package org.example.infra;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

public class ProtocoloUrgencia implements IProtocoloAtendimento {

    @Override
    public String priorizar(Paciente paciente) {

        return "CÓDIGO AMARELO! BPM: " + paciente.frequenciaCardica +" "+ "Atendimento em até 1 hora para: " + paciente.nome;
    }
}
