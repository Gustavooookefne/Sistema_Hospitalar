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

        System.out.println("=== SISTEMA HOSPITLAR===\n");

        System.out.print("Nome do Paciente: ");
        String nome = leitor.nextLine();
        Paciente paciente = new Paciente(nome, 80);

        System.out.println("\nEscolha o tipo de Atendimento: ");
        System.out.println("1 - Emergência (Risco de Vida)");
        System.out.println("2 - Urgência (Moderado) ");

        int escolha = leitor.nextInt();

        if (escolha == 1) {
            hospital.configurarPainel(new ProtocoloEmergencia());
        } else {
            hospital.configurarPainel(new ProtocoloUrgencia());
        }
        hospital.realizarAtendimento(paciente);

        System.out.println("\n Atendimento finalizado com sucesso!");
        leitor.close();
    }
}