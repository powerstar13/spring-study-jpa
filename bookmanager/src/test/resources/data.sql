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

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`, `status`)
VALUES (1, 'JPA 초격차 패키지', 1, false, 100);

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`, `status`)
VALUES (2, 'Spring Security 초격차 패키지', 1, false, 200);

INSERT INTO book (`id`, `name`, `publisher_id`, `deleted`, `status`)
VALUES (3, 'SpringBoot 올인원 패키지', 1, true, 100);

INSERT INTO review (`id`, `title`, `content`, `score`, `user_id`, `book_id`)
VALUES (1, '내 인생을 바꾼 책', '너무너무 좋았어요', 5.0, 1, 1);

INSERT INTO review (`id`, `title`, `content`, `score`, `user_id`, `book_id`)
VALUES (2, '너무 진도가 빨라요', '조금 별로였어요', 3.0, 2, 2);

INSERT INTO comment (`id`, `comment`, `review_id`)
VALUES (1, '저도 좋았어요', 1);

INSERT INTO comment (`id`, `comment`, `review_id`)
VALUES (2, '저는 별로였는데요', 1);

INSERT INTO comment (`id`, `comment`, `review_id`)
VALUES (3, '저도 그냥 그랬어요', 2);