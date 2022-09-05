# JPA 프로그래밍

1. dependencies
   - spring-boot-starter-data-jpa
   - spring-boot-starter-web
   - lombok
   - h2database
2. JPA의 필수 도구 Lombok
3. H2 DB 연결
4. Jpa Repository Interface 계층 살펴보기
5. Jpa Repository Interface 메서드 실습
   - data.sql 사용하여 데이터 세팅
   - spring.jpa.show-sql 사용하여 Query Logging
   - spring.jpa.properties.hibernate.format_sql 사용하여 Query를 예쁘게 출력
6. SimpleJpaRepository 분석
7. QueryMethod 기본 실습
8. 쿼리메서드로 정렬시켜보기
9. 쿼리메서드로 페이징 처리하기
10. Entity의 기본속성 (annotation)
11. Entity의 Listener 활용
    - @prePersist
    - @PostPersist
    - @PreUpdate
    - @PostUpdate
    - @PreRemove
    - @PostRemove
    - @PostLoad
    - @EntityListeners
    - @EnableJpaAuditing
    - @EntityListeners(value = { AuditingEntityListener.class })
    - @CreatedDate
    - @LastModifiedDate
    - @MappedSuperclass
12. 연관관계(relation) 및 ERD 알아보기
13. 1:1 연관관계 알아보기
    - @OneToOne
      - optional
      - mappedBy
    - @ToString.Exclude
14. 1:N 연관관계 알아보기
    - @Column
      - name
    - @OneToMany
      - fetch
    - @JoinColumn
      - name
      - insertable
      - updateable
15. N:1 연관관계 알아보기
    - @ManyToOne
16. M:N 연관관계 알아보기
    - @ManyToMany
    - 현업에서는 잘 사용하지 않는 연관관계이다.
    - 아주 특별한 필요가 없는 이상 OneToMany와 ManyToOne을 사용하여 ManyToMany을 피해가는 것이 좋다.
    - ManyToMany는 중간 테이블이 생성된다.
    - 연관관계를 풀어내기 위해 1:N:1 조합으로 설계하는 것이 좋다.
17. M:N 연관관계를 1:N:1 연관관계로 풀어내기
    - @OneToMany
    - @ManyToOne
