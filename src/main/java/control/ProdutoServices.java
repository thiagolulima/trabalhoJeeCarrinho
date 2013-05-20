/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Produto;

/**
 *
 * @author THIAGO
 */
@Stateless
public class ProdutoServices {

     @PersistenceContext
  private EntityManager em;

  @SuppressWarnings("unchecked")
  public List<Produto> findAll() {
    return em.createQuery("SELECT p FROM Produto p ORDER BY p.titulo")
        .getResultList();
  }
    public void salvarProduto(Produto produto){
       em.merge(produto);
    }
    

}
