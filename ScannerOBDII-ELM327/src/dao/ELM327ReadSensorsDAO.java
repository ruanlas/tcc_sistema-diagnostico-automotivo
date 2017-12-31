package dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import database.DBConnection;
import models.ELM327ReadSensors;

public class ELM327ReadSensorsDAO {
	private MongoCollection<Document> collection;
	
	public ELM327ReadSensorsDAO() {
		MongoDatabase database = DBConnection.getConnection();
		collection = database.getCollection("reading_sensors");
	}
	
	public void insertDB(ELM327ReadSensors readSensors) {
		Document bson = new Document();
//		inserir os dados aquiiii
//		bson.append("key", "value").append("key", "value");
		bson.append("chassi", readSensors.getChassiCarro())
			.append("modelo", readSensors.getModeloCarro())
			.append("rpm", readSensors.getRpm())
			.append("pressao_combustivel", readSensors.getPressaoCombustivel())
			.append("tipo_combustivel", readSensors.getTipoCombustivel())
			.append("velocidade", readSensors.getVelocidade())
			.append("data", LocalDate.now().toString())
			.append("hora", LocalTime.now().toString());
		
		collection.insertOne(bson);
	}
	
	
}
