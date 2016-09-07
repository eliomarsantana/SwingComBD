
public class Cidade {
	private long cidade_id;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(long cidade_id) {
		this.cidade_id = cidade_id;
	}

	public String toString(){
		return this.nome;
	}
}