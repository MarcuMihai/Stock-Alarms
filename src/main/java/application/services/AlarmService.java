package application.services;

import application.builders.AlarmBuilder;
import application.dtos.AddAlarmDTO;
import application.dtos.AlarmDTO;
import application.entities.Alarm;
import application.repositories.AlarmRepository;
import application.repositories.StockRepository;
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
    private final StockRepository stockRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public AlarmService(AlarmRepository alarmRepository, UserRepository userRepository, StockRepository stockRepository) {
        this.alarmRepository = alarmRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
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
                stockRepository.findById(addAlarmDTO.getStock()).get(),
                addAlarmDTO.getDefinitionPrice(),
                addAlarmDTO.getCurrentPrice(),
                addAlarmDTO.getVariancePercentage(),
                addAlarmDTO.getTargetPercentage(),
                addAlarmDTO.getActive()
        );
        if (!alarmRepository.findByUserAndStock(alarmDTO.getUser().getId(), alarmDTO.getStock().getId()).isEmpty()) {
            LOGGER.error("There is already an alarm which monitors the stock with the id {} by the user with the id {}", alarmDTO.getStock().getId(), alarmDTO.getUser().getId());
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
                stockRepository.findById(addAlarmDTO.getStock()).get(),
                addAlarmDTO.getDefinitionPrice(),
                addAlarmDTO.getCurrentPrice(),
                addAlarmDTO.getVariancePercentage(),
                addAlarmDTO.getTargetPercentage(),
                addAlarmDTO.getActive()
        );
        if (alarmRepository.findByUserAndStock(alarmDTO.getUser().getId(), alarmDTO.getStock().getId()).isEmpty()) {
            LOGGER.error("There isn't any alarm set by the user with the id {} monitoring the stock with the id {}", alarmDTO.getUser().getId(), alarmDTO.getStock().getId());
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

    public List<AlarmDTO> getAlarmsByStock(UUID id) {
        List<Alarm> alarmsList = alarmRepository.findByStock(id);
        if (alarmsList.isEmpty()) {
            LOGGER.error("Alarms with stock id {} were not found in db", id);
            return null;
        }
        return alarmsList.stream()
                .map(AlarmBuilder::toDTO)
                .collect(Collectors.toList());
    }
}
