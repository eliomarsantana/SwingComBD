import java.util.Vector;


public class Pessoa {
	private long pessoa_id;
	private String nome;
	private String telefone;
	private int sexo;
	private Cidade cidade;
	private Vector<Curticao> Curticoes;
	public long getPessoa_id() {
		return pessoa_id;
	}
	public void setPessoa_id(long pessoa_id) {
		this.pessoa_id = pessoa_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Vector<Curticao> getCurticoes() {
		return Curticoes;
	}
	public void setCurticoes(Vector<Curticao> curticoes) {
		Curticoes = curticoes;
	}
}