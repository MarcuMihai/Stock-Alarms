package application.controllers;

import application.dtos.AddAlarmDTO;
import application.dtos.AlarmDTO;
import application.services.AlarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/alarms")
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping()
    public ResponseEntity<List<AlarmDTO>> getAlarms() {
        List<AlarmDTO> alarmDTOs = alarmService.getAlarms();
        return new ResponseEntity<>(alarmDTOs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> insertAlarm(@RequestBody AddAlarmDTO addAlarmDTO) {
        UUID alarmId = alarmService.insert(addAlarmDTO);
        if (alarmId==null) {
            return new ResponseEntity<>("There is already an alarm made for this stock by this user", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(alarmId, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateAlarm(@RequestBody AddAlarmDTO addAlarmDTO) {
        UUID alarmId = alarmService.update(addAlarmDTO);
        if (alarmId==null) {
            return new ResponseEntity<>("There is no alarm set for this stock by this user", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(alarmId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UUID> deleteAlarm(@PathVariable("id") UUID id) {
        alarmService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlarmDTO> getAlarmById(@PathVariable("id") UUID id) {
        AlarmDTO alarmDTO = alarmService.findAlarmById(id);
        return new ResponseEntity<>(alarmDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<AlarmDTO>> getAlarmsByUser(@PathVariable("userId") UUID userId) {
        List<AlarmDTO> alarmDTOs = alarmService.getAlarmsByUser(userId);
        return new ResponseEntity<>(alarmDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/stock/{stockId}")
    public ResponseEntity<List<AlarmDTO>> getAlarmsByStock(@PathVariable("stockId") UUID stockId) {
        List<AlarmDTO> alarmDTOs = alarmService.getAlarmsByStock(stockId);
        return new ResponseEntity<>(alarmDTOs, HttpStatus.OK);
    }
}
