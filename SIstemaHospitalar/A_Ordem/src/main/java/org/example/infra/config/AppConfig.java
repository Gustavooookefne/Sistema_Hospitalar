package org.example.infra.config;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.infra.PainelNotification;
import org.example.infra.RepositoryPacienteMemoria;
import org.example.service.TriagemService;

@Schema(
        name = "AppConfig",
        description = "Classe de configuração responsável pela Injeção de Dependências e orquestração dos componentes de Infraestrutura e Serviço."
)
public class AppConfig {

    // Detalhe: No Swagger, podemos descrever as implementações concretas que o sistema usa por padrão.
    @Schema(description = "Implementação concreta de notificação via Painel Console.")
    private final INotification notification = new PainelNotification();

    @Schema(description = "Implementação de repositório utilizando armazenamento em memória volátil.")
    private final IPacienteRepository pacienteRepository = new RepositoryPacienteMemoria();

    /**
     * Factory method para o serviço de triagem.
     * @return Uma instância configurada de TriagemService com suas dependências injetadas.
     */
    @Schema(description = "Retorna o serviço de triagem pronto para uso, seguindo o princípio da Inversão de Dependência (DIP).")
    public TriagemService triagemService() {
        return new TriagemService(notification, pacienteRepository);
    }
}