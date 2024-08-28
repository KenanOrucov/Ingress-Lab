//package com.example.Ingress_lab.util;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//import jakarta.persistence.criteria.Predicate;
//
//
//public class PredicateUtil {
//
//    private final List<Predicate> predicates = new ArrayList<>();
//
//    public static PredicateUtil builder() {
//        return new PredicateUtil();
//    }
//
//    public <T> PredicateUtil add(T object, Function<T, Predicate> function){
//        predicates.add(function.apply(object));
//        return this;
//    }
//
//    public <T> PredicateUtil addNullSafety(T object, Function<T, Predicate> function){
//        if(object != null){
//            predicates.add(function.apply(object));
//        }
//        return this;
//    }
//
//    public Predicate[] build(){
//        return predicates.toArray(new Predicate[0]);
//    }
//
//    public static String applyLikePattern(String key){
//        return "%" + key + "%";
//    }
//}
