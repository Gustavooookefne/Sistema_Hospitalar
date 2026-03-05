package org.example.service.strategy;

import org.example.domain.IProtocoloAtendimento;
import org.example.domain.Paciente;

/**
 * SRP (Princípio da Responsabilidade Única):
 * A única razão para esta classe mudar é se a regra médica de EMERGÊNCIA mudar.
 * * LSP (Princípio da Substituição de Liskov):
 * Como esta classe assina o contrato IProtocoloAtendimento, ela pode ser
 * injetada lá no TriagemService substituindo qualquer outro protocolo
 * (como o de Urgência) e o sistema vai continuar funcionando perfeitamente!
 */
public class ProtocoloEmergencia implements IProtocoloAtendimento {

    @Override
    public String priorizar(Paciente paciente) {
        return "CODIGO VERMELHO! BPM: " + paciente.getFrequenciaCardica()
                + " Atendimento imediato para: " + paciente.getNome();
    }
}