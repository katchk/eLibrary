package com.katchgr.elibrary.beans;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

@ManagedBean
@ApplicationScoped
public class AppBean {
    private MongoClient mongoClient;
    private DB db;
    private Properties properties;

    @PostConstruct
    private void initMongoConnection(){
        loadProperties();
        try {
            mongoClient = new MongoClient("localhost", 27017);
            this.db = mongoClient.getDB(properties.getProperty("dbName"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy(){
        mongoClient.close();
    }

    private void loadProperties(){
        this.properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = getClass().getResourceAsStream("/app.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DB getDb() {
        return db;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
