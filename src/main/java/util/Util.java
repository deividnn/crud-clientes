package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DeividnN classe utilitaria
 */
public class Util {

    public static Session pegarSessao() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * *
     * cria uma mensagem do facesmessage, do tipo warning
     *
     * @param texto
     */
    public static void criarMensagemAviso(String texto) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, texto);
        FacesContext.getCurrentInstance().addMessage(texto, msg);
    }

    
     /**
     * *
     * limpa valores do componente
     *
     * @param id
     */
    public static void resetarComponente(String id) {
        RequestContext.getCurrentInstance().reset(id);
    }
}
