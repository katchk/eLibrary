package com.katchgr.elibrary.beans;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.katchgr.elibrary.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    @ManagedProperty(value = "#{appBean}")
    AppBean appBean;

    /**
     * Adding new user to db
     * @param user
     * @return
     */
    public boolean writeToDB(User user){
        String userCollectionName = appBean.getProperty("userCollectionName");
        DB db = appBean.getDb();

        BasicDBObject doc = new BasicDBObject("form", "user").append("login", user.getLogin()).append("password", user.getPassword());

        db.getCollection(userCollectionName).insert(doc);
        return true;
    }

    /**
     * Check if user exists in db
     * @param user
     * @return
     */
    public boolean isExists(User user){
        String userCollectionName = appBean.getProperty("userCollectionName");
        DB db = appBean.getDb();

        BasicDBObject query = new BasicDBObject("name", "user").append("email", user.getLogin()).append("pwd", user.getPassword());
        return db.getCollection(userCollectionName).findOne(query) != null;
    }

    public void setAppBean(AppBean appBean) {
        this.appBean = appBean;
    }
}
