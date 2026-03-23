package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Scanner;

import java.util.Scanner;


@Schema(description = "Entidade simplificada com acesso direto a atributos (Viola Encapsulamento)")
class Paciente {
    @Schema(description = "Nome do paciente", example = "João do Caos")
    public String nomeCompleto;
}


@Schema(description = "Interface genérica que força métodos desnecessários em subclasses (Interface Poluída)")
interface IAtendimentoStrategy {

    @Operation(summary = "Verificar Urgência", description = "Método que pode lançar exceção se a implementação não for de Emergência.")
    void verificarUrgencia();

    @Operation(summary = "Solicitar Exame", description = "Método que pode lançar exceção se a implementação não for de Exame.")
    void solicitarExameLaboratorial();

    void executar();
}

// --- INFRAESTRUTURA (ACOPLADA) ---
class MySQLRepository {
    public void salvarNoBanco(Paciente p) {
        System.out.println("Salvando " + p.nomeCompleto + " no MySQL via JDBC...");
    }
}

@Tag(name = "Serviço com Alto Acoplamento", description = "Endpoint que gerencia múltiplos fluxos via IF/ELSE e depende diretamente do MySQL.")
class AtendimentoService {

    // Documentação técnica: O repositório está "hardcoded"
    @Schema(description = "Depedência rígida do MySQL (Violação de DIP)")
    private MySQLRepository repository = new MySQLRepository();

    @Operation(
            summary = "Iniciar Atendimento (Modo Rígido)",
            description = "Executa a lógica baseada em Strings. Requer alteração manual no código para novos tipos (Violação de OCP)."
    )
    @ApiResponse(responseCode = "500", description = "Erro interno caso o tipo de atendimento não suporte o método chamado (Violação de LSP)")
    public void iniciar(
            @Parameter(description = "Tipo: EMERGENCIA ou EXAME", example = "EMERGENCIA") String tipoAtendimento,
            Paciente paciente
    ) {
        // Lógica interna com If/Else...
    }
}

@Schema(description = "Implementação que quebra ao tentar executar ações de laboratório.")
class EmergenciaStrategy implements IAtendimentoStrategy {

    @Operation(summary = "Execução de Emergência")
    public void executar() { System.out.println("Atendendo!"); }

    /**
     * @throws UnsupportedOperationException Sempre que chamado.
     */
    @Operation(
            summary = "MÉTODO PERIGOSO",
            description = "NÃO CHAMAR: Lança UnsupportedOperationException pois Emergência não faz exames."
    )
    public void solicitarExameLaboratorial() {
        throw new UnsupportedOperationException("Emergência não pede exame laboratorial aqui.");
    }

    public void verificarUrgencia() { System.out.println("Sinais OK."); }
}

@Tag(name = "Entrada Principal (Legado)", description = "Ponto de entrada que utiliza lógica procedural e alto acoplamento.")
public class Main {

    @Operation(
            summary = "Executar Fluxo de Atendimento Caótico",
            description = "Inicia o sistema via console, capturando nome e tipo de atendimento. " +
                    "Nota: Este fluxo depende de strings mágicas (EMERGENCIA/EXAME) e instanciamento direto de serviços."
    )
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        Paciente p = new Paciente();

        AtendimentoService service = new AtendimentoService();

        System.out.println("### SISTEMA HOSPITALAR: VERSÃO CAOS ###");
        System.out.print("Nome do Paciente: ");
        p.nomeCompleto = leitor.nextLine();

        System.out.print("Tipo (EMERGENCIA/EXAME): ");
        String tipo = leitor.nextLine().toUpperCase();

        service.iniciar(tipo, p);
        leitor.close();
    }
}