package com.example.android.testconnection;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
Button buttontest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttontest =  findViewById(R.id.button);
    }

    public void ViewAll(){
        buttontest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Statement stmt;
                            ResultSet rs;

                            // Register the JDBC driver for MySQL

                            Class.forName("com.mysql.jdbc.Driver");

                            String url = "jdbc:mysql://awsrds.cqsn9gv0hzme.eu-west-2.rds.amazonaws.com:3306/DronrDatabase";

                            Connection con = DriverManager.getConnection(url, "ShayaanKiani", "Shayaan100");
                            Statement select = con.createStatement();

                            // Execute a quesry

                            rs = select.executeQuery("SELECT  Username, Password FROM UserTable");

                            System.out.println("Some results:");
                            while (rs.next()) { // process results one row at a time

                                String field = rs.getString(1);
                                String amt = rs.getString(2);

                                if (field.matches("test")&&amt.matches("test")) {

                                   showMessage("Worked","");
                                }  else {
                                    showMessage("Didnt Work","");
                                }

                            }
                        } catch (Exception e) {
                            System.out.println(e); // What is this doing?
                        }




                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
