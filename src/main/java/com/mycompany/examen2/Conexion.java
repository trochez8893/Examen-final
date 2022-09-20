/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;


public class Conexion {
     MongoClient conex;
    String servidor="localhost";
    int puerto=27017;
    
    public Conexion(){
           try{
            conex = new MongoClient (servidor,puerto);
               System.out.println("Conexion exitosa");
           }catch(MongoClientException error) {
               System.out.println("Error en conexion: "+ error.toString());
           }
    }
    
    public void mostrarBD(){
    
    }
}

           
