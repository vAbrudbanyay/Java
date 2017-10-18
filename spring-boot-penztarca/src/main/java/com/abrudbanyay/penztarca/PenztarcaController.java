package com.abrudbanyay.penztarca;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abrudbanyay.penztarca.*;;

@Controller
public class PenztarcaController {

	private Penztarca tarca = new Penztarca();


   @RequestMapping("/")
   public String index() {
      return "index";
   }
   
   @RequestMapping("/processForm")
	public String osszegBe(HttpServletRequest request, Model model){
		
		String result ="";
		int osszeg;
		// read param from the HTML form
		
		try{
			osszeg = (Math.abs(Integer.parseInt(request.getParameter("fizetendoOsszeg"))));
			result = tarca.fizet(osszeg);
			model.addAttribute("message", result);
		} catch(NumberFormatException e){
			result = "A megadott összeg csak szám lehet!";
			model.addAttribute("message", result);
		}
		
		// add message to model
		
		return "index";
	}
	
}
