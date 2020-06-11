package com.testkit.build.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testkit.build.entity.ResourceEntity;

@Repository("ResourceRepository")
public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {

	ResourceEntity save(ResourceEntity resourceEntity);

	ResourceEntity findResourceEntityByUserEmailOrUserMobile(String userEmail, String userMobile);
}
