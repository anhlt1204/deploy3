package com.esdo.bepilot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "config")
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int default 0")
    private Integer maxMission;

    @Column(columnDefinition = "int default 0")
    private Integer missionLifeCycle;

    @Column(columnDefinition = "boolean default true")
    private Boolean isRepeat;

    @OneToMany(mappedBy = "config", cascade = CascadeType.ALL)
    private List<SubConfig> subConfigs = new ArrayList<>();
}
