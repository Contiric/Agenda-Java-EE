package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.DAO;
import Model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/cadastro", "/select", "/update", 
		"/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de conexão
		// dao.testeConexão();
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/cadastro")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		}  else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			excluirContato(request, response); 
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);  
		} else {
			response.sendRedirect("index.html");
		}

	}
	//ListarContatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listaContatos();
		//Teste de recebimento da lista 
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getIdcontato());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//			System.out.println(lista.get(i).getEmail());
//
//		}
		
		//Encaminhar lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	//Adicionar novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Teste de reconhecimento dos dados do formulário
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
		
		//Setar as variáveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//Invocar o método inserirContato passando como argumento o objeto contato
		dao.inserirContato(contato);
		//Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}
	
	//Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//Recebimento do id do contato que será editado
			String idcon = request.getParameter("idcontato");
			//System.out.println(idcon);
			
			//Setar a variável JavaBeans
			contato.setIdcontato(idcon);
			
			//Executar o método selecionar contato (DAO)
			dao.selecionarContato(contato);
			
			//Teste de recebimento
//			System.out.println(contato.getIdcontato());
//			System.out.println(contato.getNome());
//			System.out.println(contato.getFone());
//			System.out.println(contato.getEmail());	
			
			//Setar os atributos do formulário com o conteúdo JavaBeans
			request.setAttribute("idcontato", contato.getIdcontato());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("fone", contato.getFone());
			request.setAttribute("email", contato.getEmail());
			
			//Encaminhar dados ao documento editar.jsp
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);

	}
	
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		//Verificar recebimento
//		System.out.println(request.getParameter("idcontato"));
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
		
		contato.setIdcontato(request.getParameter("idcontato"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//Executar o método alterar contato
		dao.alterarContato(contato);
		
		//Redirecionar para o documeto agenda.jsp (Atualizado as alterações)
		
		response.sendRedirect("main");
	
	}
	protected void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String id = request.getParameter("idcontato");
//		System.out.println(id);
		
		contato.setIdcontato(request.getParameter("idcontato"));
		dao.deletarContato(contato);
		response.sendRedirect("main");
	
	}
	
	//Gerar relatório PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Document doc = new Document();
		try {
			//Tipo de conteúdo
			response.setContentType("application/pdf");
			//Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			//Criar Documento
			PdfWriter.getInstance(doc, response.getOutputStream());
			//Abrir documento -> gerar conteúdo
			doc.open();
			doc.add(new Paragraph ("Lista de Contatos:"));
			doc.add(new Paragraph (" "));
			//Criar uma Tabela
			PdfPTable tabela = new PdfPTable(3);
			//Criar cabeçalho
			PdfPCell col1 = new PdfPCell (new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell (new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell (new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listaContatos();
			for (int i=0; i <lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());				
			}
			doc.add(tabela);
			doc.close();
		} catch (Exception e) {
			System.out.println(e);
			doc.close();
		}
		
	}
	
}
