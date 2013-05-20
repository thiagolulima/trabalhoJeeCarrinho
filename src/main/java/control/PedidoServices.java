/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Cliente;
import model.Pedido;

/**
 *
 * @author THIAGO
 */
@Stateless
public class PedidoServices {

  @PersistenceContext
  private EntityManager em;
  @Inject
  private ClienteServices clienteServices;

  public Pedido criarPedido(Pedido pedido, String emailCliente,
      String senhaCliente) throws ClienteNaoEncontradoException
  {
    Cliente cliente = clienteServices.findByEmailAndSenha(emailCliente,
        senhaCliente);
    if (cliente == null) { throw new ClienteNaoEncontradoException(); }
    pedido.setData(new Date());
    pedido.setCliente(cliente);
    em.persist(pedido);
    return pedido;
  }

}
