package FinalSiteOfGroup.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import FinalSiteOfGroup.models.*;
import FinalSiteOfGroup.repositories.*;

@Service
public class PostsService {
	@Autowired
	  private UserRepository usersRepo;
	  @Autowired
	  private PostRepository postsRepo;

	  public Page<Post> getPosts(int page, int pageSize) {
	    User currentUser = usersRepo.findOne(1L);
	    return postsRepo.findByAuthorInOrderByCreatedAtDesc(
	        currentUser.getSubscriptions(),
	        new PageRequest(page-1, pageSize) // spring рахує сторінки з нуля
	    );
	  }

	  public void addPost(String text) {
	    User currentUser = usersRepo.findOne(1L);
	    postsRepo.save(new Post(currentUser, text, new Date()));
	  }
	  
	  public void deletePost(Long id) {
		    //User currentUser = usersRepo.findOne(user.);
		    postsRepo.delete(id); //(new Post(currentUser, text, new Date()));
		  }
	  
}