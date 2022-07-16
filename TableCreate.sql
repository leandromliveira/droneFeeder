CREATE TABLE dronefeeder.drone (
  id int(11) NOT NULL,
  available bit(1) DEFAULT NULL,
  latitude varchar(255) DEFAULT NULL,
  longitude varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dronefeeder.video (
  id int(11) NOT NULL,
  video longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dronefeeder.delivery (
  id int(11) NOT NULL,
  delivered_date datetime DEFAULT NULL,
  delivery_status varchar(255) DEFAULT NULL,
  latitude varchar(255) DEFAULT NULL,
  longitude varchar(255) DEFAULT NULL,
  posted_date datetime DEFAULT NULL,
  drone_id int(11) DEFAULT NULL,
  video_id int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4y03p3qe1k40wjerkrecqscl4` (`drone_id`),
  KEY `FKpv3td2gml8vyvvyom0dp6x7gw` (`video_id`),
  CONSTRAINT `FK4y03p3qe1k40wjerkrecqscl4` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`id`),
  CONSTRAINT `FKpv3td2gml8vyvvyom0dp6x7gw` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE dronefeeder.hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



