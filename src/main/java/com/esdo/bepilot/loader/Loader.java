package com.esdo.bepilot.loader;

import com.esdo.bepilot.Model.Entity.Account;
import com.esdo.bepilot.Model.Entity.Config;
import com.esdo.bepilot.Model.Entity.Employee;
import com.esdo.bepilot.Model.Entity.SubConfig;
import com.esdo.bepilot.Repository.AccountRepository;
import com.esdo.bepilot.Repository.ConfigRepository;
import com.esdo.bepilot.Repository.EmployeeRepository;
import com.esdo.bepilot.Repository.SubConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Loader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private SubConfigRepository subConfigRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public void run(String... args) throws Exception {
        fakeDataAccount();
        fakeDataConfig();
    }

    private void fakeDataConfig() {
        List<Config> configs = configRepository.findAll();
        if (configs.size() == 0) {
            Config config = new Config();
            config.setMaxMission(30);
            config.setMissionLifeCycle(7);
            config.setIsRepeat(true);
            configRepository.save(config);

            SubConfig subConfig1 = new SubConfig();
            subConfig1.setCommunication("Youtube");
            subConfig1.setType("Chạy từ khóa");
            subConfig1.setMinTime(3);
            subConfig1.setMaxTime(10);
            subConfig1.setCustomerPay(BigDecimal.valueOf(2000));
            subConfig1.setUserReceived(BigDecimal.valueOf(1000));
            subConfig1.setConfig(config);
            subConfigRepository.save(subConfig1);

            SubConfig subConfig2 = new SubConfig();
            subConfig2.setCommunication("Google");
            subConfig2.setType("Chạy từ khóa");
            subConfig2.setMinTime(3);
            subConfig2.setMaxTime(10);
            subConfig2.setCustomerPay(BigDecimal.valueOf(2000));
            subConfig2.setUserReceived(BigDecimal.valueOf(1000));
            subConfig2.setConfig(config);
            subConfigRepository.save(subConfig2);

            SubConfig subConfig3 = new SubConfig();
            subConfig3.setCommunication("Google");
            subConfig3.setType("Chạy backlink");
            subConfig3.setMinTime(3);
            subConfig3.setMaxTime(10);
            subConfig3.setCustomerPay(BigDecimal.valueOf(1400));
            subConfig3.setUserReceived(BigDecimal.valueOf(700));
            subConfig3.setConfig(config);
            subConfigRepository.save(subConfig3);

            SubConfig subConfig4 = new SubConfig();
            subConfig4.setCommunication("Google");
            subConfig4.setType("Nhiệm vụ phức tạp");
            subConfig4.setMinTime(3);
            subConfig4.setMaxTime(10);
            subConfig4.setCustomerPay(BigDecimal.valueOf(1400));
            subConfig4.setUserReceived(BigDecimal.valueOf(700));
            subConfig4.setConfig(config);
            subConfigRepository.save(subConfig4);
        }
    }

    private void fakeDataAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() == 0) {
            Account account = new Account();
            account.setEmail("admin@gmail.com");
            account.setPassword(bcryptEncoder.encode("admin123"));
            account.setRole("ADMIN");
            accountRepository.save(account);

            Employee employee = new Employee();
            employee.setAvatar("src/main/resources/images/avatar.png");
            employee.setName("Admin");
            employee.setPhone("0123456789");
            employee.setEmployeeKey("NV1");
            employee.setAccount(account);
            employeeRepository.save(employee);
        }
    }
}
