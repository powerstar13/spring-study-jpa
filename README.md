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
18. 영속성 컨텍스트(Persistence Context)
    - MySQL 연결
19. Entity 캐시
20. Entity 생명주기
    - 비 영속 상태
    - 영속 상태
    - 준 영속 상태
    - 삭제 상태
21. JPA에서 Transaction 활용하기(트랜잭션 매니저)
    - 메서드에 @Transactional 애노테이션 사용
      - 메서드의 시작이 트랜잭션 시작
      - 메서드의 종료가 트랜잭션 종료
22. UncheckedException과 CheckedException의 차이
    - UncheckedExcpetion는 Transactional이 있으면 rollback 처리됨
    - CheckedException는 try-catch를 통해 핸들링을 강제한다. (Transactional이 있더라도 rollback되지 않음)
    - rollbackFor 옵션을 사용하여 CheckedException 또한 rollbackOn() 메서드 안에 들어갈 수 있도록 등록할 수 있다.
23. WARN: 같은 Bean 안에서 메서드가 다른 메서드를 호출할 경우 @Transactional 애노테이션을 무시한다.
24. @Transactional 애노테이션의 isolation 옵션으로 격리 단계 제어
    - READ_UNCOMMITTED
    - READ_COMMITTED
    - REPEATABLE_READ
    - SERIALIZABLE
25. @Transactional 애노테이션의 propagation(전파) 옵션
    - REQUIRED
    - REQUIRES_NEW
    - NESTED
    - SUPPORTS
    - NOT_SUPPORTED
    - MANDATORY
    - NEVER
26. @Transactional 애노테이션은 Type과 Method에 붙일 수 있다.
    - Type이란 Class를 말함