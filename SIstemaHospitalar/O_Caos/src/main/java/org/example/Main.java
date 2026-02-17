package org.example;

import org.example.br.com.sistema.domain.Paciente;
import org.example.br.com.sistema.service.AtendimentoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        Paciente p = new Paciente();
        AtendimentoService service = new AtendimentoService();

        System.out.println("--- SISTEMA HOSPITALAR (VERSÃO CAOS) ---");

        System.out.print("Digite o nome completo do paciente: ");
        p.setNomeCompleto(leitor.nextLine());

        System.out.print("Digite o tipo de atendimento (EMERGENCIA ou EXAME): ");
        String tipo = leitor.nextLine().toUpperCase();

        System.out.println("\nProcessando...");

        service.iniciar(tipo, p);

        leitor.close();
    }
}