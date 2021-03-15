package beans;

public class VeiculoBeans {

	private String id;
	private String modelo;
	private String placa;
	private String cor;
	private String valor;
	
	
	public VeiculoBeans() {
		
	}


	public VeiculoBeans(String id, String modelo, String placa, String cor, String valor) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
		this.valor = valor;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	
}
