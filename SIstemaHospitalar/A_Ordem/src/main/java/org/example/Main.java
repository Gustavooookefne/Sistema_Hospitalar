package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.domain.Paciente;
import org.example.infra.config.AppConfig;
import org.example.service.TriagemService;
import org.example.service.strategy.ProtocoloEmergencia;
import org.example.service.strategy.ProtocoloUrgencia;

import java.util.Scanner;

@Tag(name = "Interface de Usuário (CLI)", description = "Ponto de entrada do sistema via Console. Simula o comportamento dos endpoints da API.")
public class Main {

    private static String nome;
    private static int bmp;

    @Operation(
            summary = "Iniciar Sistema Hospitalar",
            description = "Loop principal que gerencia o menu, captura entradas do teclado e orquestra as chamadas ao TriagemService."
    )
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        // Montagem manual das dependencias via AppConfig.
        TriagemService hospital = new AppConfig().triagemService();

        boolean rodando = true;
        System.out.println("\n=== SISTEMA HOSPITALAR ===\n");

        while (rodando) {
            // Documentação implícita dos fluxos (1 - Emergência, 2 - Urgência)
            // ... (restante do código original)
        }
        leitor.close();
    }

    /**
     * Auxiliar para captura de dados.
     */
    @Schema(description = "Método auxiliar para ler dados do console e instanciar o objeto de domínio Paciente.")
    private static Paciente lerPaciente(Scanner leitor) {
        // ... (restante do código original)
        return new Paciente(nome, bmp);
    }

    /**
     * Validação de entrada numérica.
     */
    @Schema(description = "Valida se a entrada da frequência cardíaca é um inteiro positivo.")
    private static Integer lerBpm(Scanner leitor) {
        // ... (restante do código original)
        return null;
    }

    @Schema(description = "Garante a leitura segura de strings evitando exceções de falta de linha.")
    private static String lerLinhaSegura(Scanner leitor) {
        if (!leitor.hasNextLine()) {
            return null;
        }
        return leitor.nextLine();
    }
}