package application.builders;

import application.dtos.AlarmDTO;
import application.entities.Alarm;

public class AlarmBuilder {
    private AlarmBuilder() {}

    public static AlarmDTO toDTO(Alarm alarm) {
        return new AlarmDTO(alarm.getId(),
                alarm.getUser(),
                alarm.getStock(),
                alarm.getDefinitionPrice(),
                alarm.getCurrentPrice(),
                alarm.getVariancePercentage(),
                alarm.getLowTargetPercentage(),
                alarm.getHighTargetPercentage(),
                alarm.getActive());
    }

    public static Alarm toEntity(AlarmDTO alarmDTO) {
        return new Alarm(alarmDTO.getId(),
                alarmDTO.getUser(),
                alarmDTO.getStock(),
                alarmDTO.getDefinitionPrice(),
                alarmDTO.getCurrentPrice(),
                alarmDTO.getLowTargetPercentage(),
                alarmDTO.getHighTargetPercentage(),
                alarmDTO.getActive());
    }
}
