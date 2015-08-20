/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Cliente;
import java.util.List;
import util.HibernateDAO;
import util.Util;

/**
 *
 * @author DeividnN classe que contem as regras de negocios do cliente
 */
public class ClienteController {

    //dao de persistencia
    private final HibernateDAO dao;

    public ClienteController() {
        this.dao = new HibernateDAO();
    }

    /**
     * *
     *
     * @param cliente salva um cliente
     * @return
     */
    public boolean salvar(Cliente cliente) {
        try {
            this.dao.salvar(cliente);
            return true;
        } catch (Exception e) {
            Util.criarMensagemAviso(e.toString());
            return false;
        }
    }

    /**
     * *
     *
     * @param cliente atualiza um cliente
     * @return
     */
    public boolean atualizar(Cliente cliente) {
        try {
            this.dao.atualizar(cliente);
            return true;
        } catch (Exception e) {
            Util.criarMensagemAviso(e.toString());
            return false;
        }
    }

    /**
     * *
     *
     * @param cliente exclui um cliente
     * @return
     */
    public boolean excluir(Cliente cliente) {
        try {
            this.dao.excluir(cliente);
            return true;
        } catch (Exception e) {
            Util.criarMensagemAviso(e.toString());
            return false;
        }
    }

    /**
     * *
     *
     * @param hql lista os clientes
     * @return
     */
    public List<Cliente> listar(String hql) {
        return this.dao.listar(hql);
    }

    /**
     *
     * @param hql carrega um cliente especifico
     * @return
     */
    public Cliente carregar(String hql) {
        return (Cliente) this.dao.carregar(hql);
    }
}
