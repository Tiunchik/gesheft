package org.test.gesheft.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "select * from client where name like ?1 or surname like ?1",
            nativeQuery = true)
    List<Client> filter(String str);
}
