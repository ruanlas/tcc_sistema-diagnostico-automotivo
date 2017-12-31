package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
	public static MongoDatabase getConnection() {
		MongoClient connect = new MongoClient("18.231.62.135", 27017);
		return connect.getDatabase("car_monitor");
	}
}
