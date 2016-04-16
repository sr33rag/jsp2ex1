package net.codesree.action;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction extends Serializable {
  /**
  * The method which is called by the controller
  * @param req HttpServletRequest object
  * @param res HttpServletResponse object
  * @return The url to which the result is to be shown.
  **/
  String execute(HttpServletRequest req,HttpServletResponse res)
    throws Exception;
  boolean isTobeForward();
  boolean isTobeInclude();
}