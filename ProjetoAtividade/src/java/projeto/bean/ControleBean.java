
package projeto.bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import projeto.tabelas.Cargo;
import projeto.tabelas.Endereco;
import projeto.tabelas.Funcionario;
import projeto.DAO.DAO;

@SessionScoped
@ManagedBean(name="bean")
public class ControleBean {
    
    private Cargo car;
    private Funcionario fun;
    private Endereco end;
    private ArrayList<Funcionario> funList;
    private ArrayList<Cargo> carList;
    private DAO dao;
    
    public ControleBean(){
    car = new Cargo();
    fun = new Funcionario();
    end = new Endereco();
    funList = new ArrayList<>();
    dao = new DAO();
    carList = dao.selectAll();
    funList = dao.selecionarFuncionarios();
    }

    public Cargo getCar() {
        return car;
    }

    public void setCar(Cargo car) {
        this.car = car;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    public ArrayList<Funcionario> getFunList() {
        return funList;
    }

    public void setFunList(ArrayList<Funcionario> funList) {
        this.funList = funList;
    }

    public ArrayList<Cargo> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<Cargo> carList) {
        this.carList = carList;
    }
    public void inserirCargo(){
        dao.InsertCargo(car);
        car = new Cargo();
        carList = dao.selectAll();
    }
    public void inserirFuncionario(){
        dao.insertFuncionario(fun);
        fun=new Funcionario();

   }
    public String listaFuncionarios(){
       funList = dao.selecionarFuncionarios();
       return "listaFuncionario.xhtml";

    
    }
    public String cadFuncionario(){
        carList = dao.selectAll();
        return "cadastroFuncionario.xhtml";
    }
    
}