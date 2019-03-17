package com.kulchuri.bill.accountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kulchuri.bill.db.BillDb;

public class AccountantDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public boolean addAccountant(AccountantDto dto) {
		boolean flag = false;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			ps = conn.prepareStatement(
					"insert into accountant_master(name, uname, pwd, mno, bid, doj, salary, email, address,gender) values(?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getUname());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getMno());
			ps.setInt(5, dto.getBid());
			ps.setString(6, "");
			ps.setFloat(7, dto.getSalary());
			ps.setString(8, dto.getEmail());
			ps.setString(9, dto.getAddress());
			ps.setString(10, dto.getGender());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception at addAccountant():" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public boolean updateAccountant(AccountantDto dto) {
		boolean flag = false;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {

			ps = conn.prepareStatement(
					"update accountant_master set name=?, uname=?,mno=?, bid=?, doj=?, salary=?, email=?, address=?,gender=? where aid=?");
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getUname());
			ps.setString(3, dto.getMno());
			ps.setInt(4, dto.getBid());
			ps.setString(5, dto.getDoj());
			ps.setFloat(6, dto.getSalary());
			ps.setString(7, dto.getEmail());
			ps.setString(8, dto.getAddress());
			ps.setString(9, dto.getGender());
			ps.setInt(10, dto.getAid());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println("Exception at updateAccountant():" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public boolean deleteAccountant(int aid) {
		boolean flag = false;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			ps = conn.prepareStatement("delete from accountant_master where aid=?");
			ps.setInt(1, aid);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception at deleteAccountant():" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public ArrayList<AccountantDto> getAllAccountants() {
		ArrayList<AccountantDto> al = new ArrayList<>();
		AccountantDto dto;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			rs = conn.prepareStatement("SELECT * FROM accountant_master").executeQuery();
			while (rs.next()) {
				dto = new AccountantDto();
				dto.setAid(rs.getInt("aid"));
				dto.setName(rs.getString("name"));
				dto.setUname(rs.getString("uname"));
				dto.setMno(rs.getString("mno"));
				dto.setDoj(rs.getString("doj"));
				dto.setSalary(rs.getFloat("salary"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
				al.add(dto);
			}
		} catch (Exception e) {
			System.out.println("Exception at getAllAccountants():" + e);
		} finally {
			if (al.isEmpty()) {
				al = null;
			}
			rs = null;
			conn = null;
			return al;
		}
	}

	public ArrayList<AccountantDto> getAllAccountantsByBranch(int bid) {
		ArrayList<AccountantDto> al = new ArrayList<>();
		AccountantDto dto;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			ps = conn.prepareStatement(
					"select am.*, bm.name as bname from accountant_master am, branch_master bm where am.bid=? and am.bid=bm.bid");
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new AccountantDto();
				dto.setAid(rs.getInt("aid"));
				dto.setName(rs.getString("name"));
				dto.setUname(rs.getString("uname"));
				dto.setBid(rs.getInt("bid"));
				dto.setBname(rs.getString("bname"));
				dto.setMno(rs.getString("mno"));
				dto.setDoj(rs.getString("doj"));
				dto.setSalary(rs.getFloat("salary"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
				al.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception at getAllAccountantsByBranch():" + e);
		} finally {
			if (al.isEmpty()) {
				al = null;
			}
			rs = null;
			conn = null;
			return al;
		}
	}

	public AccountantDto getAccountant(int aid) {
		AccountantDto dto = null;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			ps = conn.prepareStatement("select am.*, bm.name as bname from accountant_master am, branch_master bm where am.aid=? and am.bid=bm.bid");
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new AccountantDto();
				dto.setAid(rs.getInt("aid"));
				dto.setName(rs.getString("name"));
				dto.setUname(rs.getString("uname"));
				dto.setBid(rs.getInt("bid"));
				dto.setBname(rs.getString("bname"));
				dto.setMno(rs.getString("mno"));
				dto.setDoj(rs.getString("doj"));
				dto.setSalary(rs.getFloat("salary"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
			}
		} catch (Exception e) {
			System.out.println("Exception at getAccountant():" + e);
		} finally {
			rs = null;
			conn = null;
			return dto;
		}
	}
}
