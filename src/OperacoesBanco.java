import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class OperacoesBanco {
	private BDMySql banco;

	public OperacoesBanco(){
		 banco = BDMySql.getInstance();
	}

	public Vector <Cidade> listaCidades(){
		ResultSet rs;
		Cidade cidade;
		Vector <Cidade> cidades = new Vector<Cidade>();

		rs = banco.executarBuscaSQL("select * from cidades");
		
		try {
			while (rs.next()){
				cidade = new Cidade();
				cidade.setNome(rs.getString("nome"));
				cidade.setCidade_id(rs.getLong("cidade_id"));
				cidades.add(cidade);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return cidades;
	}
	
	public Vector <Curticao> listaCurticoes(){
		ResultSet rs;
		Curticao curticao;
		Vector <Curticao> curticoes = new Vector<Curticao>();

		rs = banco.executarBuscaSQL("select * from curticoes");
		
		try {
			while (rs.next()){
				curticao = new Curticao();
				curticao.setDescricao(rs.getString("descricao"));
				curticao.setCurticao_id(rs.getLong("curticao_id"));
				curticoes.add(curticao);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return curticoes;
	}

	public Vector <Pessoa> listaPessoas(){
		ResultSet rs;
		Pessoa pessoa;
		Vector <Pessoa> pessoas = new Vector<Pessoa>();

		rs = banco.executarBuscaSQL("select * from pessoas");

		try {
			while (rs.next()){
				pessoa = new Pessoa();
				pessoa.setNome(rs.getString("nome"));
				pessoa.setPessoa_id(rs.getLong("pessoa_id"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setSexo(rs.getInt("sexo"));
				pessoas.add(pessoa);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return pessoas;
	}

	public void SalvaPessoa(Pessoa pessoa){
		long pessoa_id = 0;
		ResultSet rs;

		banco.executarSQL("insert into pessoas(nome,telefone,sexo,cidade_id) values ('"+pessoa.getNome()+"','"+
				pessoa.getTelefone()+"', "+pessoa.getSexo()+", "+pessoa.getCidade().getCidade_id()+")");

		rs = banco.executarBuscaSQL("select max(pessoa_id) as pessoa_id from pessoas");

		try {
			rs.next();
			pessoa_id = rs.getLong("pessoa_id");
		} catch(SQLException e) {
		}

		for (int i=0;i<pessoa.getCurticoes().size();i++)
			banco.executarSQL("insert into pessoas_curticoes(pessoa_id, curticao_id) values ("+pessoa_id+", "+pessoa.getCurticoes().get(i).getCurticao_id()+")");
	}

	public void AtualizaPessoa(Pessoa pessoa){
		ResultSet rs;

		banco.executarSQL("update pessoas set nome = '"+pessoa.getNome()+"', telefone = '"+pessoa.getTelefone()+"', sexo = "+pessoa.getSexo()+", cidade_id="+pessoa.getCidade().getCidade_id()+
				" where pessoa_id = "+pessoa.getPessoa_id());

		banco.executarSQL("delete from pessoas_curticoes where pessoa_id = "+pessoa.getPessoa_id());

		for (int i=0;i<pessoa.getCurticoes().size();i++)
			banco.executarSQL("insert into pessoas_curticoes(pessoa_id, curticao_id) values ("+pessoa.getPessoa_id()+", "+pessoa.getCurticoes().get(i).getCurticao_id()+")");
	}
	
	public Pessoa carregaPessoa(long pessoa_id){
		ResultSet rs;
		Pessoa pessoa = new Pessoa();
		Cidade cidade = new Cidade();
		Curticao curticao = null;
		Vector <Curticao> curticoes = new Vector();
		try {
			rs = banco.executarBuscaSQL("select p.pessoa_id as pessoa_id, p.nome as pnome, p.sexo as sexo, " +
					"p.telefone as telefone, c.cidade_id as cidade_id, c.nome as cnome from pessoas p, " +
					"cidades c where p.cidade_id = c.cidade_id and p.pessoa_id = " + pessoa_id);
			rs.next();
			cidade.setCidade_id(rs.getLong("cidade_id"));
			cidade.setNome(rs.getString("cnome"));
			pessoa.setPessoa_id(pessoa_id);
			pessoa.setSexo(rs.getInt("sexo"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setNome(rs.getString("pnome"));
			pessoa.setCidade(cidade);

			rs = banco.executarBuscaSQL("select c.* from curticoes c, pessoas_curticoes pc where c.curticao_id = pc.curticao_id " +
					"and pc.pessoa_id = " + pessoa_id);
			while (rs.next()){
				curticao = new Curticao();
				curticao.setCurticao_id(rs.getLong("curticao_id"));
				curticao.setDescricao(rs.getString("descricao"));
				curticoes.add(curticao);
			}

			pessoa.setCurticoes(curticoes);
		} catch(SQLException e){
		}

		return pessoa;
	}

	public void removePessoa(long pessoa_id){
		 banco.executarSQL("delete from pessoas_curticoes where pessoa_id = " + pessoa_id);
		 banco.executarSQL("delete from pessoas where pessoa_id = " + pessoa_id);
	}
}