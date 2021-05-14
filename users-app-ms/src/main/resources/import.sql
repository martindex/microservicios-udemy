INSERT INTO usersapp (username, password, enabled, name, last_name, email, intents) VALUES ('tuali','$2a$10$uPw9aU/fDSPRMk2ybKD6QeTuVDVCUfJ6QFv/yR3hKBTe7hKV4f9IG',true, 'Paula', 'Arganaraz','tuali@bolsadeideas.com', 0);
INSERT INTO usersapp (username, password, enabled, name, last_name, email, intents) VALUES ('admin','$2a$10$NJ6HDSi5IeO6qhAywqpLRucGUKREexfCW/MHTMbK/JOUDoYurJ4Im',true, 'Martin', 'Tapia','admin@bolsadeideas.com', 0);
INSERT INTO usersapp (username, password, enabled, name, last_name, email, intents) VALUES ('mimi','$2a$10$ruVswsXOY8C.V02uVw1oD.OA5gjqaqoUmkEJnrtL3Vy/NxeYBcAKG',true, 'Eva', 'Tapia','eva@bolsadeideas.com', 0);
INSERT INTO usersapp (username, password, enabled, name, last_name, email, intents) VALUES ('pocho','$2a$10$i.HnevqywuIPHmaTOXtpCu6z6peDKkirG71L9MutNcJIV.O0M.8A6',true, 'Maximo', 'Tapia','pocho@bolsadeideas.com', 0);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO usersapp_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO usersapp_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO usersapp_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO usersapp_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO usersapp_roles (user_id, role_id) VALUES (4, 1);