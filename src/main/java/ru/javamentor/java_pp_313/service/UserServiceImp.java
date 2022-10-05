package  ru.javamentor.java_pp_313.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.java_pp_313.model.User;
import ru.javamentor.java_pp_313.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   @Transactional
   public void saveUser(User user) {
      User userFromDB = userRepository.findByUsername(user.getUsername());
      if (userFromDB == null) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);
      }
   }

   @Override
   public User getUserById(Long id) {
      return userRepository.getReferenceById(id);
   }

   @Override
   @Transactional
   public void updateUser(Long id, User user) {
      if (user.getPassword().isEmpty()) {
         user.setPassword(userRepository.findByUsername(user.getUsername()).getPassword());
      } else {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      User userToBeUpdate = userRepository.getReferenceById(id);
      userToBeUpdate.setId(id);
      userRepository.save(user);
   }

   @Override
   @Transactional
   public void deleteUser(Long id) {
      if (userRepository.findById(id).isPresent()) {
         userRepository.deleteById(id);
      }
   }
   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   public User getUserByName(String username) {
      return userRepository.findByUsername(username);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = getUserByName(username);
      if (user == null) {
         throw new UsernameNotFoundException(String.format("User '%s' not found", username));
      }
      return user;
   }

}
