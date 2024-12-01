package com.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Enum.MaintenanceStatus;
import com.app.entities.MaintenanceRequest;
import com.app.repositiory.MaintenanceRequestRepository;

@RestController
@RequestMapping("/api/maintenance") // request from postman to backedn use these url with methods like get put post delete
public class MaintenanceRequestController {

	//repositpry call here
	@Autowired
	private MaintenanceRequestRepository maintenanceRequestRepository;

	// to add MaintenanceRequest to backend http://localhost:8080/maintenance (post)
	@PostMapping 
	public ResponseEntity<MaintenanceRequest> createRequest(@Valid @RequestBody MaintenanceRequest maintenanceRequest) {
		maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
		maintenanceRequest.setReportedDate(LocalDate.now());
		return ResponseEntity.ok(maintenanceRequestRepository.save(maintenanceRequest));
	}
	
	// to get all Maintenance form db. use url http://loaclhost:8080/Maintenance
	@GetMapping
	public ResponseEntity<List<MaintenanceRequest>> getAllRequests() {
		return ResponseEntity.ok(maintenanceRequestRepository.findAll());
	}

	// now get Maintenance  by id ill take the help of MaintenanceRequest entity to get by id. 
	@GetMapping("/{id}")
	public ResponseEntity<MaintenanceRequest> getRequestById(@PathVariable Long id) {
		Optional<MaintenanceRequest> request = maintenanceRequestRepository.findById(id);
		return request.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// to update added Maintenance in db 
	@PutMapping("/{id}")
	public ResponseEntity<MaintenanceRequest> updateRequest(@PathVariable Long id,
			@Valid @RequestBody MaintenanceRequest updatedRequest) {
		return maintenanceRequestRepository.findById(id).map(existingRequest -> {
			existingRequest.setIssueDescription(updatedRequest.getIssueDescription());
			existingRequest.setStatus(updatedRequest.getStatus());
			existingRequest.setResolvedDate(updatedRequest.getResolvedDate());
			return ResponseEntity.ok(maintenanceRequestRepository.save(existingRequest));
		}).orElse(ResponseEntity.notFound().build());
	}
	// now to write a code to delete Maintenance from db 
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        if (maintenanceRequestRepository.existsById(id)) {
            maintenanceRequestRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
