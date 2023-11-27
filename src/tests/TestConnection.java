package tests;

import connections.ConnectionFactory;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionFactory.getConnection();
            System.out.println("Conectado ao banco de dados");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
