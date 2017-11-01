package new_bank_poo_pos_graduação;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitarios.ConectaBanco;

public class Account {

    public double saldo;
    public int numeroConta;
    public Date dataAbertura;
    public double transacaoAnterior;
    public int senha;
    public String tipoConta;
    public String NomeCliente;
    public String clienteId;
    public Date dataDeposito;
    public Date dataSaque;
    public Date dataTransação;
    public String tipoTransacao;

    ConectaBanco con = new ConectaBanco();

    Scanner scanner = new Scanner(System.in);

    // zerar o balanço
    public Account() {

        saldo = 0.0;
    }

    public void criarConta(float saldo, int numeroConta, String senha, String tipoConta, String nomeCliente) {

        try {

            String tipoTransacao = "Criação de conta bancaria, e deposito inicial";

            java.util.Date date = new java.util.Date();
            java.sql.Date dataTransacao = new java.sql.Date(date.getTime());
            java.sql.Date dataDeposito = new java.sql.Date(date.getTime());

            boolean resposta = con.criaNovaContaBancaria(saldo, numeroConta, senha, tipoConta, nomeCliente, tipoTransacao, dataDeposito, dataTransacao);

            if (resposta == true) {

                System.out.println("Conta criada com sucesso!");
                System.out.println("Obrigado!");
            } else {
                System.out.println("Nao inseriu os dados no banco corretamente!");
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }

    // Deposito
    public void deposito(float valDeposito, int numberoConta, String senha) {

        if (valDeposito != 0) {

            try {

                String tipoTransacao = "Deposito";

                java.util.Date date = new java.util.Date();

                java.sql.Date dataTransacao = new java.sql.Date(date.getTime());

                java.sql.Date dataDeposito = new java.sql.Date(date.getTime());

                Account conta = con.depositar(numberoConta, senha);

            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void confirmarDeposito(boolean confirmacao, float valorDeposito, float novoSaldo) {

        Scanner scanner = new Scanner(System.in);

        if (confirmacao == true) {

            DecimalFormat df = new DecimalFormat("####0.00");

            System.out.println("\n");
            System.out.println("***** Obrigado pelo seu deposito no valor: " + valorDeposito);
            System.out.println("***** Novo saldo disponivel $$R  " + df.format(novoSaldo));
            System.out.println("\n");
            System.out.println("Precione enter para continuar");
            scanner.nextLine();

        } else {

            System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
            System.out.println("\n");

        }

        Account cliente1 = new Account();
        cliente1.mostrarMenu();
    }

    public void depositoRegraDeNegocio(boolean confirmacao, float valorDeposito, float saldoAtual, int numConta, int numContaNoBD, String nomeCli) throws SQLException {

        ConectaBanco con = new ConectaBanco();

        float novoSaldo = saldoAtual + valorDeposito;

        String tipoTransacao = "Deposito";

        java.util.Date date = new java.util.Date();

        java.sql.Date dataTransacao = new java.sql.Date(date.getTime());

        java.sql.Date dataDeposito = new java.sql.Date(date.getTime());

        boolean saldoAtualizado = con.updateSaldo(novoSaldo, numConta, tipoTransacao, dataDeposito, dataTransacao, valorDeposito);

        if (saldoAtualizado == true) {

            if (nomeCli != null && numContaNoBD == numConta) {

                confirmarDeposito(true, valorDeposito, novoSaldo);
            } else {
                confirmarDeposito(false, valorDeposito, novoSaldo);
            }

        }

        if (numConta != numContaNoBD) {

            System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
            System.out.println("\n");
        }

        Account cliente1 = new Account();
        cliente1.mostrarMenu();
    }

    public void confirmarSaque(boolean confirmacao, float valorSaque, float novoSaldo) {

        Scanner scanner = new Scanner(System.in);

        if (confirmacao == true) {

            DecimalFormat df = new DecimalFormat("####0.00");

            System.out.println("\n");
            System.out.println("***** Obrigado pelo seu saque no valor: " + valorSaque);
            System.out.println("***** Novo saldo disponivel $$$R  " + df.format(novoSaldo));
            System.out.println("\n");
            System.out.println("Precione enter para continuar");
            scanner.nextLine();
        } else {

            System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
            System.out.println("\n");
        }
        Account cliente1 = new Account();
        cliente1.mostrarMenu();
    }

    public void sacar(float valSaque, int numeroConta, String senha) {

        if (valSaque != 0) {

            try {

                Account conta = con.buscarSaldo(numeroConta, senha);

                float novoSaldo = (float) conta.saldo - valSaque;

                //(float deposito, int numeroConta, String tipoTransacao, Date dataDeposito, Date dataTransacao) 
                java.util.Date date = new java.util.Date();

                java.sql.Date dataTransacao = new java.sql.Date(date.getTime());

                boolean resultado = con.updateSaldo(novoSaldo, numeroConta, "saque", null, dataTransacao, valSaque);

                if (resultado == true) {

                    confirmarSaque(true, valSaque, novoSaldo);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getHistorico(int numConta, String password) {

        try {

            ArrayList<Account> accountList = con.buscarHistorico(numConta, password);

            DecimalFormat df = new DecimalFormat("####0.00");

            for (Account item : accountList) {

                System.out.println("\n");
                System.out.println("***** saldo atual: " + item.saldo);
                System.out.println("***** tipo transação: " + item.tipoTransacao);
                System.out.println("***** valor da transação $$$R  " + df.format(item.transacaoAnterior));
                System.out.println("***** Data da transação: " + item.dataTransação);
                System.out.println("\n");
   
            }

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Pedir saldo 
    public void getSaldo(int nConta, String pwd) {

        try {

            Account conta = new Account();

            conta = con.buscarSaldo(nConta, pwd);

            if (conta.NomeCliente != null && conta.numeroConta == nConta) {

                DecimalFormat df = new DecimalFormat("####0.00");

                System.out.println("\n");

                System.out.println("***** Seja bem vindo " + conta.NomeCliente + "!");
                System.out.println("***** Saldo disponivel $R  " + df.format(conta.saldo));
                System.out.println("\n");
                System.out.println("Precione enter para continuar");
                scanner.nextLine();
            } else {

                System.out.println("Numero de conta ou senha invalida!");
            }

            if (conta.numeroConta != nConta) {

                System.out.println("****** Numero de conta ou senha invalida! Tente novamente ******");
                System.out.println("\n");
            }

            Account cliente1 = new Account();
            cliente1.mostrarMenu();

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void mostrarMenu() {

        char opcaoInput = '\0';
        char opcao = '\0';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seja bem vindo! Escolha uma das opções abaixo e digite a letra correstpondente: ");
        System.out.println("\n");
        System.out.println("A. Checar saldo");
        System.out.println("B. Depositar ");
        System.out.println("C. Saque");
        System.out.println("D. Transação anterior / Historico");
        System.out.println("E. Criar nova conta");
        System.out.println("S. Sair");

        do {

            System.out.println("==================================================");
            System.out.println("Digite uma opção: ");
            System.out.println("==================================================");
            opcaoInput = scanner.next().charAt(0);
            opcao = Character.toUpperCase(opcaoInput);
            System.out.println("S. sair");

            switch (opcao) {

                case 'A':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int nConta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passwd = scanner.nextLine();

                    getSaldo(nConta, passwd);

                    break;

                case 'B':

                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int numConta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passWD = scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Digite o valor para depositar: ");
                    System.out.println("-----------------------------------------");
                    float vDepoisto = scanner.nextInt();

                    deposito(vDepoisto, numConta, passWD);

                    System.out.println("\n");
                    break;

                case 'C':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int numContaSaque = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passWDConta = scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Digite o valor para Sacar: ");
                    System.out.println("-----------------------------------------");
                    float saqueValor = scanner.nextInt();
                    sacar(saqueValor, numContaSaque, passWDConta);

                    System.out.println("\n");
                    break;

                case 'D':
                    System.out.println("-----------------------------------------");
                    System.out.println("Digite numero de sua conta  ");
                    System.out.println("-----------------------------------------");
                    int numContaHistorico = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String passWDContaHistorico = scanner.nextLine();
                    System.out.println("-----------------------------------------");
                    getHistorico(numContaHistorico, passWDContaHistorico);
                    
                    System.out.println("\n");
                    break;

                case 'E':
                    System.out.println("-----------------------------------------");
                    System.out.println("Nova conta bancaria, digite o numero da conta: ");
                    System.out.println("-----------------------------------------");
                    int n1Conta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Nome do cliente: ");
                    System.out.println("-----------------------------------------");
                    String nomeCli = scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Tipo de conta: ");
                    System.out.println("-----------------------------------------");
                    String tipoCon = scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Novo saldo: ");
                    System.out.println("-----------------------------------------");
                    Float saldo1 = scanner.nextFloat();
                    scanner.nextLine();

                    System.out.println("-----------------------------------------");
                    System.out.println("Senha: ");
                    System.out.println("-----------------------------------------");
                    String password = scanner.nextLine();

                    criarConta(saldo1, n1Conta, password, tipoCon, nomeCli);
                    System.out.println("\n");

                    mostrarMenu();

                    break;

                case 'S':
                    System.out.println("good bye! ---------------");
                    break;

                default:
                    System.out.println("Opção invalida! Tente novamente ");
                    break;

            }

        } while (opcao != 'S');

        System.out.println("Obrigado por usar nossos serviços!!!");
    }

}
