package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScreenMonitorController;
import models.ELM327ReadSensors;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VeicheSetting extends JDialog implements ActionListener{

	private final JPanel contentPanel;
	private JTextField txtFieldModelo, txtFieldChassi;
	private JButton okButton, cancelButton;
	
	private ELM327ReadSensors elm327ReadSensors;
	private ScreenMonitorController screenMonitorController;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			VeicheSetting dialog = new VeicheSetting();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VeicheSetting() {
		
		contentPanel = new JPanel();
				
		setBounds(100, 100, 226, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPreenchaAsInformaes = new JLabel("Preencha as informa\u00E7\u00F5es");
		lblPreenchaAsInformaes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreenchaAsInformaes.setBounds(23, 0, 163, 14);
		contentPanel.add(lblPreenchaAsInformaes);
		
		JLabel lblModelo = new JLabel("Modelo: ");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblModelo.setBounds(10, 21, 46, 14);
		contentPanel.add(lblModelo);
		
		txtFieldModelo = new JTextField();
		txtFieldModelo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFieldModelo.setBounds(52, 18, 148, 20);
		contentPanel.add(txtFieldModelo);
		txtFieldModelo.setColumns(10);
		
		JLabel lblChassi = new JLabel("Chassi: ");
		lblChassi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblChassi.setBounds(10, 49, 38, 14);
		contentPanel.add(lblChassi);
		
		txtFieldChassi = new JTextField();
		txtFieldChassi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFieldChassi.setBounds(49, 46, 151, 20);
		contentPanel.add(txtFieldChassi);
		txtFieldChassi.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			this.btnOk();
		}
		if (e.getSource() == cancelButton) {
			this.dispose();
		}
		
	}
	
	private void btnOk() {
		String chassi = txtFieldChassi.getText().toString();
		String modelo = txtFieldModelo.getText().toString();
		
		if ( !chassi.isEmpty() && !modelo.isEmpty()) {
			if (elm327ReadSensors == null) {
				elm327ReadSensors = new ELM327ReadSensors(chassi, modelo);
			}else{
				elm327ReadSensors.setChassiCarro(chassi);
				elm327ReadSensors.setModeloCarro(modelo);
			}
			screenMonitorController.setELM327ReadSensors(elm327ReadSensors);
			System.out.println(elm327ReadSensors.getChassiCarro());
			System.out.println(elm327ReadSensors.getModeloCarro());
			this.dispose();
		}
	}
	
	public void setParameters(ScreenMonitorController screenMonitorController, ELM327ReadSensors elm327ReadSensors) {
		this.elm327ReadSensors = elm327ReadSensors;
		this.screenMonitorController = screenMonitorController;
	}
	
}
