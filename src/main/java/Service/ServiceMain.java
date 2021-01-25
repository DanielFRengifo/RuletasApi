package Service;

import static spark.Spark.*;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Objects.Gamble;

public class ServiceMain 
{
	private static final String DB_NAME = "RULETAS";
	private static final String PASSWORD = "PASSWORD";
	
	private static ArrayList<Gamble> gambles;
	private static MongoCollection<Document> db;
	
	public static void main(String[] args) 
	{		
		MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:"+PASSWORD+"@cluster0.ekmtq.mongodb.net/"+DB_NAME+"?retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("test");		
		db = database.getCollection(DB_NAME);		
		gambles = new ArrayList<Gamble>(); 
		
		post("/Ruleta", (request, response) -> 
        {
        	Gamble gamble = new Gamble(gambles.size());
        	gambles.add(gamble);        	
        	updateDB(gamble);        	
        	return String.valueOf(gamble.getId());
        });
	}
	
	public static void updateDB(Gamble gamble)
	{
		Document document = new Document();
    	document.put("id", String.valueOf(gamble.getId()));
    	document.put("state", String.valueOf(gamble.getState()));
    	document.put("bets", String.valueOf(gamble.getBets()));
    	db.insertOne(document);
	}
}
