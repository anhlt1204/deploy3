package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.Withdrawn;
import com.esdo.bepilot.Model.Response.WithdrawnResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class WithdrawnMapper {

    public WithdrawnResponse mapToWithdrawn(Withdrawn entity) {

        WithdrawnResponse response = new WithdrawnResponse() ;
        response.setId(entity.getId());

        response.setUserId(entity.getUser().getId());
        response.setAmount(entity.getAmount());
        response.setMoneyRemaining(entity.getMoneyRemaining());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setStatus(entity.getStatus());

        return response ;

    }

    public List<WithdrawnResponse> mapWithdrawnList(List<Withdrawn> list) {
        List<WithdrawnResponse> responses = new ArrayList<>() ;
        list.forEach(withdrawn ->  {
            responses.add(this.mapToWithdrawn(withdrawn)) ;
        });
        return responses ;
    }
}
