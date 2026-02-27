package org.example;

import org.example.domain.Paciente;
import org.example.infra.config.AppConfig;
import org.example.service.TriagemService;
import org.example.service.strategy.ProtocoloEmergencia;
import org.example.service.strategy.ProtocoloUrgencia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        // Montagem manual das dependencias.
        TriagemService hospital = new AppConfig().triagemService();

        boolean rodando = true;
        System.out.println("\n=== SISTEMA HOSPITALAR ===\n");

        while (rodando) {
            System.out.println("Escolha o tipo de atendimento:");
            System.out.println("1 - Emergencia (Risco de vida)");
            System.out.println("2 - Urgencia (Moderado)");
            System.out.println("3 - Sair");
            System.out.print("Opcao: ");
            String opcao = lerLinhaSegura(leitor);
            if (opcao == null) {
                System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
                break;
            }
            opcao = opcao.trim();

            switch (opcao) {
                case "1":
                    Paciente pacienteEmergencia = lerPaciente(leitor);
                    if (pacienteEmergencia == null) {
                        rodando = false;
                        break;
                    }
                    // Strategy em tempo de execucao: protocolo escolhido pelo usuario.
                    hospital.configurarProtocolo(new ProtocoloEmergencia());
                    hospital.realizarAtendimento(pacienteEmergencia);
                    System.out.println("Atendimento finalizado com sucesso!");
                    break;
                case "2":
                    Paciente pacienteUrgencia = lerPaciente(leitor);
                    if (pacienteUrgencia == null) {
                        rodando = false;
                        break;
                    }
                    // Troca de estrategia sem alterar o service (OCP).
                    hospital.configurarProtocolo(new ProtocoloUrgencia());
                    hospital.realizarAtendimento(pacienteUrgencia);
                    System.out.println("Atendimento finalizado com sucesso!");
                    break;
                case "3":
                    System.out.println("\nEncerrando o atendimento...");
                    rodando = false;
                    break;
                default:
                    System.out.println("\nOpcao invalida! Voltando ao inicio...");
                    break;
            }
        }

        leitor.close();
    }

    private static Paciente lerPaciente(Scanner leitor) {
        System.out.print("Nome do paciente: ");
        String nome = lerLinhaSegura(leitor);
        if (nome == null) {
            System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
            return null;
        }

        Integer bpm = lerBpm(leitor);
        if (bpm == null) {
            System.out.println("\nEntrada finalizada. Encerrando o atendimento...");
            return null;
        }

        return new Paciente(nome, bpm);
    }

    private static Integer lerBpm(Scanner leitor) {
        while (true) {
            System.out.print("Frequencia cardiaca: ");
            String linha = lerLinhaSegura(leitor);
            if (linha == null) {
                return null;
            }

            try {
                int bpm = Integer.parseInt(linha.trim());
                if (bpm <= 0) {
                    System.out.println("Valor invalido. Informe um numero maior que zero.");
                    continue;
                }
                return bpm;
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite apenas numeros.");
            }
        }
    }

    private static String lerLinhaSegura(Scanner leitor) {
        if (!leitor.hasNextLine()) {
            return null;
        }
        return leitor.nextLine();
    }
}
