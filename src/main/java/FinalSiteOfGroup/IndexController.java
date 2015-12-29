package FinalSiteOfGroup;


import java.io.Console;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.outerJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import FinalSiteOfGroup.models.Post;
import FinalSiteOfGroup.services.PostsService;

import FinalSiteOfGroup.models.User;
import FinalSiteOfGroup.services.UsersService;

@Controller
public class IndexController {
	 @Autowired
	  private PostsService postsService;

	  @Autowired
	  private UsersService usersService;

	  @RequestMapping("/")
	  public String index(Model model) { return "Home"; }

	  @RequestMapping("/About us")
	  public String home(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
	    Page<Post> postsPage = postsService.getPosts(page, 5);
	    
	    int realPage;
	    if (postsPage.getNumberOfElements()==0 && page!=1)
	    {
	    	realPage=page-1;
	    	postsPage = postsService.getPosts(page-1, 5);
	    }
	    else realPage=page;
	    
	    model.addAttribute("posts", postsPage.getContent());
	    model.addAttribute("users", usersService.getSubscribeRecommendations());
	    model.addAttribute("pagesCount", postsPage.getTotalPages());
	  //  System.out.println("elements_count:"+postsPage.getNumberOfElements());
	    model.addAttribute("currentPage",realPage);
	    return "About us";
	  }

	  @RequestMapping(value = "/post", method = RequestMethod.POST)
	  public String createPost(@RequestParam("text") String postText) {
	    postsService.addPost(postText);
	    return "redirect:About us";
	  }
	  
	  @RequestMapping(value = "/remove_post", method = RequestMethod.GET)
	  public String removePost(@RequestParam("post_id") Long postId,HttpServletRequest request) {
	    postsService.deletePost(postId);
	    String referer=request.getHeader("Referer");
	    return "redirect:"+referer;
	  }

	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	  public String register(@RequestParam("login") String login, 
	      @RequestParam("email") String email, 
	      @RequestParam("pass") String pass) { 
	    usersService.register(login, email, pass);
	    return "redirect:home";
	  }

	  @RequestMapping(value="/subscribe", method = RequestMethod.POST)
	  public String subscribe(@RequestParam("login") String login) {
	    usersService.subscribe(login);
	    return "redirect:home";
	  }
	  
	  @RequestMapping("/Home")
	    public String index() {
	        return "Home";
	    }

	    @RequestMapping("/Cathalogs")
	    public String index2() {
	        return "Cathalogs";
	    }
	    

	    @RequestMapping("/Contacts")
	    public String index3() {
	        return "Contacts";
	    }
	    
	    @RequestMapping("/API")
	    public String index4() {
	        return "API";
	    }
} 

