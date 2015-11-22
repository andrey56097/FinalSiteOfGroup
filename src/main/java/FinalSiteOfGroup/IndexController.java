package FinalSiteOfGroup;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import FinalSiteOfGroup.models.Post;
import FinalSiteOfGroup.services.PostsService;

@Controller
public class IndexController {

	 @Autowired
     private PostsService postsService;

     @RequestMapping("/About us")
     public String index(Model model) {
         model.addAttribute("posts", postsService.getRecentPosts());
         return "About us";
     }
     
     @RequestMapping(value = "/post", method = RequestMethod.POST)
     public String createPost(@RequestParam("text") String postText) {
         postsService.addPost(new Post("Unknown", postText, new Date()));
         return "redirect:About us";
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

