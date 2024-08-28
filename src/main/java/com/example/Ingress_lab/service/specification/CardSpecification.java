//package com.example.Ingress_lab.service.specification;
//
//import com.example.Ingress_lab.dao.entity.CardEntity;
//import com.example.Ingress_lab.model.criteria.CardCriteria;
//import com.example.Ingress_lab.util.PredicateUtil;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import lombok.AllArgsConstructor;
//import org.springframework.data.jpa.domain.Specification;
//
//import static com.example.Ingress_lab.dao.entity.CardEntity.Fields.*;
//import static com.example.Ingress_lab.util.PredicateUtil.applyLikePattern;
//
//@AllArgsConstructor
//public class CardSpecification implements Specification<CardEntity> {
//    private CardCriteria cardCriteria;
//
//
//    @Override
//    public Predicate toPredicate(Root<CardEntity> root,
//                                 CriteriaQuery<?> query,
//                                 CriteriaBuilder cb) {
//        var predicates = PredicateUtil.builder()
//                .addNullSafety(cardCriteria.getUsername(),
//                        usernameLike -> cb.like(root.get(username), applyLikePattern(usernameLike)))
//                .addNullSafety(cardCriteria.getAmountFrom(),
//                        amountFrom -> cb.greaterThanOrEqualTo(root.get(amount), amountFrom))
//                .addNullSafety(cardCriteria.getAmountTo(),
//                        amountTo -> cb.lessThanOrEqualTo(root.get(amount), amountTo))
//                .build();
//
//        return cb.and(predicates);
//    }
//}
