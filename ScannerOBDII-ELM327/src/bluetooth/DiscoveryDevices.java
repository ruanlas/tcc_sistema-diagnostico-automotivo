package bluetooth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class DiscoveryDevices implements DiscoveryListener{
	private Object lock;
	private Vector remoteDevices;
	private List<String> namesRemoteDevices;
	private String connectionUrl;
	private LocalDevice localDevice;
	private DiscoveryAgent discoveryAgent;
	private String status;
	
	public DiscoveryDevices() {
		this.lock = new Object();
		this.remoteDevices = new Vector();
		this.namesRemoteDevices = new ArrayList<String>();
	}
	
	public void discovery() throws BluetoothStateException, IOException {
		localDevice = LocalDevice.getLocalDevice();
		discoveryAgent = localDevice.getDiscoveryAgent();
		
		discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
		try
	    {
	        synchronized (lock)
	            {
	                lock.wait();
	            }
	    }
	    catch(InterruptedException e)
	    {
	        e.printStackTrace();
	    }

	    if(remoteDevices.size()<=0)
	    {
	    	status = "Status: No devices found";
	        System.out.println(status);
	    }
	    else
	    {
	    	namesRemoteDevices.clear();
	        for(int i = 0; i < remoteDevices.size(); i++)
	        {
	            RemoteDevice remote_device = (RemoteDevice)remoteDevices.elementAt(i);
	            System.out.println((i+1)+".)"+remote_device.getFriendlyName(true)+" "+remote_device.getBluetoothAddress());
	            //Adiciona os elementos na List
	            namesRemoteDevices.add((i+1)+".)"+remote_device.getFriendlyName(true)+" "+remote_device.getBluetoothAddress());
	        }

	    }

	}

	@Override
	public void deviceDiscovered(RemoteDevice device, DeviceClass dClass) {
		if( !remoteDevices.contains(device) )
        {
			remoteDevices.addElement(device);
        }
	}

	@Override
	public void inquiryCompleted(int discType) {
		synchronized(lock)
	    {
	        lock.notify();
	    }
	    switch(discType)
	    {
	    case DiscoveryListener.INQUIRY_COMPLETED:
	    	status = "Status: Inquiry Completed";
	        System.out.println(status);
	        break;

	    case DiscoveryListener.INQUIRY_TERMINATED:
	    	status = "Status: Inquiry Terminated";
	        System.out.println(status);
	        break;

	    case DiscoveryListener.INQUIRY_ERROR:
	    	status = "Status: Inquiry Error";
	        System.out.println(status);
	        break;

	    default:
	    	status = "Status: Unknown Response Code";
	        System.out.println(status);
	    }
	}

	@Override
	public void serviceSearchCompleted(int arg0, int arg1) {
		synchronized(lock)
	    {
	        lock.notify();
	    }
	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] serviceRecord) {
		if( !(serviceRecord==null) && serviceRecord.length>0 )
	    {
	        connectionUrl=serviceRecord[0].getConnectionURL(0, false);
	    }
	}
	
	public Vector getRemoteDevices() {
		return remoteDevices;
	}
	
	public List<String> getNamesRemoteDevices() {
		return namesRemoteDevices;
	}
	
	public DiscoveryAgent getDiscoveryAgent() {
		return discoveryAgent;
	}
	
	public Object getLock() {
		return lock;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public String getStatus() {
		return status;
	}
	
}
