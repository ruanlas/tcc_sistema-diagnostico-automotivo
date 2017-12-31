package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ScreenMonitorController;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.bluetooth.BluetoothStateException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenMonitor implements ActionListener{

	private JFrame frame;
	private JLabel lblScannerObd2, lblSelecionarDispositivo, lblStatus, lblRpm,
					lblTipoCumbustivel, lblPressaoMotor, lblVelocidade;
	private JButton btnAtualizarConexao, btnConectar, btnRealizarLeitura;
	private JComboBox cbListDevices;
	
	private ScreenMonitorController controller;

	/**
	 * Create the application.
	 */
	public ScreenMonitor() {
		initialize();
		try {
			controller = new ScreenMonitorController();
		} catch (BluetoothStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.listarDevicesComboBox(cbListDevices, lblStatus);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 308, 221);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblScannerObd2 = new JLabel("Scanner OBDII");
		lblScannerObd2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScannerObd2.setBounds(94, 0, 129, 20);
		frame.getContentPane().add(lblScannerObd2);
		
		lblSelecionarDispositivo = new JLabel("Selecionar Dispositivo:");
		lblSelecionarDispositivo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelecionarDispositivo.setBounds(10, 21, 111, 14);
		frame.getContentPane().add(lblSelecionarDispositivo);
		
		cbListDevices = new JComboBox();
		cbListDevices.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbListDevices.setBounds(125, 18, 157, 20);
		frame.getContentPane().add(cbListDevices);
		
		btnAtualizarConexao = new JButton("Atualizar Conex\u00E3o");
		btnAtualizarConexao.addActionListener(this);
		btnAtualizarConexao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarConexao.setBounds(10, 44, 151, 23);
		frame.getContentPane().add(btnAtualizarConexao);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(this);
		btnConectar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConectar.setBounds(165, 44, 117, 23);
		frame.getContentPane().add(btnConectar);
		
		lblStatus = new JLabel("Status: ");
		lblStatus.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblStatus.setBounds(10, 70, 272, 14);
		frame.getContentPane().add(lblStatus);
		
		btnRealizarLeitura = new JButton("Realizar Leitura");
		btnRealizarLeitura.addActionListener(this);
		btnRealizarLeitura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRealizarLeitura.setBounds(81, 148, 123, 23);
		frame.getContentPane().add(btnRealizarLeitura);
		
		lblRpm = new JLabel("RPM:");
		lblRpm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRpm.setBounds(10, 100, 123, 14);
		frame.getContentPane().add(lblRpm);
		
		lblTipoCumbustivel = new JLabel("Tipo Cumbustivel:");
		lblTipoCumbustivel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoCumbustivel.setBounds(10, 85, 184, 14);
		frame.getContentPane().add(lblTipoCumbustivel);
		
		lblPressaoMotor = new JLabel("Press\u00E3o Motor:");
		lblPressaoMotor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPressaoMotor.setBounds(10, 115, 129, 14);
		frame.getContentPane().add(lblPressaoMotor);
		
		lblVelocidade = new JLabel("Velocidade:");
		lblVelocidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVelocidade.setBounds(10, 132, 129, 14);
		frame.getContentPane().add(lblVelocidade);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAtualizarConexao) {
			atualizarConexao();
		}
		if (e.getSource() == btnConectar) {
			connectar();
		}
		if (e.getSource() == btnRealizarLeitura) {
			realizarLeitura();
		}
	}
	
	private void atualizarConexao() {
		try {
			controller.atualizarConexao();
		} catch (BluetoothStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		controller.listarDevicesComboBox(cbListDevices, lblStatus);
	}
	
	private void connectar() {
		
		int index = cbListDevices.getSelectedIndex();
		try {
			controller.btnConnectar(index, lblStatus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void realizarLeitura() {
		try {
			controller.btnRealizarLeitura(lblRpm, lblTipoCumbustivel, lblPressaoMotor, lblVelocidade);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
