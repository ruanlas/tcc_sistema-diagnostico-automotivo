package bluetooth;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class BluetoothConnection {
	private static StreamConnection stConnection;
	
	public static StreamConnection getConnectionBluetooth(String connectionUrl) throws IOException {
		stConnection = (StreamConnection)Connector.open(connectionUrl);
		return stConnection;
	}
}
