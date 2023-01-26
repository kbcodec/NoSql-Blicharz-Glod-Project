package org.example.collections.user;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BSONObject;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.example.collections.district.Districts;
import org.example.collections.inhabitant.Inhabitants;
import org.example.database.MongoDBConnection;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Users {
    private ObjectId id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Users() {
    }

    public Users(String login, String email, String password, String firstName, String lastName) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addUser() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        Document newUser = new Document();
        newUser.append("login", this.login);
        newUser.append("email", this.email);
        newUser.append("password", BCrypt.withDefaults().hashToString(12, this.password.toCharArray()));
        newUser.append("firstName", this.firstName);
        newUser.append("lastName", this.lastName);
        database.getCollection("Users").insertOne(newUser);
    }

    public Users checkUser(String login, String email) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Users> collection = database.getCollection("Users", Users.class);
        Document loginDoc = new Document();
        loginDoc.append("login", login);
        Document emailDoc = new Document();
        emailDoc.append("email", email);
        List<Document> reqDocs = new ArrayList<>();
        reqDocs.add(loginDoc);
        reqDocs.add(emailDoc);
        Document regQuery = new Document();
        regQuery.append("$or", reqDocs);

        return collection.find(regQuery).first();
    }

    public Users readUser(String login) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Users> collection = database.getCollection("Users", Users.class);

        return collection.find(Filters.eq("login", login)).first();
    }

    public boolean areLoginAndPasswordCorrectForExistingUser(String login, String password) {
        Users user = new Users().readUser(login);
        return user != null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
