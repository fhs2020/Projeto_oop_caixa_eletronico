package utilitarios;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import new_bank_poo_pos_graduação.Account;
import java.util.ArrayList;

public class ConectaBanco {

    public Statement stm;
    public PreparedStatement preparedStatement = null;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/new_bank_fhs?zeroDateTimeBehavior=convertToNull";
    private String usuario = "root";
    private String senhaBanco = "123456";
    public Connection conn;

    public void conexao() {

        try {

            System.setProperty("jdbc.Drivers", driver);
            conn = DriverManager.getConnection(caminho, usuario, senhaBanco);
            JOptionPane.showMessageDialog(null, "Conectatdo com sucesso! Bem vindo ao melhor banco do mundo!");

        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro: " + ex.getMessage());
        }
    }

    public boolean criaNovaContaBancaria(float saldo, int numeroConta, String senha, String tipoConta, String nomeCliente, String tipoTransacao, Date dataDeposito, Date dataTrasacao) throws SQLException {

        Statement statement = null;

  
         java.util.Date date = new java.util.Date();
         java.sql.Date DataAbertura = new java.sql.Date(date.getTime());

        String query = "INSERT INTO conta ("
                + " saldo,"
                + " ContaId,"
                + " NumeroConta,"
                + " DataAbertura,"
                + " transacaoAnterior,"
                + " senha,"
                + " tipoConta,"
                + " NomeCliente,"
                + " clienteid,"
                + " dataDeposito,"
                + " dataSaque,"
                + " dataTransacao,"
                + " tipoTransacao) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String queryHistorico = "INSERT INTO historico ("
                                    + " conta,"
                                    + " dataTransacao,"
                                    + " tipoTransacao,"
                                    + " valor,"
                                    + " dataAbertura ) VALUES ("
                                    + "?, ?, ?, ?, ?)";

        try {
            Connection dbConn = DriverManager.getConnection(caminho, usuario, senhaBanco);

            preparedStatement = dbConn.prepareStatement(query);

            preparedStatement.setFloat(1, saldo);
            preparedStatement.setInt(2, numeroConta);
            preparedStatement.setInt(3, numeroConta);
            preparedStatement.setDate(4, null);
            preparedStatement.setFloat(5, 00);
            preparedStatement.setString(6, senha);
            preparedStatement.setString(7, tipoConta);
            preparedStatement.setString(8, nomeCliente);
            preparedStatement.setInt(9, numeroConta);
            preparedStatement.setDate(10, dataDeposito);
            preparedStatement.setDate(11, null);
            preparedStatement.setDate(12, dataTrasacao);
            preparedStatement.setString(13, tipoTransacao);
            preparedStatement.executeUpdate();
            
            preparedStatement = dbConn.prepareStatement(queryHistorico);
            
            
            preparedStatement.setFloat(1, numeroConta);
            preparedStatement.setDate(2, dataTrasacao);
            preparedStatement.setString(3, tipoTransacao);
            preparedStatement.setFloat(4, saldo);
            preparedStatement.setDate(5, DataAbertura);
            preparedStatement.executeUpdate();
            

            return true;

        } catch (SQLException e) {

            System.out.println(e.toString());
            return false;

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean updateSaldo(float saldoAtual, int numeroConta, String tipoTransacao, Date dataDeposito, Date dataTransacao, float valorTransacao) throws SQLException {

        Statement statement = null;

        String queryToUpdateSaldo = "UPDATE conta SET saldo=?, dataDeposito=?, dataTransacao=?, tipoTransacao=? WHERE NumeroConta=?";
        
        String queryHistorico = "INSERT INTO historico ("
                                    + " conta,"
                                    + " dataTransacao,"
                                    + " tipoTransacao,"
                                    + " valor,"
                                    + " valorTransacao ) VALUES ("
                                    + "?, ?, ?, ?, ?)";

        LocalDate data = LocalDate.now();

        java.sql.Date dataDeposito2 = getCurrentDatetime();

        try {
            Connection dbConn = DriverManager.getConnection(caminho, usuario, senhaBanco);

            preparedStatement = dbConn.prepareStatement(queryToUpdateSaldo);

            preparedStatement.setFloat(1, saldoAtual);
            preparedStatement.setDate(2, new java.sql.Date(dataDeposito2.getTime()));
            preparedStatement.setDate(3, dataTransacao);
            preparedStatement.setString(4, tipoTransacao);
            preparedStatement.setInt(5, numeroConta);

            preparedStatement.executeUpdate();
            
            
            preparedStatement = dbConn.prepareStatement(queryHistorico);

            preparedStatement.setFloat(1, numeroConta);
            preparedStatement.setDate(2,  dataTransacao);
            preparedStatement.setString(3, tipoTransacao);
            preparedStatement.setFloat(4, saldoAtual);
            preparedStatement.setFloat(5, valorTransacao);


            preparedStatement.executeUpdate();
            

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public Account buscarSaldo(int numeroConta, String senha) throws SQLException {

        Statement statement = null;
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();

        String query = "SELECT * from conta WHERE  NumeroConta = ? and senha = ?";

        try {
            Connection dbConn = DriverManager.getConnection(caminho, usuario, senhaBanco);

            preparedStatement = dbConn.prepareStatement(query);
            preparedStatement.setInt(1, numeroConta);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            int numContaN = 0;
            float saldoN1 = 0;
            String nomeCli = "";
            int contaIdN = 0;

            while (rs.next()) {

                saldoN1 = rs.getFloat("saldo");
                contaIdN = rs.getInt("ContaId");
                numContaN = rs.getInt("NumeroConta");
                nomeCli = rs.getString("NomeCliente");
            }

            account.saldo = new Double(saldoN1);
            account.numeroConta = numContaN;
            account.NomeCliente = nomeCli;

            return (account);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return (account);

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

    }
    
    
       public ArrayList<Account> buscarHistorico(int numeroConta, String senha) throws SQLException {

        Statement statement = null;
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        ArrayList<Account> accountList = new ArrayList<>();

        String query = "SELECT * from historico WHERE  conta = ?";

        try {
            Connection dbConn = DriverManager.getConnection(caminho, usuario, senhaBanco);

            preparedStatement = dbConn.prepareStatement(query);
            preparedStatement.setInt(1, numeroConta);

            ResultSet rs = preparedStatement.executeQuery();

            int numContaN = 0;
            float saldoN1 = 0;
            String tipoTransacao = "";
            int contaIdN = 0;
            java.util.Date date = new java.util.Date();
            java.sql.Date dataTransacao = null;
              

            while (rs.next()) {
                
                float valorTransacao = 0;

                Account account2 = new Account();
                
                account2.saldo = rs.getFloat("valor");
                account2.numeroConta = rs.getInt("conta");
                account2.dataTransação = rs.getDate("dataTransacao");
                account2.tipoTransacao = rs.getString("tipoTransacao");
                valorTransacao = rs.getFloat("valorTransacao");
                account2.transacaoAnterior = (double)valorTransacao;
                
                accountList.add(account2);
                
            }

            return (accountList);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return (accountList);

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

    }
    
    

    public Account depositar(int numeroConta, String senha) throws SQLException {

        Statement statement = null;
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();

        String query = "SELECT * from historico WHERE  NumeroConta = ? and senha = ?";

        try {
            Connection dbConn = DriverManager.getConnection(caminho, usuario, senhaBanco);

            preparedStatement = dbConn.prepareStatement(query);
            preparedStatement.setInt(1, numeroConta);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                float saldoAtual = rs.getFloat("saldo");
                int contaIdN = rs.getInt("ContaId");
                int numContaNoBanco = rs.getInt("NumeroConta");
                String nomeCli = rs.getString("NomeCliente");

              return account;
            }

        } catch (SQLException e) {

            return account;
            //System.out.println("Nao inseriu os dados no banco corretamente!");

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public void desconecta() {

        try {
            conn.close();
            JOptionPane.showMessageDialog(null, "Conexão foi fechada com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar fechar conexão!\n Erro: " + ex.getMessage());
        }
    }

    public java.sql.Date getCurrentDatetime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
}
