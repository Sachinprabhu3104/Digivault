package com.digivault.bank;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    
    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    } 

    /**
     * Get all users.
     *
     * @return the list of users
     */
    public List<Users> fetchUsers() {
        return usersRepository.findAll();
    }

    /**
     * Save a new user.
     *
     * @param user the user to save
     * @return the saved user
     */
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public boolean loginUser(Long userId, String password) {
        Optional<Users> user = usersRepository.findById(userId);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    /**
     * Change the password of a user
     *
     * @param userId       the user's ID
     * @param newPassword  the new password to set
     * @return true if changed successfully, false if user not found
     */
    public boolean changePassword(Long userId, String newPassword) {
        Optional<Users> optionalUser = usersRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setPassword(newPassword);
            usersRepository.save(user);
            return true;
        }

        return false;
    }
}

