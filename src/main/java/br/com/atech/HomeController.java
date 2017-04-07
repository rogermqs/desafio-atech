package br.com.atech;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"","/"})
public class HomeController {

    @GetMapping
    public void home(HttpServletResponse response) throws IOException{
    	response.sendRedirect("index.html#!/index#index"); 
    }

}
