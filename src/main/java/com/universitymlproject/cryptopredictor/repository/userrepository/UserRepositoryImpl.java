package com.universitymlproject.cryptopredictor.repository.userrepository;

import com.universitymlproject.cryptopredictor.model.userrelated.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUserById(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsernameWithRoles(String username){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                                 "JOIN FETCH u.roles " +
                                 "WHERE u.username = :data", User.class);
        query.setParameter("data", username);

        return query.getSingleResult();
    }

}
