package net.codesree.web;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codesree.action.IAction;
import net.codesree.action.HomeAction;
import net.codesree.action.LoginAction;
import net.codesree.action.LogoutAction;

public class FrontController extends HttpServlet {

  private Map<String,IAction> actionMap=null;
  
  public void init() {
   this.actionMap=new HashMap<>();
   this.actionMap.put("index",new LoginAction());
   this.actionMap.put("home",new HomeAction());
   this.actionMap.put("login",new LogoutAction());
  }
  
  public void service(HttpServletRequest req,HttpServletResponse res) 
    throws IOException, ServletException {
    String retUrl=null;
    IAction action=getAction(req);
    if(action==null) {
      action=new HomeAction();   //default action
    }
    retUrl=action.execute(req,res);
    if(action.isTobeForward()) {
      forward(req,res,retUrl);
    } else if(action.isTobeInclude()) {
      include(req,res,retUrl);
    } else {
      redirect(res,retUrl);
    }
  }
  
  public void destroy() {
    this.actionMap.clear();
  }
  
  private IAction getAction(HttpServletRequest req) throws Exception {}
  
  private void redirect(HttpServletResponse res,String url) {
    res.sendRedirect(url);
  }
  
  private void forward(HttpServletRequest req,HttpServletResponse res,String url) {
    req.getRequestDispatcher(url).forward(req,res);
  }
  
  private void include(HttpServletRequest req,HttpServletResponse res,String pageLoc) {
    req.getRequestDispatcher(pageLoc).include(req,res);
  }
}