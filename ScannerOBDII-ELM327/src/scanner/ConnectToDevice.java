package scanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.StreamConnection;

import bluetooth.BluetoothConnection;
import bluetooth.DiscoveryDevices;

public class ConnectToDevice {
	private DiscoveryDevices discoveryDevices;
	private DiscoveryAgent discoveryAgent;
	private Vector remoteDevices;
	private Object lock;
	private String connectionUrl;
	private String status;
	
	public ConnectToDevice(DiscoveryDevices discoveryDevices) {
		this.discoveryDevices = discoveryDevices;
		this.discoveryAgent = discoveryDevices.getDiscoveryAgent();
		this.remoteDevices = discoveryDevices.getRemoteDevices();
		this.lock = discoveryDevices.getLock();
		this.connectionUrl = discoveryDevices.getConnectionUrl();
	}
	
	public ELM327 connectToDevice(int index) throws IOException {
		ELM327 scanner = null;
		RemoteDevice des_device = (RemoteDevice)remoteDevices.elementAt(index);
        UUID[] uuidset=new UUID[1];
        uuidset[0]=new UUID("1101",true);

        discoveryAgent.searchServices(null, uuidset, des_device, discoveryDevices);
        try
        {
            synchronized(lock)
            {
                lock.wait();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        if(connectionUrl == null)
        {
        	status = "Status: Device does not support SPP.";
            System.out.println(status);
        }
        else
        {
        	status = "Status: Device supports SPP.";
            System.out.println(status);
            StreamConnection objectConnection = BluetoothConnection.getConnectionBluetooth(connectionUrl);
            OutputStream outStream = objectConnection.openOutputStream();
    		InputStream inStream = objectConnection.openInputStream();
    		scanner = new ELM327(inStream, outStream);
        }
		return scanner;
	}
	
	public String getStatus() {
		return status;
	}
}
