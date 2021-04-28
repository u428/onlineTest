package com.omad.lee.damo.Repository;

import com.omad.lee.damo.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findUserByEmail(String email);

    UserEntity findUserEntityByUserId(String userId);
}
