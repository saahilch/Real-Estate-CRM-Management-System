package com.app.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.MaintenanceRequest;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

}
