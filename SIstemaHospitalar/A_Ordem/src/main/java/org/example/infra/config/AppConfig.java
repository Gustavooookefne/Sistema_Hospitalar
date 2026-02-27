package org.example.infra.config;

import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.infra.PainelNotification;
import org.example.infra.RepositoryPacienteMemoria;
import org.example.service.TriagemService;

public class AppConfig {

    private final INotification notification = new PainelNotification();
    private final IPacienteRepository pacienteRepository = new RepositoryPacienteMemoria();

    public TriagemService triagemService() {
        return new TriagemService(notification, pacienteRepository);
    }
}
