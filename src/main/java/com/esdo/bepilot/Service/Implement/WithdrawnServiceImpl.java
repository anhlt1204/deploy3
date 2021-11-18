package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Response.WithdrawnResponse;
import com.esdo.bepilot.Repository.WithdrawnRepository;
import com.esdo.bepilot.Service.Mapper.WithdrawnMapper;
import com.esdo.bepilot.Service.WithdrawnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WithdrawnServiceImpl implements WithdrawnService {

    @Autowired
    WithdrawnRepository withdrawnRepository ;
    @Autowired
    WithdrawnMapper mapper ;

    public Withdrawn create(Withdrawn withdrawn){
        log.info("Inside create of Withdrawn Service ");
//        withdrawnRepository.save(withdrawn) ;
        return null ;
    }

    public List<WithdrawnResponse> getAllWithdrawn(){
        log.info("Inside getAllWithdrawn of Withdrawn Service ");
//        withdrawnRepository.findAll() ;
        return null ;
    }

    public List<WithdrawnResponse> getWithdrawnByUserId(int pageIndex , int pageSize ,Long id) {
        log.info("Inside getWithdrawnById of Withdrawn Service ");

        Pageable paging = PageRequest.of(pageIndex, pageSize);

        Page<Withdrawn> page = withdrawnRepository.getByUserId(paging,id) ;
        List<Withdrawn> withdrawns = page.getContent();
        List<WithdrawnResponse> responses = mapper.mapWithdrawnList(withdrawns) ;

        return responses ;
    }

    public String deleteWithdrawnById(Long id) {
        log.info("Inside deleteWithdrawnById of Withdrawn Service ");
//        withdrawnRepository.deleteById(id);
        return "" ;
    }

    public WithdrawnResponse updateWithdrawnById(Withdrawn newWithdrawn) {
        log.info("Inside updateWithdrawnById of Withdrawn Service ");
//        withdrawnRepository.save(newWithdrawn);
        return null ;
    }
}