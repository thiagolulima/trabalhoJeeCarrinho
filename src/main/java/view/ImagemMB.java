/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author THIAGO
 */
@Named
@ApplicationScoped
@ManagedBean
public class ImagemMB {
    
    private static final String PATH_IMAGES = "/path/to/images";
    public StreamedContent getImage() throws IOException {
    FacesContext context = FacesContext.getCurrentInstance();
    DefaultStreamedContent content = new DefaultStreamedContent();
    content.setContentType("image/jpg");
    if (context.getRenderResponse()) {
      return content;
    }
    else {
      String name = context.getExternalContext().getRequestParameterMap().get("name");
      content.setStream(new FileInputStream(PATH_IMAGES + name));
      return content;
    }
  }
}
