/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ClienteExistenteException;
import control.ClienteNaoEncontradoException;
import control.ClienteServices;
import control.PedidoServices;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

/**
 *
 * @author THIAGO
 */
@Named
@SessionScoped
@ManagedBean
public class CarrinhoDeComprasMB implements Serializable {

  private static final long serialVersionUID = 1172514592649335124L;
  private Pedido pedidoCarrinho;
  private Long idPedidoGerado;
  private Cliente cliente;
  private Produto produtoRemover;
  @Inject
  private transient ClienteServices clienteServices;
  @Inject
  private transient PedidoServices pedidoServices;
  @Inject
  private transient UtilsMB utilsMB;

  @PostConstruct
  public void init() {
    pedidoCarrinho = new Pedido();
    cliente = new Cliente();
  }

  public String adicionarItem(Produto produto) {
    pedidoCarrinho.adicionarItem(produto, 1);
    return "carrinho?faces-redirect=true";
  }

  public void removerItem() {
    pedidoCarrinho.removerItem(produtoRemover);
  }

  public void atualizarQuantidadeItem(Produto produto, Integer novaQuantidade) {
    pedidoCarrinho.atualizarQuantidade(produto, novaQuantidade);
  }

  public String fecharPedidoUsuarioExistente() {
    return fecharPedido();
  }

  public String fecharPedidoNovoUsuario() {
    try {
      cliente = clienteServices.adicionar(cliente);
    }
    catch (ClienteExistenteException e) {
      adicionarMensagem(FacesMessage.SEVERITY_ERROR, "cliente-existente");
      return null;
    }
    return fecharPedido();
  }

  private String fecharPedido() {
    try {
      pedidoCarrinho = pedidoServices.criarPedido(pedidoCarrinho,
          cliente.getEmail(), cliente.getSenha());
      idPedidoGerado = pedidoCarrinho.getId();
      init();
      return "pedidoFechado?faces-redirect=true";
    }
    catch (ClienteNaoEncontradoException e) {
      adicionarMensagem(FacesMessage.SEVERITY_ERROR, "cliente-nao-encontrado");
      return null;
    }
  }

  private void adicionarMensagem(FacesMessage.Severity severidade, String chave)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null,
        new FacesMessage(severidade, utilsMB.getMessage(chave), null));
  }

  public void recalcularTotal(ItemPedido itemPedido) {
    itemPedido.calcularTotal();
    pedidoCarrinho.calcularTotal();
  }

  public boolean temItens() {
    return pedidoCarrinho.getItens().size() > 0;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public Long getIdPedidoGerado() {
    return idPedidoGerado;
  }

  public Pedido getPedidoCarrinho() {
    return pedidoCarrinho;
  }

  public Produto getProdutoRemover() {
    return produtoRemover;
  }

  public void setProdutoRemover(Produto produtoRemover) {
    this.produtoRemover = produtoRemover;
  }
    
}
