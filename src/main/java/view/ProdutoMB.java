/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ProdutoServices;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import model.Produto;

/**
 *
 * @author THIAGO
 */
@Named
@RequestScoped
@ManagedBean
public class ProdutoMB {
    
    private Produto produto = new Produto();
    @Inject
    private ProdutoServices produtoServices;
    private List<Produto> produtos;

    @PostConstruct
     public void init() {
    produtos = produtoServices.findAll();
    }

    public List<Produto> getProdutos() {
    return produtos;
  }
    
    public void salvarProduto(){
        produtoServices.salvarProduto(produto);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
