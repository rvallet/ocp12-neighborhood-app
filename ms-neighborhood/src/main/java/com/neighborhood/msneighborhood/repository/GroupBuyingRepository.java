package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, String> {

    List<GroupBuying> findAll();
    GroupBuying findGroupBuyingById (Long groupBuyingId);

}
