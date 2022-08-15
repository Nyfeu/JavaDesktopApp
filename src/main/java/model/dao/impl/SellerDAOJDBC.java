package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement("INSERT INTO seller\n" +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1,seller.getName());
            st.setString(2,seller.getEmail());
            st.setDate(3,new Date(seller.getBirthDate().getTime()));
            st.setDouble(4,seller.getBaseSalary());
            st.setInt(5,seller.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {

                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    seller.setId(id);
                }
                DB.closeResultSet(rs);

            } else throw new DBException("Unexpected Error! No rows affected!");


        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);

        }


    }

    @Override
    public void update(Seller seller) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement("UPDATE seller\n" +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?\n" +
                            "WHERE Id = ?");

            st.setString(1,seller.getName());
            st.setString(2,seller.getEmail());
            st.setDate(3,new Date(seller.getBirthDate().getTime()));
            st.setDouble(4,seller.getBaseSalary());
            st.setInt(5,seller.getDepartment().getId());
            st.setInt(6,seller.getId());

            st.executeUpdate();

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement("DELETE FROM seller\n" +
                    "WHERE Id = ?");

            st.setInt(1,id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                throw new DBException("Invalid Seller Id! No data deleted!");
            }


        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);

        }

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                                           "FROM seller INNER JOIN department\n" +
                                           "ON seller.DepartmentId = department.Id\n" +
                                           "WHERE seller.Id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()) {

                Department department = instantiateDepartment(rs);
                return instantiateSeller(rs, department);

            }
            return null;

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }

    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {

            Department department = new Department();
            department.setId(rs.getInt("DepartmentId"));
            department.setName(rs.getString("DepName"));
            return department;

    }

    private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {

            Seller obj = new Seller();
            obj.setId(rs.getInt("Id"));
            obj.setName(rs.getString("Name"));
            obj.setEmail(rs.getString("Email"));
            obj.setBirthDate(rs.getDate("BirthDate"));
            obj.setBaseSalary(rs.getDouble("BaseSalary"));
            obj.setDepartment(department);
            return obj;

    }


    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "ORDER BY Name");

            rs = st.executeQuery();
            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                sellerList.add(instantiateSeller(rs, dep));

            }

            return sellerList;

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE DepartmentId = ?\n" +
                    "ORDER BY Name");

            st.setInt(1,department.getId());
            rs = st.executeQuery();
            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                sellerList.add(instantiateSeller(rs, dep));

            }

            return sellerList;

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        } finally {

            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }


}
