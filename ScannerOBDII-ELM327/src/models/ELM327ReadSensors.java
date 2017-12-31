package models;

public class ELM327ReadSensors {
	private String modeloCarro, chassiCarro, rpm, velocidade, pressaoCombustivel, tipoCombustivel;

	public ELM327ReadSensors() {

	}

	
	public ELM327ReadSensors(String chassiCarro, String modeloCarro) {
		this.modeloCarro = modeloCarro;
		this.chassiCarro = chassiCarro;
	}


	public ELM327ReadSensors(String modeloCarro, String chassiCarro, String rpm, String velocidade, String pressaoCombustivel,
			String tipoCombustivel) {
		this.modeloCarro = modeloCarro;
		this.chassiCarro = chassiCarro;
		this.rpm = rpm;
		this.velocidade = velocidade;
		this.pressaoCombustivel = pressaoCombustivel;
		this.tipoCombustivel = tipoCombustivel;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getChassiCarro() {
		return chassiCarro;
	}

	public void setChassiCarro(String chassiCarro) {
		this.chassiCarro = chassiCarro;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

	public String getPressaoCombustivel() {
		return pressaoCombustivel;
	}

	public void setPressaoCombustivel(String pressaoCombustivel) {
		this.pressaoCombustivel = pressaoCombustivel;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	
}
