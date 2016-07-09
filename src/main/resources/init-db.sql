INSERT INTO USR VALUES (
  NEXTVAL('user_id_seq'),
  true,
  null,
  'admin@localhost',
  'Anthony',
  'en',
  'LastName admin',
  'admin',
  '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC',
  null,
  null
);

INSERT INTO USR VALUES (
  NEXTVAL('user_id_seq'),
  true,
  null,
  'user@localhost',
  'Anthony',
  'en',
  'LastName user',
  'user',
  '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',
  null,
  null
);

INSERT INTO authority VALUES (
  NEXTVAL('authority_id_seq'),
  'ROLE_ADMIN'
);

INSERT INTO authority VALUES (
  NEXTVAL('authority_id_seq'),
  'ROLE_USER'
);

INSERT INTO user_authority VALUES (
  1,
  1
);

INSERT INTO user_authority VALUES (
  2,
  2
);
-- create table permission (id int8 not null, permission varchar(50) not null, primary key (id))

INSERT INTO SITE VALUES (NEXTVAL('SITE_ID_SEQ'), 'vk', 'vk.com', 1);
INSERT INTO SITE VALUES (NEXTVAL('SITE_ID_SEQ'), 'fb', 'facebook.com', 1);

--create table site_session (id int8 not null, duration int8, time_end bytea not null, time_start bytea not null, site_id int8, primary key (id))

INSERT INTO SITE_SESSION (id, duration, time_start, time_end, site_id)
VALUES (
  NEXTVAL('SITE_SESSION_ID_SEQ'),
  60,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + INTERVAL '1 HOUR',
  1
);

INSERT INTO SITE_SESSION (id, duration, time_start, time_end, site_id) VALUES  (
  NEXTVAL('SITE_SESSION_ID_SEQ'),
  120,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + INTERVAL '2 HOUR',
  1
);

INSERT INTO SITE_SESSION (id, duration, time_start, time_end, site_id)
VALUES (
  NEXTVAL('SITE_SESSION_ID_SEQ'),
  120,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP + INTERVAL '2 HOUR',
  2
);

-- INSERT INTO SOCIAL_NETWORK VALUES (NEXTVAL('SOCIAL_NETWORK_ID_SEQ'), 'vk', 'vk.com', 1);
-- INSERT INTO SOCIAL_NETWORK VALUES (NEXTVAL('SOCIAL_NETWORK_ID_SEQ'), 'vk', 'vk.com', 2);
-- INSERT INTO SOCIAL_NETWORK VALUES (NEXTVAL('SOCIAL_NETWORK_ID_SEQ'), 'vk', 'vk.com', 3);
-- INSERT INTO SOCIAL_NETWORK VALUES (NEXTVAL('SOCIAL_NETWORK_ID_SEQ'), 'vk', 'vk.com', 4);
--
-- INSERT INTO SOCIAL_NETWORK_SESSION VALUES(
--   NEXTVAL('SOCIAL_NETWORK_SESSION_ID_SEQ'),
--   CURRENT_TIMESTAMP,
--   CURRENT_TIMESTAMP + INTERVAL '1 HOUR',
--   1,
--   1
-- );

-- SELECT * FROM USR;

-- SELECT * FROM SOCIAL_NETWORK_SESSION sns
--   INNER JOIN SOCIAL_NETWORK sn ON sns.SOCIAL_NETWORK_ID = sn.SOCIAL_NETWORK_ID
--   INNER JOIN USR u ON sn.USER_ID = u.USER_ID



-- DROP SCHEMA public CASCADE;
-- CREATE SCHEMA public;
--
-- CREATE TABLE USR (
--   USER_ID   SERIAL  NOT NULL,
--   USER_NAME VARCHAR NOT NULL,
--   CONSTRAINT PK_USER PRIMARY KEY (USER_ID)
-- );
--
-- CREATE TABLE SOCIAL_NETWORK (
--   SOCIAL_NETWORK_ID SERIAL  NOT NULL,
--   NAME              VARCHAR NOT NULL,
--   URL               VARCHAR NOT NULL,
--   USER_ID           INTEGER NOT NULL,
--   CONSTRAINT PK_SOCIAL_NETWORK PRIMARY KEY (SOCIAL_NETWORK_ID),
--   CONSTRAINT FK_SOCIAL_NETWORKS_USER FOREIGN KEY (USER_ID)
--   REFERENCES USR (USER_ID) MATCH SIMPLE
--   ON UPDATE CASCADE ON DELETE CASCADE
-- );
--
-- CREATE TABLE SOCIAL_NETWORK_SESSION (
--   SOCIAL_NETWORK_SESSION_ID SERIAL    NOT NULL,
--   TIME_START                TIMESTAMP NOT NULL,
--   TIME_END                  TIMESTAMP,
--   INTERVAL                  INT,
--   SOCIAL_NETWORK_ID         INTEGER   NOT NULL,
--   CONSTRAINT PK_SOCIAL_NETWORK_SESSION PRIMARY KEY (SOCIAL_NETWORK_SESSION_ID),
--   CONSTRAINT FK_SOCIAL_NETWORK_SESSIONS_SOCIAL_NETWORK FOREIGN KEY (SOCIAL_NETWORK_ID)
--   REFERENCES SOCIAL_NETWORK (SOCIAL_NETWORK_ID) MATCH SIMPLE
--   ON UPDATE CASCADE ON DELETE CASCADE
-- );
--