package FinalSiteOfGroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import FinalSiteOfGroup.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	 User findByLogin(String login);
	  List<User> findFirst10ByIdNotIn(List<Long> users);

}
