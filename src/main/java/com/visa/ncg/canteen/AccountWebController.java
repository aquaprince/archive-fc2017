package com.visa.ncg.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountWebController {

  @GetMapping("/account/{id}")
  public String accountView(@PathVariable("id") String id,
                            Model model) {
    // return name of a view
    return "account-view";
  }

}
