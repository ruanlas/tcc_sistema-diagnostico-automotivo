package controller;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.bluetooth.BluetoothStateException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import bluetooth.DiscoveryDevices;
import dao.ELM327ReadSensorsDAO;
import models.ELM327ReadSensors;
import scanner.ConnectToDevice;
import scanner.ELM327;
import view.VeicheSetting;

public class ScreenMonitorController {
	private DiscoveryDevices discoveryDevices;
	private ELM327 elm327;
	private ELM327ReadSensors elm327ReadSensors;
	private ELM327ReadSensorsDAO dao;
	
	
	public ScreenMonitorController() throws BluetoothStateException, IOException {
		discoveryDevices = new DiscoveryDevices();
		discoveryDevices.discovery();
		dao = new ELM327ReadSensorsDAO();
	}
	
	public void listarDevicesComboBox(JComboBox cbListDevices, JLabel lblStatus) {
		cbListDevices.removeAllItems();
		for (String device : discoveryDevices.getNamesRemoteDevices()) {
			cbListDevices.addItem(device);
		}
		lblStatus.setText(discoveryDevices.getStatus());
	}
	
	public void atualizarConexao() throws BluetoothStateException, IOException {
		discoveryDevices.discovery();
	}
	
	public void btnConnectar(int index, JLabel lblStatus) throws IOException {
		if(index != -1){
			try {
				VeicheSetting dialog = new VeicheSetting();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setParameters(this, elm327ReadSensors);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ConnectToDevice connectToDevice = new ConnectToDevice(discoveryDevices);
			elm327 = connectToDevice.connectToDevice(index);
			lblStatus.setText(connectToDevice.getStatus());
		}
		
//		System.out.println(index); //retorna -1 quando não há seleção
		
	}
	public void btnRealizarLeitura(JLabel lblRpm, JLabel lblTipoCumbustivel, 
			JLabel lblPressaoMotor,JLabel lblVelocidade) throws IOException, InterruptedException {
		this.elm327ReadSensors.setRpm(elm327.readRpm());
		this.elm327ReadSensors.setPressaoCombustivel(elm327.readFuelPressure());
		this.elm327ReadSensors.setTipoCombustivel(elm327.readFindFuelType());
		this.elm327ReadSensors.setVelocidade(elm327.readSpeed());
		
		lblRpm.setText("RPM: " + elm327ReadSensors.getRpm());
		lblTipoCumbustivel.setText("Tipo Combustível: " + elm327ReadSensors.getTipoCombustivel());
		lblPressaoMotor.setText("Pressão do Combustível: " + elm327ReadSensors.getPressaoCombustivel());
		lblVelocidade.setText("Velocidade: " + elm327ReadSensors.getVelocidade());
		
//		dao.insertDB(elm327ReadSensors);
	}
	public void setELM327ReadSensors(ELM327ReadSensors elm327ReadSensors) {
		this.elm327ReadSensors = elm327ReadSensors;
	}
}
