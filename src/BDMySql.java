

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe implementa um exemplo simples utilizando Banco de dados (MySQL)
 * maiores refer�ncias devem ser consultadas no java doc
 * � preciso importar para o seu projeto o driver jdbc para o MySQL
 * 
 * @author Renato Novais
 * Criada em: 21/09/2008
 * �ltima atualiza��o: 21/09/2008 
 * */
public class BDMySql{
	
	private static BDMySql singleton = null;
	private Connection con;

	//padrao de projeto que cria uma �nica instancia da classe BDMySql
	public static BDMySql getInstance(){
		if (singleton == null)
		{
			singleton = new BDMySql();
		}		
			return singleton;
	}
	
	//construtor que conecta ao banco
	BDMySql(){
		try {
			
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			new com.mysql.jdbc.Driver();
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingbd", "root", "root");
		   /* O que quer dizer cada um dos par�mentros acima?
			*  banco ->     "jdbc:mysql://localhost:3306/sgwf"  //banco de nome sgwf no ip local (localhost)
			*  usu�rio -> "sgwf"
			*  senha -> "sgwfpass"
			*/
			
		   
		}
		catch(Exception e) {
			System.out.println("Nao foi poss�vel realizar a conex�o.");
			System.out.println(e.getMessage());
		}
	}
	
	
	//Executar consultas no banco: SELECTs
	public ResultSet executarBuscaSQL(String sql){
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		 }
		 catch(Exception e) {
			System.out.println(sql);
			return null;
		 }
		
	}
	
	public int executarBuscaSQL2(String sql){
		int idDado;
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			idDado = rs.getInt("novoid");
			
			System.out.println(idDado);
			return idDado;
			//return rs;
		 }
		 catch(Exception e) {
			System.out.println("Nao foi poss�vel recupear dados.");
			return 1;
		 }
		
	}
	//executar atualiza��es no banco: INSERTs, UPDATEs, DELETEs
	public void executarSQL(String sql){
		 try{
			 
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			
		 }
		 catch(Exception e) {
			System.out.println(e.getMessage() + " : " + sql);
		 }
	}
	private int getRowCount(ResultSet rs){
		int rows = 0;
		try{
			rs.last();                 
			rows = rs.getRow();          
			rs.beforeFirst();
		}catch(Exception e){
			System.out.println("Erro ao capturar a quantidade de linhas do resultset");
		}	
		return rows;
	}

	//fechando a conex�o
	public void fecharConexao(){
		try{
			con.close();
		 }
		 catch(Exception e) {
			System.out.println("Nao foi poss�vel fechar a conex�o.");
		 }
	   
	}
	
	public void finalize(){
	 	fecharConexao();
  }

}
 
