CREATE TABLE input_directory (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  path VARCHAR(255) NOT NULL
);

CREATE TABLE input_directory_history (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  directory_name VARCHAR(255),
  message_log VARCHAR(255),
  date TIMESTAMP
);

CREATE TABLE xml_file (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  message_log VARCHAR(255),
  date TIMESTAMP
);

INSERT INTO input_directory(path) VALUES('/home/ro');

COMMIT;

