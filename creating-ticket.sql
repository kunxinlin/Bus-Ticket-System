CREATE DATABASE  IF NOT EXISTS `bus_ticket_tracker`;
USE `bus_ticket_tracker`;

--
-- Table structure for table `busticket`
--

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `ticket_number` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `origin` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `estimated_time_arrival` timestamp DEFAULT NULL,
  `departure_time` time DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `price` decimal(11, 2) DEFAULT NULL,
  
  PRIMARY KEY (`ticket_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;