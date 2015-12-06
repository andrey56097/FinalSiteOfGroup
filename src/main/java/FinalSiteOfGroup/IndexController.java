package FinalSiteOfGroup;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	  public String index(Model model) { return "index"; }

	  @RequestMapping("/About us")
	  public String home(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
	    Page<Post> postsPage = postsService.getPosts(page, 5);
	    model.addAttribute("posts", postsPage.getContent());
	    model.addAttribute("users", usersService.getSubscribeRecommendations());
	    model.addAttribute("pagesCount", postsPage.getTotalPages());
	    model.addAttribute("currentPage", page);
	    return "About us";
	  }

	  @RequestMapping(value = "/post", method = RequestMethod.POST)
	  public String createPost(@RequestParam("text") String postText) {
	    postsService.addPost(postText);
	    return "redirect:About us";
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
} 

