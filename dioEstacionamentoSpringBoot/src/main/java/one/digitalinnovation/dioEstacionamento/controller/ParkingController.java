package one.digitalinnovation.dioEstacionamento.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.dioEstacionamento.controller.dto.ParkingDTO;
import one.digitalinnovation.dioEstacionamento.controller.mapper.ParkingMapper;
import one.digitalinnovation.dioEstacionamento.model.Parking;
import one.digitalinnovation.dioEstacionamento.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	public List<ParkingDTO> findAll(){
		
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		
		return result;
		
		
	}

}
