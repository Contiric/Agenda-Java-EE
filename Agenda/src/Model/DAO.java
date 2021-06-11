package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Módulo de conexão
	// Parâmetros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "meriva2010";

	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {
			// Abrir a conexão com o banco
			Connection con = conectar();
			// Preparar a query para ser executada no banco
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir as ? pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Inserir os dados no banco
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**  CRUD READ **/
	public ArrayList<JavaBeans> listaContatos() {
		// Criando um objeto para acessar a JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// Abrindo conexão
			Connection con = conectar();
			// Preparar a query para ser executada no banco
			PreparedStatement pstm = con.prepareStatement(read);
			// Result Set é usado para armazenar o retorno do BD temporariamente em um
			// objeto
			ResultSet rs = pstm.executeQuery();
			// Laço abaixo será executado equanto houver contatos
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				String idcontato = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcontato, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Teste de conexão
//	public void testeConexão() {
//		try {
//			Connection con = conectar();
//			System.out.println("Conexão realizada com sucesso ");
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	//CRUD UPDATE
		//Selecionar o contato
		public void selecionarContato (JavaBeans contato) {
			String createDois = "select * from contatos where idcontato = ?";
			try {
				Connection con = conectar();
				PreparedStatement ps = con.prepareStatement(createDois);
				ps.setString(1, contato.getIdcontato());
				ResultSet rs= ps.executeQuery();
				while (rs.next()) {
					contato.setIdcontato(rs.getString(1));
					contato.setNome(rs.getString(2));
					contato.setFone(rs.getString(3));
					contato.setEmail(rs.getString(4));
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		//Editar Contato
		public void alterarContato (JavaBeans contato) {
			String createTres = "update contatos set nome=?, fone=?, email=? where idcontato=?";
			try {
				Connection con = conectar();
				PreparedStatement ps = con.prepareStatement(createTres);
				ps.setString(1, contato.getNome());
				ps.setString(2, contato.getFone());
				ps.setString(3, contato.getEmail());
				ps.setString(4, contato.getIdcontato());
				ps.executeUpdate();
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		//CRUD DELETAR 
		
		public void deletarContato (JavaBeans contato) {
			String createQuatro = "delete from contatos where idcontato=?";
			try {
				Connection con = conectar();
				PreparedStatement ps = con.prepareStatement(createQuatro);
				ps.setString(1, contato.getIdcontato());
				ps.executeUpdate();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
	
}


	

