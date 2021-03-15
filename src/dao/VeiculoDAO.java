package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.VeiculoBeans;
import connection.SingleConnection;
import exception.DbException;
import exception.DbIntegrityException;

public class VeiculoDAO {

	
	
	public void registrarVeiculo(VeiculoBeans veiculobeans) {
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement("insert into carro (modelo,placa,cor,valor)  values (?,?,?,?) ");
			ps.setString(1, veiculobeans.getModelo());
			ps.setString(2, veiculobeans.getPlaca());
			ps.setString(3, veiculobeans.getCor());
			ps.setString(4, veiculobeans.getValor());
			ps.executeUpdate();
			SingleConnection.closeStatement(ps);
			SingleConnection.closeConnection(con);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public ArrayList<VeiculoBeans> listar(){
		ArrayList<VeiculoBeans> list =  new ArrayList<>();
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement("select  * from  carro order by id");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String modelo = rs.getString(2);
				String placa = rs.getString(3);
				String cor = rs.getString(4);
				String vlaor = rs.getString(5);
				list.add(new VeiculoBeans(id,modelo,placa,cor,vlaor));
			}
			SingleConnection.closeStatement(ps);
			SingleConnection.closeResultSet(rs);
			SingleConnection.closeConnection(con);
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public void deletarVeiculo(VeiculoBeans veiculobeans) {
		
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement("delete from carro where ID = ?");
			ps.setString(1, veiculobeans.getId());
			ps.executeUpdate();
			SingleConnection.closeStatement(ps);
			SingleConnection.closeConnection(con);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
	}
	
	
	public void selecionarVeiculo(VeiculoBeans veiculobeans) {
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement("select * from carro where id = ?");
			ps.setString(1, veiculobeans.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				veiculobeans.setId(rs.getString(1));
				veiculobeans.setModelo(rs.getString(2));
				veiculobeans.setPlaca(rs.getString(3));
				veiculobeans.setCor(rs.getString(4));
				veiculobeans.setValor(rs.getString(5));
			}
			SingleConnection.closeStatement(ps);
			SingleConnection.closeResultSet(rs);
			SingleConnection.closeConnection(con);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public void atualizarVeiculo(VeiculoBeans veiculobeans) {
		try {
			Connection con = SingleConnection.conectar();
			PreparedStatement ps = con.prepareStatement("UPDATE carro SET valor = ? WHERE ID = ?");
			ps.setString(1, veiculobeans.getValor());
			ps.setString(2, veiculobeans.getId());
			ps.executeUpdate();
			SingleConnection.closeStatement(ps);
			SingleConnection.closeConnection(con);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
