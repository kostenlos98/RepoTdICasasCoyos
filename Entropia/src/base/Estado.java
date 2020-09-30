package base;

public class Estado {
	
	private double prob;
	private String simbolo;
	
	public Estado(String simbolo, Double prob) {
		super();
		this.prob = prob;
		this.simbolo = simbolo;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	
	
	

}
