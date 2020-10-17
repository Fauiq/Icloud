package com.icloud.respository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icloud.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {
	
}
