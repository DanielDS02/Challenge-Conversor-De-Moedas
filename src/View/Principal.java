package View;
import Service.ConversorService;
import Model.Moeda;

import java.util.Scanner;

public class Principal{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorService service = new ConversorService();

        System.out.println("**************** Bem-vindo ao Conversor de Moedas! ****************\n");
        System.out.println("********************* Escolha uma conversão: **********************\n");
        System.out.println("1. USD para BRL");
        System.out.println("2. EUR para USD");
        System.out.println("3. GBP para BRL");
        System.out.println("4. BRL para USD");
        System.out.println("5. AUD para BRL");
        System.out.println("6. CAD para USD");

        int opcao = scanner.nextInt();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        String moedaOrigem = "";
        String moedaDestino = "";

        switch (opcao) {
            case 1 -> {
                moedaOrigem = "USD";
                moedaDestino = "BRL";
            }
            case 2 -> {
                moedaOrigem = "EUR";
                moedaDestino = "USD";
            }
            case 3 -> {
                moedaOrigem = "GBP";
                moedaDestino = "BRL";
            }
            case 4 -> {
                moedaOrigem = "BRL";
                moedaDestino = "USD";
            }
            case 5 -> {
                moedaOrigem = "AUD";
                moedaDestino = "BRL";
            }
            case 6 -> {
                moedaOrigem = "CAD";
                moedaDestino = "USD";
            }
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        try {
            Moeda moeda = new Moeda(moedaOrigem, valor);
            double resultado = service.converter(moeda, moedaDestino);
            System.out.printf("Resultado: %.2f %s%n", resultado, moedaDestino);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
