package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBeans;
import connection.SingleConnection;
import exception.DbException;

public class UserDAO {

	public void RegistrarUsuario(UserBeans userbeans) {
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con
					.prepareStatement("insert into usuario (nome,usuario,senha,email) values (?,?,?,?) ");
			ps.setString(1, userbeans.getNome());
			ps.setString(2, userbeans.getUsuario());
			ps.setString(3, userbeans.getSenha());
			ps.setString(4, userbeans.getEmail());
			ps.executeUpdate();
			SingleConnection.closeStatement(ps);
			SingleConnection.closeConnection(con);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public boolean validarUsuarioNormal(String usuario, String senha) {
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement(
					"select * from usuario where usuario = '" + usuario + "' and senha = '" + senha + "'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	public boolean validarAdmin(String usuario, String senha) {

		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement(
					"select * from usuario where usuario = '" + usuario + "' and senha = '" + senha + "'");
			ResultSet rs = ps.executeQuery();
			if (usuario.equals("admin") && senha.equals("123")) {
				rs.next();
				return true;
			}

			else {
				return false;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

}
