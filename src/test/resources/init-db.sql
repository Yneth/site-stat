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

INSERT INTO SITE VALUES (1, 'vk', 'vk.com', 1);

INSERT INTO SITE_SESSION (id, duration, time_start, time_end, site_id) VALUES  (
  1,
  120,
  now(),
  now() + INTERVAL '2 HOUR',
  1
);