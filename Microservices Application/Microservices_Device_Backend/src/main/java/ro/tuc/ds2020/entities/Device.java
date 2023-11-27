package ro.tuc.ds2020.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.enums.DeviceType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "devices")
public class Device {

    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer deviceId;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(name = "user_id")
    private Integer userId;
}
