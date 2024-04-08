package com.MissSherlockHolmes.Farmers.Market.repository;

import com.MissSherlockHolmes.Farmers.Market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
}
