package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Entity.Config;
import com.esdo.bepilot.Model.Entity.SubConfig;
import com.esdo.bepilot.Model.Request.ConfigRequest;
import com.esdo.bepilot.Model.Response.ConfigResponse;
import com.esdo.bepilot.Repository.ConfigRepository;
import com.esdo.bepilot.Repository.SubConfigRepository;
import com.esdo.bepilot.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private SubConfigRepository subConfigRepository;

    @Override
    public ConfigResponse getConfig() {
        Optional<Config> configOptional = configRepository.findById(1L);
        if (configOptional.isEmpty()) {
            throw new InvalidException("Config is empty");
        }
        Config config = configOptional.get();
        ConfigResponse response = new ConfigResponse();
        response.setConfig(config);

        return response;
    }

    @Override
    public ConfigResponse editConfig(ConfigRequest request) {
        Optional<SubConfig> subConfigOptional1 = subConfigRepository.findById(1L);
        if (subConfigOptional1.isEmpty()) {
            throw new InvalidException("SubConfig is empty");
        }
        SubConfig subConfig1 = subConfigOptional1.get();

        subConfig1.setMinTime(request.getYtKeyMinTime());
        subConfig1.setMaxTime(request.getYtKeyMaxTime());
        subConfig1.setCustomerPay(BigDecimal.valueOf(request.getYtKeyCustomerPay()));
        subConfig1.setUserReceived(BigDecimal.valueOf(request.getYtKeyUserReceived()));
        subConfigRepository.save(subConfig1);

        Optional<SubConfig> subConfigOptional2 = subConfigRepository.findById(2L);
        if (subConfigOptional2.isEmpty()) {
            throw new InvalidException("SubConfig is empty");
        }
        SubConfig subConfig2 = subConfigOptional2.get();

        subConfig2.setMinTime(request.getGgKeyMinTime());
        subConfig2.setMaxTime(request.getGgKeyMaxTime());
        subConfig2.setCustomerPay(BigDecimal.valueOf(request.getGgKeyCustomerPay()));
        subConfig2.setUserReceived(BigDecimal.valueOf(request.getGgKeyUserReceived()));
        subConfigRepository.save(subConfig2);

        Optional<SubConfig> subConfigOptional3 = subConfigRepository.findById(3L);
        if (subConfigOptional3.isEmpty()) {
            throw new InvalidException("SubConfig is empty");
        }
        SubConfig subConfig3 = subConfigOptional3.get();

        subConfig3.setMinTime(request.getGgBackLinkMinTime());
        subConfig3.setMaxTime(request.getGgBackLinkMaxTime());
        subConfig3.setCustomerPay(BigDecimal.valueOf(request.getGgBackLinkCustomerPay()));
        subConfig3.setUserReceived(BigDecimal.valueOf(request.getGgBackLinkUserReceived()));
        subConfigRepository.save(subConfig3);

        Optional<SubConfig> subConfigOptional4 = subConfigRepository.findById(4L);
        if (subConfigOptional4.isEmpty()) {
            throw new InvalidException("SubConfig is empty");
        }
        SubConfig subConfig4 = subConfigOptional4.get();

        subConfig4.setMinTime(request.getGgMissionMinTime());
        subConfig4.setMaxTime(request.getGgMissionMaxTime());
        subConfig4.setCustomerPay(BigDecimal.valueOf(request.getGgMissionCustomerPay()));
        subConfig4.setUserReceived(BigDecimal.valueOf(request.getGgMissionUserReceived()));
        subConfigRepository.save(subConfig4);

        Optional<Config> configOptional = configRepository.findById(1L);
        if (configOptional.isEmpty()) {
            throw new InvalidException("Config is empty");
        }
        Config config = configOptional.get();

        config.setMaxMission(request.getMaxMission());
        config.setMissionLifeCycle(request.getMissionLifeCycle());
        config.setIsRepeat(request.getIsRepeat());
        configRepository.save(config);

        Config configSave = configRepository.save(config);
        ConfigResponse response = new ConfigResponse();
        response.setConfig(configSave);
        return response;
    }
}
