/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controladores.ClienteController;
import entidades.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;

/**
 *
 * @author DeividnN classe que se comunica com a pagina jsf
 */
//declara que a classe eh um bean gerenciado
@ManagedBean
//escopo do bean eh de visao, enquanto estiver na msm pagina os dados sao mantidos
@ViewScoped
public class ClienteBean implements Serializable {

    //cliente do formulario
    private Cliente cliente;
    //lista de clientes salvos e resultado da pesquisa
    private List<Cliente> lista;
    //coluna usado na pesquisa
    private String colunaBusca;
    //valor usado na pesquisa
    private String valorBusca;

    //metodo que eh chamado antes de mostrar a pagina
    @PostConstruct
    public void init() {
        //instancia um novo cliente
        this.cliente = new Cliente();
        //lista os clientes que estao na base de dados
        listarClientes();

    }

    //metodo que consulta a base de dados e retorna uma lista de clientes
    public void listarClientes() {
        //limpa a lista atual
        this.lista = new ArrayList<>();
        //consulta feita na base
        String hql = "select c from Cliente c order by c.id desc";
        //executa a consulta e armazena o resultado na lista
        this.lista = new ClienteController().listar(hql);
    }

    //metodo que decide se vai salvar ou atualizar um cliente
    public void salvarCliente() {
        //se o id do cliente for nulo ele salva senao ele apenas atualiza
        if (this.cliente.getId() == null) {
            salvar();
        } else {
            atualizar();
        }
    }

    //metodo que insere um novo cliente na base de dados
    public void salvar() {
        try {

            //se o cliente for inserido com sucesso
            if (new ClienteController().salvar(cliente)) {
                //mostrar msg para o usuario
                sucesso("cliente salvo com sucesso");
            }
        } catch (Exception e) {
            //se houver algum erro, a msg de erroa sera mostrada
            Util.criarMensagemAviso(e.toString());
        }
    }

    //metodo que atualiza um cliente existente
    public void atualizar() {
        try {
            //se atualizacao for feita com sucesso
            if (new ClienteController().atualizar(cliente)) {
                 //mostrar msg para o usuario
                sucesso("cliente alterado com sucesso");
            }
        } catch (Exception e) {
            Util.criarMensagemAviso(e.toString());
        }
    }

    
    //metodo que exclui um cliente existente da base
    public void excluir() {
        try {
            //se a exclusao for feita com sucesso
            if (new ClienteController().excluir(cliente)) {
                 //mostrar msg para o usuario
                sucesso("cliente excluido com sucesso");
            }
        } catch (Exception e) {
            Util.criarMensagemAviso(e.toString());
        }
    }

    //metodo que pesquisa clientes usando a coluna e o valor
    public void pesquisar() {
        //consulta de pesquisa
        String hql = "select c from Cliente c"
                + " where c." + colunaBusca + " like '" + valorBusca + "%'";
        //atualiza lista de clientes
        this.lista = new ClienteController().listar(hql);

    }

    //metodo que seta um cliente da lista para o formulario
    public void selecionar(Cliente cliente) {
        this.cliente = cliente;
        //atualiza o formulario com os dados do cliente selecionado
        Util.resetarComponente("form_cliente");
    }

    //metodo que limpa o formulario
    public void novo() {
        this.cliente = new Cliente();
        Util.resetarComponente("form_cliente");
    }

    //metodo que mostra a msg
    private void sucesso(String msg) {
        //mostrar msg para o usuario
        Util.criarMensagemAviso(msg);
        //atualiza a lista de clientes
        listarClientes();
        //limpa o formulario
        novo();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public String getColunaBusca() {
        return colunaBusca;
    }

    public void setColunaBusca(String colunaBusca) {
        this.colunaBusca = colunaBusca;
    }

    public String getValorBusca() {
        return valorBusca;
    }

    public void setValorBusca(String valorBusca) {
        this.valorBusca = valorBusca;
    }

}
