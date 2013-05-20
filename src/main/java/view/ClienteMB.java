/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ClienteExistenteException;
import control.ClienteServices;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import model.Cliente;

/**
 *
 * @author THIAGO
 */
@Named(value = "clienteMB")
@RequestScoped
@ManagedBean
public class ClienteMB {
  
       private Cliente cliente = new Cliente();
    @EJB
    private ClienteServices clienteServices;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void salvarCliente(){
        clienteServices.salvarCliente(cliente);
    }
       
}
