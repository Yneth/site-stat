-- CREATE TABLE IF NOT EXISTS acl_access (
--   id int4 PRIMARY KEY,
--   name varchar not null
-- );
--
-- CREATE TABLE IF NOT EXISTS acl_class (
--   id int4 PRIMARY KEY,
--   name varchar not null
-- );
--
-- CREATE TABLE IF NOT EXISTS acl_entry (
--   id int4 PRIMARY KEY,
--   role_id int4,
--   permission_id int4,
--   access_id int4,
--   class_id int4,
--
--   FOREIGN KEY (role_id) REFERENCES authority (id),
--   FOREIGN KEY (permission_id) REFERENCES permission (id),
--   FOREIGN KEY (access_id) REFERENCES acl_access (id),
--   FOREIGN KEY (class_id) REFERENCES acl_class (id)
-- );




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
-- INSERT INTO PERMISSION (id, permission) VALUES (NEXTVAL('PERMISSION_ID_SEQ'), 'read');
-- INSERT INTO PERMISSION (id, permission) VALUES (NEXTVAL('PERMISSION_ID_SEQ'), 'write');

INSERT INTO SITE VALUES (NEXTVAL('SITE_ID_SEQ'), 'vk', 'vk.com', 1);
INSERT INTO SITE VALUES (NEXTVAL('SITE_ID_SEQ'), 'fb', 'facebook.com', 1);

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

-- DROP TABLE IF EXISTS acl_entry;
-- DROP TABLE IF EXISTS acl_access;
-- DROP TABLE IF EXISTS acl_class;
-- DROP TABLE IF EXISTS acl_permission;
--
-- CREATE TABLE IF NOT EXISTS acl_access (
--   id serial PRIMARY KEY,
--   name varchar not null
-- );
--
-- CREATE TABLE IF NOT EXISTS acl_class (
--   id serial PRIMARY KEY,
--   name varchar not null
-- );
--
-- CREATE TABLE IF NOT EXISTS acl_entry (
--   id serial PRIMARY KEY,
--   role_id int4,
--   permission_id int4,
--   access_id int4,
--   class_id int4,
--
--   FOREIGN KEY (role_id) REFERENCES authority (id),
--   FOREIGN KEY (permission_id) REFERENCES permission (id),
--   FOREIGN KEY (access_id) REFERENCES acl_access (id),
--   FOREIGN KEY (class_id) REFERENCES acl_class (id)
-- );
--
-- CREATE TABLE IF NOT EXISTS acl_permission (
--   id serial PRIMARY KEY,
--   name varchar NOT NULL
-- );
--
--
-- INSERT INTO acl_class VALUES (DEFAULT, 'Site');
-- INSERT INTO acl_class VALUES (DEFAULT, 'SiteSession');
--
-- INSERT INTO acl_access VALUES (DEFAULT, 'public');
-- INSERT INTO acl_access VALUES (DEFAULT, 'private');
--
-- INSERT INTO acl_permission VALUES (DEFAULT, 'read');
-- INSERT INTO acl_permission VALUES (DEFAULT, 'write');
--
--
-- INSERT INTO acl_entry VALUES (DEFAULT, 2, 1, 1, 1);
-- INSERT INTO acl_entry VALUES (DEFAULT, 2, 1, 1, 2);
--
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 1, 1, 1);
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 1, 1, 2);
--
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 2, 1, 1);
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 2, 1, 2);
--
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 1, 2, 1);
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 1, 2, 2);
--
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 2, 2, 1);
-- INSERT INTO acl_entry VALUES (DEFAULT, 1, 2, 2, 2);