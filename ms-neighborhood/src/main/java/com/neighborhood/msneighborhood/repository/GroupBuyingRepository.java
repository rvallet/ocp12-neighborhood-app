package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, String> {

    List<GroupBuying> findAll();

    @Query("SELECT gb FROM GroupBuying gb WHERE gb.groupBuyingStatus IN ?1 ORDER BY gb.creationDate ASC")
    List<GroupBuying> findAllFiltredByStatusList(List<String> groupBuyingStatus);

    GroupBuying findGroupBuyingById (Long groupBuyingId);


}
