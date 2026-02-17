package org.example.br.com.sistema.service;

import org.example.br.com.sistema.domain.Paciente;
import org.example.br.com.sistema.infra.MySQLRepository;

public class AtendimentoService {


    private MySQLRepository repository = new MySQLRepository();

    public void iniciar(String tipoAtendimento, Paciente paciente) {


        if (tipoAtendimento.equals("EMERGENCIA")) {
            System.out.println("Prioridade máxima para: " + paciente.getNomeCompleto());
        } else if (tipoAtendimento.equals("EXAME")) {
            System.out.println("Encaminhando para o laboratório...");
        } else {
            System.out.println("Atendimento comum.");
        }

        repository.salvar(paciente);

        System.out.println("Atendimento finalizado com sucesso.");
    }
}