package application.services;

import application.builders.AlarmBuilder;
import application.dtos.AddAlarmDTO;
import application.dtos.AlarmDTO;
import application.entities.Alarm;
import application.repositories.AlarmRepository;
import application.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public AlarmService(AlarmRepository alarmRepository, UserRepository userRepository) {
        this.alarmRepository = alarmRepository;
        this.userRepository = userRepository;
    }

    public List<AlarmDTO> getAlarms() {
        List<Alarm> alarmsList = alarmRepository.findAll();
        return alarmsList.stream()
                .map(AlarmBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public AlarmDTO findAlarmById(UUID id) {
        Optional<Alarm> alarmOptional = alarmRepository.findById(id);
        if (alarmOptional.isEmpty()) {
            LOGGER.error("Alarm with id {} was not found in db", id);
            return null;
        }
        return AlarmBuilder.toDTO(alarmOptional.get());
    }

    public UUID insert(AddAlarmDTO addAlarmDTO) {
        AlarmDTO alarmDTO = new AlarmDTO(
                addAlarmDTO.getId(),
                userRepository.findById(addAlarmDTO.getUser()).get(),
                addAlarmDTO.getStock(),
                addAlarmDTO.getDefinitionPrice(),
                addAlarmDTO.getCurrentPrice(),
                addAlarmDTO.getVariancePercentage(),
                addAlarmDTO.getTargetPercentage(),
                addAlarmDTO.getActive()
        );
        if (!alarmRepository.findByUserAndStock(alarmDTO.getUser().getId(), alarmDTO.getStock()).isEmpty()) {
            LOGGER.error("There is already an alarm which monitors the stock with the symbol {} by the user with the id {}", alarmDTO.getStock(), alarmDTO.getUser().getId());
            return null;
        }
        Alarm alarm = AlarmBuilder.toEntity(alarmDTO);
        alarm = alarmRepository.save(alarm);
        LOGGER.debug("Alarm with id {} was inserted in db", alarm.getId());
        return alarm.getId();
    }

    public UUID update(AddAlarmDTO addAlarmDTO) {
        AlarmDTO alarmDTO = new AlarmDTO(
                addAlarmDTO.getId(),
                userRepository.findById(addAlarmDTO.getUser()).get(),
                addAlarmDTO.getStock(),
                addAlarmDTO.getDefinitionPrice(),
                addAlarmDTO.getCurrentPrice(),
                addAlarmDTO.getVariancePercentage(),
                addAlarmDTO.getTargetPercentage(),
                addAlarmDTO.getActive()
        );
        if (alarmRepository.findByUserAndStock(alarmDTO.getUser().getId(), alarmDTO.getStock()).isEmpty()) {
            LOGGER.error("There isn't any alarm set by the user with the id {} monitoring the stock with the symbol {}", alarmDTO.getUser().getId(), alarmDTO.getStock());
            return null;
        }
        Alarm alarm = AlarmBuilder.toEntity(alarmDTO);
        alarm = alarmRepository.save(alarm);
        LOGGER.debug("Alarm with id {} was updated in db", alarm.getId());
        return alarm.getId();
    }

    public void delete(UUID id) {
        alarmRepository.deleteById(id);
        LOGGER.debug("Alarm with id {} was deleted from db", id);
    }

    public List<AlarmDTO> getAlarmsByUser(UUID id) {
        List<Alarm> alarmsList = alarmRepository.findByUser(id);
        if (alarmsList.isEmpty()) {
            LOGGER.error("Alarms with user id {} were not found in db", id);
            return null;
        }
        return alarmsList.stream()
                .map(AlarmBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public List<AlarmDTO> getAlarmsByStock(String stockSymbol) {
        List<Alarm> alarmsList = alarmRepository.findByStock(stockSymbol);
        if (alarmsList.isEmpty()) {
            LOGGER.error("Alarms with stock symbol {} were not found in db", stockSymbol);
            return null;
        }
        return alarmsList.stream()
                .map(AlarmBuilder::toDTO)
                .collect(Collectors.toList());
    }
}
