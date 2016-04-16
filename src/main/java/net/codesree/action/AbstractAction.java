package net.codesree.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractAction implements IAction {

  protected boolean forward,include,validationSuccess;
  protected String retUrl=null;
  
  protected ServletContext context=null;
  protected HttpSession session=null;
  protected HttpServletRequest req=null;
  protected HttpServletResponse res=null;
  
  public AbstractAction() {
   this.validationSuccess=false;
   this.forward=false;
   this.include=false;
  }
  
  @Override
  public String execute(HttpServletRequest _req,HttpServletResponse _res)
    throws Exception {
    this.req=_req;
    this.context=this.req.getServletContext();
    this.res=_res;
    this.session=this.req.getSession(false);
    mapBean();
    validate();
    if(validationSuccess) {
     process();
    }
    return retUrl;    
  }
  @Override  
  public boolean isTobeForward() { return this.forward; }
  @Override
  public boolean isTobeInclude() { return this.include; }
  
  protected void setTobeForward() {
   this.include=false;
   this.forward=true;
  }
  
  protected void setTobeInclude() {
   this.include=true;
   this.forward=false;
  }
  
  protected abstract void mapBean();
  protected abstract void validate();
  protected abstract void process();
}