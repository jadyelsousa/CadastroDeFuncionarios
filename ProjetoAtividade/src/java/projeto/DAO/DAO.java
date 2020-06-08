
package projeto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.tabelas.Cargo;
import projeto.conexao.Conexaobanco;
import projeto.tabelas.Endereco;
import projeto.tabelas.Funcionario;

public class DAO {
    
    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public void InsertCargo(Cargo car){
        sql="insert into cargo (nome,descricao) values (?,?)";
        con=Conexaobanco.conectar();
        try{
        ps=con.prepareStatement(sql);
        ps.setString(1, car.getNome());
        ps.setString(2, car.getDescricao());
        ps.execute();
        
        }catch(SQLException ex){
            System.out.println("Erro ao inserir dados!");
        }
        
    
    }
        
        public ArrayList<Cargo> selectAll(){
        ArrayList<Cargo> lista = new ArrayList<>();
        Cargo car;
        sql = "Select * from cargo ";
        con = Conexaobanco.conectar();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                car = new Cargo();
                car.setNome(rs.getString("nome"));
                car.setDescricao(rs.getString("descricao"));
                car.setId(rs.getInt("id_cargo"));
                lista.add(car);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
   
    }
        public void insertFuncionario(Funcionario fun){
        sql="insert into funcionario (nome,cpf,dt_nascimento,telefone,email,id_cargo)"
                + "values(?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?)";
        con=Conexaobanco.conectar();
        try{
        ps = con.prepareStatement(sql);
        ps.setString(1, fun.getNome());
        ps.setString(2, fun.getCpf().replace(".", "").replace("-", ""));
        ps.setString(3,fun.getDt_nascimento());
        ps.setString(4, fun.getTelefone());
        ps.setString(5, fun.getEmail());
        ps.setInt(6, fun.getCargo().getId());
        ps.execute();
        
        sql="insert into endereco (rua,complemento,numero,bairro,cidade,estado,cpf)"
                + "values(?,?,?,?,?,?,?)";
        
        ps=con.prepareStatement(sql);
        ps.setString(1, fun.getEndereco().getRua());
        ps.setString(2, fun.getEndereco().getComplemento());
        ps.setString(3, fun.getEndereco().getNumero());
        ps.setString(4, fun.getEndereco().getBairro());
        ps.setString(5, fun.getEndereco().getCidade());
        ps.setString(6, fun.getEndereco().getEstado());
        ps.setString(7, fun.getCpf().replace(".", "").replace("-", ""));
        ps.execute();
        System.out.println("Certo");
        
        }catch (SQLException ex) {
            System.out.println("erro");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        
        public  ArrayList<Funcionario> selecionarFuncionarios(){
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        Funcionario fun;
        Cargo car;
        
        sql="select *,DATE_FORMAT(dt_nascimento,'%d/%m/%Y') as data,"
                + "c.nome as nome_cargo from funcionario as f inner join cargo as c on c.id_cargo=f.id_cargo";
        con=Conexaobanco.conectar();
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                fun=new Funcionario();
                car=new Cargo();
                fun.setCargo(car);
                fun.setCpf(rs.getString("cpf"));
                fun.setNome(rs.getString("nome"));
                fun.setEmail(rs.getString("email"));
                fun.getCargo().setDescricao(rs.getString("descricao"));
                fun.getCargo().setNome(rs.getString("nome_cargo"));
                fun.setDt_nascimento(rs.getString("data"));
                lista.add(fun);
            }
        }catch (SQLException ex) {
            System.out.println("erro");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return lista;
        }
    
}
