package com.warehouse.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.app.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

	UserInfo findByIdUser(int idUser);

}
