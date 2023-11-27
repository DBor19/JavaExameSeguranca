package tests;

import connections.ConnectionFactory;

import java.sql.Connection;

public class TesteConnection {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionFactory.getConnection();
            System.out.println("Conectado ao BD com sucesso!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
