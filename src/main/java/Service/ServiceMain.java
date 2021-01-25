package Service;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Objects.Bet;
import Objects.Gamble;

public class ServiceMain 
{
	private static final String DB_NAME = "RULETAS";
	private static final String PASSWORD = "PASSWORD";
	
	private static HashMap<String,Gamble> gambles;
	private static MongoCollection<Document> db;
	
	public static void main(String[] args) 
	{		
		MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:"+PASSWORD+"@cluster0.ekmtq.mongodb.net/"+DB_NAME+"?retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("test");		
		db = database.getCollection(DB_NAME);		
		gambles = new HashMap<String,Gamble>();
		
		FindIterable<Document> cursor = db.find();
		Iterator<Document> it = cursor.iterator();
	    while (it.hasNext()) 
	    {
	    	Document documentLoad = (Document) it.next();
	    	System.out.println(documentLoad);
	    	Gamble gambleLoad = new Gamble();
	    	gambleLoad.setId(documentLoad.get("_id").toString());
	    	gambleLoad.setState(documentLoad.get("state").toString().equals("true"));
	    	gambleLoad.setBetsString(documentLoad.get("bets").toString());	
	    	gambles.put(documentLoad.get("_id").toString(), gambleLoad);
	    }
	    
		get("/Ruletas", (request, response) -> 
        { 
        	String resp = "";
        	Set<String> keys = gambles.keySet();
        	Iterator<String> iter = keys.iterator();
            while (iter.hasNext()) 
            {
            	Gamble gamble = gambles.get(iter.next());
            	resp += "Ruleta: " + gamble.getId() + " Estado: ";
    			resp += (gamble.getState() ? "ABIERTA" : "CERRADA");
    			resp += (iter.hasNext() ? " || " : "");
            }
        	
        	return resp;
        });
		
		post("/Ruleta", (request, response) -> 
        {
        	Gamble gamble = new Gamble();        	
        	Document document = new Document();
        	document.put("state", "false");
        	document.put("bets", "");
        	db.insertOne(document);        	
        	String id = document.get("_id").toString();
        	gamble.setId(id);        	
        	gambles.put(id, gamble);   
        	
        	return String.valueOf(gamble.getId());
        });
		
		post("/OpenRuleta", (request, response) -> 
        {
        	try
        	{        
            	String id = request.body().split(":")[1].replaceAll("[^a-zA-Z0-9]", "");
            	Gamble gamble = gambles.get(id); 
            	gamble.setState(true);
            	updateDB(gamble);            	
            	
            	return "TRUE";
        	}
        	catch (Exception e)
        	{
        		return "FALSE";
        	}
        });
		
		post("/CloseRuleta", (request, response) -> 
        {
        	try
        	{
        		String id = request.body().split(":")[1].replaceAll("[^a-zA-Z0-9]", "");
        		Gamble gamble = gambles.get(id); 
            	if (gamble.getState())
            	{                   	
            		String resp = gamble.close();
            		gamble.setBets(new ArrayList<Bet>());
            		gamble.setState(false);
            		updateDB(gamble);
            		
                	return "TRUE || " + resp;
            	}
            	else
            	{
            		return "TRUE";
            	}
        	}
        	catch (Exception e)
        	{
        		return "FALSE";
        	}
        });
		
		post("/Apuesta", (request, response) -> 
        {
        	try
        	{
        		String[] arrParams = request.body().split(",");
        		String[] tempStr = new String[2];
        		Map<String,String> params = new HashMap<String,String>(); 
        		for (String string : arrParams) 
        		{
        			tempStr = string.split(":");
        			params.put(tempStr[0].replaceAll("\\W+",""), tempStr[1].replaceAll("\\W+",""));
        		}      		
            	Gamble gamble = gambles.get(params.get("id").toString());
            	Bet bet = new Bet(params.get("bet"), Integer.parseInt(params.get("money")), request.headers("Pragma").toString());
            	boolean victory = gamble.bet(bet);
            	updateDB(gamble);
            	
            	return ((victory) ? "TRUE" : "FALSE");
        	}
        	catch (Exception e)
        	{
        		return "FALSE";
        	}
        });
	}
	
	public static void updateDB(Gamble gamble)
	{
		BasicDBObject query = new BasicDBObject();
    	query.put("_id", new ObjectId(gamble.getId()));
    	System.out.println(query);
    	BasicDBObject newDocument = new BasicDBObject();
    	newDocument.put("_id", new ObjectId(gamble.getId()));
    	newDocument.put("state", String.valueOf(gamble.getState()).toLowerCase());
    	newDocument.put("bets", gamble.getBetsString());
    	BasicDBObject updateObject = new BasicDBObject();
    	updateObject.put("$set", newDocument);
    	System.out.println(updateObject);
    	db.updateOne(query, updateObject);
	}
}
