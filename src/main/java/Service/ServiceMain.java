package Service;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Objects.Ruleta;

public class ServiceMain 
{
	private static final String DB_NAME = "RULETAS";
	private static final String PASSWORD = "PASSWORD";
	
	public static void main(String[] args) 
	{		
		MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:"+PASSWORD+"@cluster0.ekmtq.mongodb.net/"+DB_NAME+"?retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("test");		
		MongoCollection<Document> db = database.getCollection(DB_NAME);		
		ArrayList<Ruleta> ruletas = new ArrayList<Ruleta>(); 
		
	}
}
