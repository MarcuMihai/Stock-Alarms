package application.dtos;

import application.entities.Alarm;

import java.util.List;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private List<Alarm> alarms;

    public UserDTO() {
    }

    public UserDTO(UUID id, String first_name, String last_name, String email, String password, List<Alarm> alarms) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.alarms = alarms;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
