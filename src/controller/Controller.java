package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBeans;
import beans.VeiculoBeans;
import dao.UserDAO;
import dao.VeiculoDAO;

@WebServlet(urlPatterns = { "/Contorller", "/login", "/cadastrousuario", "/registroVeiculo", "/enviarlistarAdmin",
		"/listarVeiculosUsuario", "/delete" ,"/select","/atualizarVeiculo","/alugar"})
public class Controller extends HttpServlet {


	private static final long serialVersionUID = -4626013876839759204L;

	UserBeans userbeans = new UserBeans();
	UserDAO userdao = new UserDAO();
	VeiculoBeans veiculobeans = new VeiculoBeans();
	VeiculoDAO veiculodao = new VeiculoDAO();

	public Controller() {
		super();

	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		if (url.equals("/cadastrousuario")) {
			registrarUsuario(request, response);
		} 
		else if (url.equals("/login")) {
			validarLogin(request, response);

		} 
		else if (url.equals("/registroVeiculo")) {
			registrarVeiculo(request, response);
		}
		else if (url.equals("/enviarlistarAdmin")) {
			listarVeiculosAdmin(request, response);
		} 
		else if (url.equals("/listarVeiculosUsuario")) {
			listarVeiculosUsuario(request, response);
		} 
		else if (url.equals("/delete")) {
			deletarVeiculo(request, response);
			listarVeiculosAdmin(request, response);
		}
		else if(url.equals("/select")) {
			selecionarVeiculo(request, response);
		}
		
		else if(url.equals("/atualizarVeiculo")) {
			atualizarVeiculo(request, response);
			listarVeiculosAdmin(request, response);
		}
		else if(url.equals("/alugar")) {
			response.sendRedirect("sucesso.html");
		}
		
		else {
			response.sendRedirect("login.html");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.process(request, response);
	}

	protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		userbeans.setNome(request.getParameter("nomeCadastro"));
		userbeans.setUsuario(request.getParameter("usuarioCadastro"));
		userbeans.setSenha(request.getParameter("senhaCadastro"));
		userbeans.setEmail(request.getParameter("emailCadastro"));
		userdao.RegistrarUsuario(userbeans);
		response.sendRedirect("login.html");

	}

	protected void validarLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("usuarioLogin");
		String senha = request.getParameter("senhaLogin");

		if (userdao.validarAdmin(nome, senha) == true) {
			RequestDispatcher rd = request.getRequestDispatcher("menuAdmin.html");
			rd.forward(request, response);
		} else if (userdao.validarUsuarioNormal(nome, senha) == true) {
			RequestDispatcher rd = request.getRequestDispatcher("menuUsuario.html");
			rd.forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}

	}

	protected void registrarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		veiculobeans.setModelo(request.getParameter("modeloCadastroVeiculo"));
		veiculobeans.setPlaca(request.getParameter("placaCadastroVeiculo"));
		veiculobeans.setCor(request.getParameter("corCadastroVeiculo"));
		veiculobeans.setValor(request.getParameter("valorCadastroVeiculo"));
		veiculodao.registrarVeiculo(veiculobeans);
		response.sendRedirect("menuAdmin.html");

	}

	protected void listarVeiculosAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<VeiculoBeans> list = veiculodao.listar();
		request.setAttribute("registros", list);
		RequestDispatcher rd = request.getRequestDispatcher("listarVeiculosAdmin.jsp");
		rd.forward(request, response);

	}
	
	
	protected void listarVeiculosUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<VeiculoBeans> list = veiculodao.listar();
		request.setAttribute("registros", list);
		RequestDispatcher rd = request.getRequestDispatcher("listarVeiculosUsuario.jsp");
		rd.forward(request, response);

	}
	

	protected void deletarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		veiculobeans.setId(request.getParameter("id"));
		veiculodao.deletarVeiculo(veiculobeans);

	}

	protected void selecionarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		veiculobeans.setId(request.getParameter("id"));
		veiculodao.selecionarVeiculo(veiculobeans);

		System.out.println(veiculobeans.getId());
		System.out.println(veiculobeans.getModelo());
		System.out.println(veiculobeans.getPlaca());
		System.out.println(veiculobeans.getCor());
		System.out.println(veiculobeans.getValor());
		
		
		request.setAttribute("id", veiculobeans.getId());
		request.setAttribute("modelo", veiculobeans.getModelo());
		request.setAttribute("placa", veiculobeans.getPlaca());
		request.setAttribute("cor", veiculobeans.getCor());
		request.setAttribute("valor", veiculobeans.getValor());
		
		RequestDispatcher rd = request.getRequestDispatcher("editarVeiculo.jsp");
		rd.forward(request, response);

	}

	
	protected void atualizarVeiculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		veiculobeans.setId(request.getParameter("id"));
		veiculobeans.setValor(request.getParameter("valor"));
		veiculodao.atualizarVeiculo(veiculobeans);

	}

}
