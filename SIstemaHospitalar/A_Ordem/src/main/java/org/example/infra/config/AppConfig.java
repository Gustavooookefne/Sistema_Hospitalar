package org.example.infra.config;

import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.infra.PainelNotification;
import org.example.infra.RepositoryPacienteMemoria;
import org.example.service.TriagemService;

/**
 * DIP (Princípio da Inversão de Dependência) na prática:
 * Para o TriagemService não ter que dar "new" nas classes concretas e ficar
 * acoplado a elas, nós isolamos a criação dessas peças nesta classe de Configuração.
 */
public class AppConfig {

    // Aqui nós definimos QUAIS implementações reais vão ser usadas no sistema hoje.
    // Se amanhã mudarmos de "Memoria" para um "RepositoryPacienteMySQL",
    // a gente só altera esta linha. O resto do sistema nem fica sabendo!
    private final INotification notification = new PainelNotification();
    private final IPacienteRepository pacienteRepository = new RepositoryPacienteMemoria();

    // Método "Fábrica": Monta o hospital e devolve pronto.
    public TriagemService triagemService() {
        return new TriagemService(notification, pacienteRepository);
    }
}