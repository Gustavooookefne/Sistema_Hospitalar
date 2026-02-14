package org.example;

import org.example.domain.Paciente;
import org.example.infra.ProtocoloUrgencia;
import org.example.service.TriagemService;

import java.util.Scanner;

import org.example.infra.ProtocoloEmergencia;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        TriagemService hospital = new TriagemService();

        boolean rodando = true;

        System.out.println("\n=== SISTEMA HOSPITLAR===\n");

        while (rodando) {
            System.out.print("Nome do Paciente: ");
            String nome = leitor.nextLine();

            System.out.print("Frequência Cardíaca: ");
            int bpm = leitor.nextInt();

            leitor.nextLine();

            Paciente paciente = new Paciente(nome, bpm);
            System.out.println("\nEscolha o tipo de Atendimento: ");
            System.out.println("1 - Emergência (Risco de Vida)");
            System.out.println("2 - Urgência (Moderado) ");
            System.out.println("3 - Sair ");

            String opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    hospital.configurarProtocolo(new ProtocoloEmergencia());
                    System.out.println("Atendimento finalizado com sucesso!");
                case "2":
                    hospital.configurarProtocolo(new ProtocoloUrgencia());
                    System.out.println("Atendimento finalizado com sucesso!");
                case "3":
                    System.out.println("\nEncerrando o atendimento...");
                    rodando = false;
                    break;
                default:
                System.out.println("\n Opção inválida! Voltando ao início...");
                break;
            }
        }
        leitor.close();
    }
}
