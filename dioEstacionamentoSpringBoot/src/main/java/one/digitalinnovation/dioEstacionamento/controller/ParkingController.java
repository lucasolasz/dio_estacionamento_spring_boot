package one.digitalinnovation.dioEstacionamento.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.dioEstacionamento.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.dioEstacionamento.controller.dto.ParkingDTO;
import one.digitalinnovation.dioEstacionamento.controller.mapper.ParkingMapper;
import one.digitalinnovation.dioEstacionamento.model.Parking;
import one.digitalinnovation.dioEstacionamento.service.ParkingService;

@RestController
@RequestMapping("/parking")
@Tag(description = "Estacionamento de Carros", 
    name = "Controle de estacionamento")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
        @Operation(summary = "Find All parkings", 
            description = "Provides all used parkings list")        
	public ResponseEntity<List<ParkingDTO>> findAll(){
		
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		
		return ResponseEntity.ok(result);
		
		
	}
	
	@GetMapping("/{id}")
        @Operation(summary = "Find By Id Vehicle", 
            description = "Provides vehicle by id")  
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping
        @Operation(summary = "Create new parking record", 
            description = "Provides new record on parking")  
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
		
	}
	
	

}
