package com.kulchuri.bill.student;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import com.kulchuri.bill.course.CourseDao;
import com.kulchuri.bill.course.CourseDto;
import com.kulchuri.bill.db.BillDb;

public class StudentDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = null;

	public boolean addStudent(StudentDto dto, InputStream photo) {
		boolean flag = false;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			String sql = "insert into student_master(bid,cid,name,email,mno,"
					+ "gender,address,photo,paid,balance,doj) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			float fees = new CourseDao().getFees(dto.getCid());
			float balance = fees - dto.getPaid();
			ps.setInt(1, dto.getBid());
			ps.setInt(2, dto.getCid());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getMno());
			ps.setString(6, dto.getGender());
			ps.setString(7, dto.getAddress());
			ps.setBlob(8, photo);
			ps.setFloat(9, dto.getPaid());
			ps.setFloat(10, balance);
			ps.setString(11, "");
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception at addStudent():" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public boolean updateStudent(StudentDto dto) {
		boolean flag = false;
		try {
			if (conn == null) {
				conn = BillDb.getBillDb();
			}
			String sql = "update student_master set name=?,address=?,mno=?,email=?,doj=?,cid=?,bid=?,paid=?,balance=? where sid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getAddress());
			ps.setString(3, dto.getMno());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getDoj());
			ps.setInt(6, dto.getCid());
			ps.setInt(7, dto.getBid());
			ps.setFloat(8, dto.getBalance());
			ps.setFloat(9, dto.getPaid());
			ps.setInt(10, dto.getSid());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception at updateStudent :" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public boolean deleteStudent(int sid) {
		boolean flag = false;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			String sql = "delete from student_master where sid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception at deleteStudent :" + e);
		} finally {
			ps = null;
			conn = null;
			return flag;
		}
	}

	public StudentDto getStudent(int sid) {
		StudentDto dto = null;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			String sql = "select * from student_master where sid =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new StudentDto();
				dto.setSid(rs.getInt("sid"));
				dto.setName(rs.getString("name"));
				dto.setMno(rs.getString("mno"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setDoj(rs.getString("doj"));
				dto.setCid(rs.getInt("cid"));
				dto.setBid(rs.getInt("bid"));
				dto.setPaid(rs.getFloat("paid"));
				dto.setBalance(rs.getFloat("balance"));
				dto.setGender(rs.getString("gender"));
				dto.setPhoto(Base64.getEncoder().encodeToString(rs.getBytes("photo")));

			}
		} catch (Exception e) {
			System.out.println("Exception at getStudent :" + e);
		} finally {
			rs = null;
			ps = null;
			conn = null;
			return dto;
		}
	}

	public ArrayList<StudentDto> getAllStudents() {
		ArrayList<StudentDto> list = new ArrayList<>();
		StudentDto dto = null;
		if (conn == null) {
			conn = BillDb.getBillDb();
		}
		try {
			ps = conn.prepareStatement("select *from student_master");
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new StudentDto();
				dto.setAddress(rs.getString("address"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setBalance(rs.getFloat("balance"));
				dto.setPaid(rs.getFloat("paid"));
				dto.setDoj(rs.getString("doj"));
				dto.setPhoto(Base64.getEncoder().encodeToString(rs.getBytes("photo")));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("Exception at getAllStudents():" + e);
		} finally {
			if (list.isEmpty()) {
				list = null;
			}
			ps = null;
			rs = null;
			conn = null;
			return list;
		}
	}
}
