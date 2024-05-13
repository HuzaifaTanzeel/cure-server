package com.example.demo.CommonFramework.Repositories.UAM;

import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UAMUserAccountRepository extends JpaRepository<UAMUserAccount,Long> {
    UAMUserAccount findByUserLogin(String userLogin);
}


