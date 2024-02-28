INSERT INTO user_details(id, name, birth_date) VALUES (10001, 'Kim', current_date());
INSERT INTO user_details(id, name, birth_date) VALUES (10002, 'Heo', current_date());

INSERT INTO post(id, description, user_id) VALUES (20001, 'Learn Java', 10001);
INSERT INTO post(id, description, user_id) VALUES (20002, 'Learn Spring', 10001);
INSERT INTO post(id, description, user_id) VALUES (20003, 'Learn DevOps', 10002);
INSERT INTO post(id, description, user_id) VALUES (20004, 'Learn Cloud', 10002);