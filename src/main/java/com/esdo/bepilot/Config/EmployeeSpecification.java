package com.esdo.bepilot.Config;

import com.esdo.bepilot.Model.Entity.Account;
import com.esdo.bepilot.Model.Entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class EmployeeSpecification {
    public static Specification<Employee> filter(String key) {
        return (root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            // Tìm theo điều kiện tên
            Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + key + "%");

            // Tìm theo điều kiện email
            Path<Account> account = root.get("account");
            Predicate emailLike = criteriaBuilder.like(account.get("email"), "%" + key + "%");

            // Tìm theo điều kiện sđt
            Predicate phoneLike = criteriaBuilder.like(root.get("phone"), "%" + key + "%");

            // Nếu key khác null thì thêm điều kiện
            if(!Objects.isNull(key)) {
                predicates.add(nameLike);
                predicates.add(emailLike);
                predicates.add(phoneLike);
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
