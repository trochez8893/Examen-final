

package com.mycompany.examen2;


import com.mongodb.client.*;

import com.mongodb.MongoException;

import com.mongodb.client.result.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.model.ReplaceOptions;
//import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.List;
import javax.swing.JOptionPane;

public class conexionDB {
    MongoClient conn;
    String servidor="localhost";
    Integer puerto=27017;
    MongoDatabase dataBaseSelect;

//constructor
    public conexionDB(){
       try{
           this.conn = MongoClients.create("mongodb://"+servidor+":"+ puerto.toString());
           System.out.println("Conexion exitosa");
       }catch(MongoException error){
           System.out.println("Error en conexion: " + error.toString());
       }
    }

   public void setBD(){
       dataBaseSelect = conn.getDatabase("Produccion");
       System.out.println("DB Selecionada: " + dataBaseSelect.toString());  
    }

    public MongoDatabase getDB(){
       return dataBaseSelect;
    }

//OPERACIONES CRUD a un documento de la BD de Mongo

    public boolean insertDocuments(MongoCollection<Document> collection,Document newEquipo){
       try{
            collection.insertOne(newEquipo);
            JOptionPane.showMessageDialog(null, "Registro creado con exito!","Importante!",JOptionPane.INFORMATION_MESSAGE);
            return true;
        }catch(MongoException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public FindIterable<Document> getDocuments(MongoCollection<Document> collection){
        FindIterable<Document> iterable =null;
        try{
           iterable  = collection.find();
        }catch(MongoException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!", JOptionPane.ERROR_MESSAGE);
        }
        return iterable;
    }

    public boolean deleteDocuments(MongoCollection<Document> collection,String id){
        try{
            // delete one document
            Bson filter = eq("_id",new ObjectId(id));
       //     Document doc = this.Equipos.findOneAndDelete(filter);
            DeleteResult result = collection.deleteOne(filter);
            return result.getDeletedCount()>0 ? true : false;
        }catch(MongoException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser eliminado","Importante!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
}

    public boolean actualizarDocuments(MongoCollection<Document> collection,Document data,String id){
            try{
                        Bson filter = eq("_id", new ObjectId(id));
                        UpdateResult updateResult = collection.replaceOne(filter, data);
                    return updateResult.getModifiedCount()>0 ? true : false;
            }catch(MongoException error){
                        JOptionPane.showMessageDialog(null, "Registro no pudo ser actualizado","Importante!", JOptionPane.ERROR_MESSAGE);
                        return false;
            }
    }
}
