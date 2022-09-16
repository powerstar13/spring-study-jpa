INSERT INTO users (`name`, `email`, `created_at`, `updated_at`)
VALUES ('master', 'master@gmail.com', NOW(), NOW());

INSERT INTO users (`name`, `email`, `created_at`, `updated_at`)
VALUES ('dennis', 'dennis@gmail.com', NOW(), NOW());

INSERT INTO users (`name`, `email`, `created_at`, `updated_at`)
VALUES ('sophia', 'sophia@gmail.com', NOW(), NOW());

INSERT INTO users (`name`, `email`, `created_at`, `updated_at`)
VALUES ('james', 'james@gmail.com', NOW(), NOW());

INSERT INTO users (`name`, `email`, `created_at`, `updated_at`)
VALUES ('master', 'master@test.com', NOW(), NOW());

INSERT INTO publisher (`id`, `name`)
VALUES (1, '패스트캠퍼스');

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`)
VALUES (1, 'JPA 초격차 패키지', 1, false);

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`)
VALUES (2, 'Spring Security 초격차 패키지', 1, false);

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`)
VALUES (3, 'SpringBoot 올인원 패키지', 1, true);
