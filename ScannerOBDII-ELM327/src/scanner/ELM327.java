package scanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.engine.OilTempCommand;
import com.github.pires.obd.commands.engine.RPMCommand;
import com.github.pires.obd.commands.fuel.FindFuelTypeCommand;
import com.github.pires.obd.commands.fuel.FuelLevelCommand;
import com.github.pires.obd.commands.pressure.FuelPressureCommand;
import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.HeadersOffCommand;
import com.github.pires.obd.commands.protocol.ObdResetCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolCommand;
import com.github.pires.obd.enums.ObdProtocols;

public class ELM327 {
	private OutputStream outStream;
	private InputStream inStream;
	
	public ELM327(InputStream inStream, OutputStream outStream) {
		this.outStream = outStream;
		this.inStream = inStream;
	}
	
	public void disconect() throws IOException {
		this.inStream.close();
		this.outStream.close();
	}
	public String readRpm() throws IOException, InterruptedException {
		
		RPMCommand commands = new RPMCommand();
		commands.run(inStream, outStream);
//		medicao.carregarElemento(object.getFormattedResult());
		return commands.getFormattedResult();
	}
	
	public String readSpeed() throws IOException, InterruptedException {
		SpeedCommand commands = new SpeedCommand();
		commands.run(inStream, outStream);
//		medicao.carregarElemento(object.getFormattedResult());
		return commands.getFormattedResult();
	}
	
	public String readFuelPressure() throws IOException, InterruptedException {
		this.clearBuffer();
		
		FuelPressureCommand commands = new FuelPressureCommand();
		commands.run(inStream, outStream);
//		medicao.carregarElemento(commands.getFormattedResult());
		return commands.getFormattedResult();
	}
	
	///Novo daqui para baixo
	
	public String readOilTemp() throws IOException, InterruptedException {
		this.clearBuffer();
		
		OilTempCommand commands = new OilTempCommand(); //??????
		commands.run(inStream, outStream);
//		medicao.carregarElemento(commands.getFormattedResult());
		return commands.getFormattedResult();
	}
	
	public String readFindFuelType() throws IOException, InterruptedException {
		this.clearBuffer();
		
		FindFuelTypeCommand commands = new FindFuelTypeCommand();
		commands.run(inStream, outStream);
//		medicao.carregarElemento(commands.getFormattedResult());
		return commands.getFormattedResult();
	}
	
	public String readFuelLevel() throws IOException, InterruptedException {
		this.clearBuffer();
		
		FuelLevelCommand commands = new FuelLevelCommand(); ///????
		commands.run(inStream, outStream);
//		medicao.carregarElemento(commands.getFormattedResult());
		return commands.getFormattedResult();
	}
	private void clearBuffer() throws IOException, InterruptedException {
		new ObdResetCommand().run(inStream, outStream);
		new EchoOffCommand().run(inStream, outStream);
		new HeadersOffCommand().run(inStream, outStream);
//		new SpacesOffCommand().run(inStream, outStream);
		new SelectProtocolCommand(ObdProtocols.AUTO).run(inStream, outStream);
	}
}
