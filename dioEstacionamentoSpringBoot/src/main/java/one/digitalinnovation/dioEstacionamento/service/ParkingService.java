package one.digitalinnovation.dioEstacionamento.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import one.digitalinnovation.dioEstacionamento.exception.ParkingNotFoundException;

import org.springframework.stereotype.Service;

import one.digitalinnovation.dioEstacionamento.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap();
	
	static {
		var id = getUUID();
		var id1 = getUUID();
		Parking parking = new Parking(id, "III-9878", "RJ", "HB20", "PRETO");
		Parking parking1 = new Parking(id1, "KJU-7865", "RJ", "GOL", "BRANCO");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
		
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
            
		Parking parking = parkingMap.get(id);
                if(parking == null){
                    throw new ParkingNotFoundException(id);
                }
		return parking;
	}
	
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(getUUID());
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
		
	}
        
        public void delete(String id){
            findById(id);
            parkingMap.remove(id);
        }
        
        public Parking update(String id, Parking parkingCreate){
            Parking parking = findById(id);
            parking.setColor(parkingCreate.getColor());
            parkingMap.replace(id, parking);
            return parking;
        }
	
}
