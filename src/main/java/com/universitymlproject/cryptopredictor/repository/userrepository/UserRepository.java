package com.universitymlproject.cryptopredictor.repository.userrepository;

import com.universitymlproject.cryptopredictor.model.userrelated.User;

public interface UserRepository {

    User findUserById(long id);

    User findUserByUsernameWithRoles(String username);

}
