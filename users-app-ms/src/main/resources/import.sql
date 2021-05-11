INSERT INTO `usersapp` (username, password, enabled, name, last_name, email) VALUES ('tuali','$2a$10$uPw9aU/fDSPRMk2ybKD6QeTuVDVCUfJ6QFv/yR3hKBTe7hKV4f9IG',1, 'Paula', 'Arganaraz','tuali@bolsadeideas.com');
INSERT INTO `usersapp` (username, password, enabled, name, last_name, email) VALUES ('admin','$2a$10$NJ6HDSi5IeO6qhAywqpLRucGUKREexfCW/MHTMbK/JOUDoYurJ4Im',1, 'Martin', 'Tapia','admin@bolsadeideas.com');
INSERT INTO `usersapp` (username, password, enabled, name, last_name, email) VALUES ('mimi','$2a$10$ruVswsXOY8C.V02uVw1oD.OA5gjqaqoUmkEJnrtL3Vy/NxeYBcAKG',1, 'Eva', 'Tapia','eva@bolsadeideas.com');
INSERT INTO `usersapp` (username, password, enabled, name, last_name, email) VALUES ('pocho','$2a$10$i.HnevqywuIPHmaTOXtpCu6z6peDKkirG71L9MutNcJIV.O0M.8A6',1, 'Maximo', 'Tapia','pocho@bolsadeideas.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `usersapp_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `usersapp_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `usersapp_roles` (user_id, role_id) VALUES (2, 1);
INSERT INTO `usersapp_roles` (user_id, role_id) VALUES (3, 1);
INSERT INTO `usersapp_roles` (user_id, role_id) VALUES (4, 1);