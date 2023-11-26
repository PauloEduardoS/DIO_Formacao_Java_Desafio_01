package contaBanco;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class ContaTerminal {

  private Scanner scanner = new Scanner(System.in);
  private PrintStream output = new PrintStream(System.out, true);

  private int numero;
  private String agencia, clienteNome;
  private double saldo = 0;

  //Gera um número aleatório para conta
  private int generateConta() {
    Random random = new Random();

    StringBuilder conta = new StringBuilder("");

    for (int i = 0; i < 5; i++) {
      int digito = random.nextInt(10);
      conta.append(digito);
    }

    return Integer.parseInt(conta.toString());
  }

  //Garante a precisão decimal desejada.
  private String precisaoDecimal(int casas, double value) {
    return String.format("%." + casas + "f", value);
  }

  //Construtor
  public ContaTerminal() throws Exception {
    try {
      output.println("Por favor, digite o número da Agência !");
      agencia = scanner.nextLine();

      //Garante que não hajam letras na string
      if (agencia.matches(".*[a-zA-Z].*")) {
        throw new IllegalArgumentException("Valor inválido informado!");
      }

      numero = generateConta();
      output.println("O número de sua conta: " + numero + ".");

      output.println("Por favor, digite o nome do cliente !");
      clienteNome = scanner.nextLine();

      //Garante que não hajam números na string
      if (clienteNome.matches(".*\\d.*")) {
        throw new IllegalArgumentException("Valor inválido informado!");
      }

      output.println(
        "Saldo inicial R$ " +
        precisaoDecimal(2, saldo) +
        ". Insira um valor de depósito !"
      );
      Double valorDeDeposito = Double.parseDouble(
        scanner.nextLine().replace(',', '.')
      );
      if (valorDeDeposito > 0) {
        saldo = valorDeDeposito;
      } else {
        throw new IllegalArgumentException("Valor de depósito inválido!");
      }

      output.println(
        "Olá " +
        clienteNome +
        ", obrigado por criar uma conta em nosso banco, sua agência é " +
        agencia +
        ", conta " +
        numero +
        " e seu saldo " +
        precisaoDecimal(2, saldo) +
        " já está disponível para saque"
      );
    } catch (Exception e) {
      output.println("Exceção inesperada: " + e.getMessage());
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }
}
