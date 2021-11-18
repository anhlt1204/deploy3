package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Response.WithdrawnResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WithdrawnService {

    public Withdrawn create(Withdrawn withdrawn);

    public List<WithdrawnResponse> getAllWithdrawn();

    public List<WithdrawnResponse> getWithdrawnByUserId(int pageIndex, int pageSize, Long id) ;

    public String deleteWithdrawnById(Long id) ;

    public WithdrawnResponse updateWithdrawnById(Withdrawn newWithdrawn);
}
