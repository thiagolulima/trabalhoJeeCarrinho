/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.Cliente;

/**
 *
 * @author THIAGO
 */
@Stateless
public class ClienteServices {

    @PersistenceContext
  private EntityManager em;

  public Cliente findByEmailAndSenha(String email, String senha) {
    Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha");
    query.setParameter("email", email);
    query.setParameter("senha", senha);
    try {
      return (Cliente) query.getSingleResult();
    }
    catch (NoResultException e) {
      return null;
    }
  }

  public Cliente adicionar(Cliente cliente) throws ClienteExistenteException {
    try {
      em.persist(cliente);
      return cliente;
    }
    catch (PersistenceException e) {
      throw new ClienteExistenteException();
    }
  }
    public void salvarCliente(Cliente cliente){
        em.merge(cliente);
    }

}
