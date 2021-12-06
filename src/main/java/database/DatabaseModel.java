package database;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import database.properties.Properties;
import database.users.Landlord;
import database.users.User;

public class DatabaseModel {
    private Properties properties;
    private User user;



    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection userCollection;
    private static DBCollection propertiesCollection;
    public DatabaseModel(){
        connectToDatabase();




        User test =  new Landlord("jeff@gmail.com","jeff robertson","abc123");
        System.out.println(test.getAccessLevel());
    }

    void connectToDatabase(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://jacob_artuso:123pass123@prms.vrama.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"));
        database = mongoClient.getDB("PRMS");
        userCollection = database.getCollection("userCollection");
        /*ConnectionString connectionString = new ConnectionString("mongodb+srv://jacob_artuso:<password>@prms.vrama.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");*/
    }
}
