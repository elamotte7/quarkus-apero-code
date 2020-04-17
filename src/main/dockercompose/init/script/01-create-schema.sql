CREATE DATABASE IF NOT EXISTS `apero-code`;

CREATE USER 'quarkus'@'%' IDENTIFIED BY 'quarkus';

GRANT ALL PRIVILEGES ON `apero-code`.* TO 'quarkus'@'%';