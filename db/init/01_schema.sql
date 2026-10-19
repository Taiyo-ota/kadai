CREATE TABLE users (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  email      VARCHAR(255) NOT NULL UNIQUE,
  password   VARCHAR(255) NOT NULL,
  name       VARCHAR(50)  NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  name    VARCHAR(50) NOT NULL,
  color   CHAR(7)     NOT NULL,
  type    VARCHAR(10) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE schedules (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id       BIGINT NOT NULL,
  category_id   BIGINT,
  title         VARCHAR(100) NOT NULL,
  schedule_date DATE NOT NULL,
  start_time    TIME,
  end_time      TIME,
  memo          TEXT,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE finances (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id      BIGINT NOT NULL,
  category_id  BIGINT,
  amount       INT NOT NULL,
  finance_date DATE NOT NULL,
  memo         VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE category_keywords (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  category_id BIGINT NOT NULL,
  keyword     VARCHAR(50) NOT NULL,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);