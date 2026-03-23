package org.example.infra.config;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.domain.INotification;
import org.example.domain.IPacienteRepository;
import org.example.infra.PainelNotification;
import org.example.infra.RepositoryPacienteMemoria;
import org.example.service.TriagemService;

<<<<<<< HEAD
@Schema(
        name = "AppConfig",
        description = "Classe de configuração responsável pela Injeção de Dependências e orquestração dos componentes de Infraestrutura e Serviço."
)
public class AppConfig {

    // Detalhe: No Swagger, podemos descrever as implementações concretas que o sistema usa por padrão.
    @Schema(description = "Implementação concreta de notificação via Painel Console.")
=======
/**
 * DIP (Princípio da Inversão de Dependência) na prática:
 * Para o TriagemService não ter que dar "new" nas classes concretas e ficar
 * acoplado a elas, nós isolamos a criação dessas peças nesta classe de Configuração.
 */
public class AppConfig {

    // Aqui nós definimos QUAIS implementações reais vão ser usadas no sistema hoje.
    // Se amanhã mudarmos de "Memoria" para um "RepositoryPacienteMySQL",
    // a gente só altera esta linha. O resto do sistema nem fica sabendo!
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    private final INotification notification = new PainelNotification();

    @Schema(description = "Implementação de repositório utilizando armazenamento em memória volátil.")
    private final IPacienteRepository pacienteRepository = new RepositoryPacienteMemoria();

<<<<<<< HEAD
    /**
     * Factory method para o serviço de triagem.
     * @return Uma instância configurada de TriagemService com suas dependências injetadas.
     */
    @Schema(description = "Retorna o serviço de triagem pronto para uso, seguindo o princípio da Inversão de Dependência (DIP).")
=======
    // Método "Fábrica": Monta o hospital e devolve pronto.
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    public TriagemService triagemService() {
        return new TriagemService(notification, pacienteRepository);
    }
}