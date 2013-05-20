/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author THIAGO
 */
@Named
@ApplicationScoped
public class UtilsMB {

       private ResourceBundle bundle;

  @PostConstruct
  public void init() {
    this.bundle = ResourceBundle.getBundle("messages", FacesContext
        .getCurrentInstance().getViewRoot().getLocale());
  }

  public String getMessage(String chave) {
    String message = null;
    try {
      message = bundle.getString(chave);
    }
    catch (MissingResourceException e) {
      return "?? chave " + chave + " inexistente ??";
    }
    return message;
  }
}
