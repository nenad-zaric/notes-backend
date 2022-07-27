CREATE TABLE users(
	id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
	name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
	email VARCHAR(40) NOT NULL,
	password CHAR(60) NOT NULL,
    created DATE NOT NULL,
    last_edit DATE NOT NULL,
    enabled BOOL NOT NULL DEFAULT FALSE,
    locked BOOL NOT NULL DEFAULT FALSE,
    role VARCHAR(5) NOT NULL DEFAULT 'USER'
);


CREATE TABLE notebooks(
	id SERIAL PRIMARY KEY,
    user_id SERIAL,
	title VARCHAR(20) NOT NULL,
    color_code VARCHAR(7),
	
	CONSTRAINT user_id_fk FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE notes(
	id SERIAL PRIMARY KEY,
	notebook_id SERIAL,
	title VARCHAR(20) NOT NULL,
	created DATE NOT NULL,
    last_edit DATE NOT NULL,

	CONSTRAINT notebook_id_fk FOREIGN KEY(notebook_id)	REFERENCES	notebooks(id)
);

CREATE TABLE components(
	id SERIAL PRIMARY KEY,
	note_id SERIAL,

	CONSTRAINT note_id_fk FOREIGN KEY(note_id) REFERENCES notes(id)
	CONSTRAINT component_pk PRIMARY KEY(id, note_id)
);

CREATE TABLE texts(
	content VARCHAR(1000)
) INHERITS (components);

CREATE TABLE pictures(
	path VARCHAR(100) NOT NULL
) INHERITS (components);

CREATE TABLE tokens(
    id SERIAL PRIMARY KEY,
    user_id SERIAL,
    token VARCHAR(100) NOT NULL,
    created TIMESTAMP NOT NULL,
    expires TIMESTAMP NOT NULL,
    confirmed TIMESTAMP,
    CONSTRAINT token_fk FOREIGN KEY(user_id) REFERENCES users(id)
);


--Function that gets called as part of an insert trigger on table user
--Creates default notebook for every new user

-- CREATE OR REPLACE FUNCTION user_inser_func()
--   RETURNS trigger AS
-- $$
-- BEGIN 
-- 	insert into notebooks(user_id, title, color_code)
-- 	values(NEW.id, 'Untitled', '#f1c40f');
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

--Actual trigger that gets called after inserting new user
-- create or replace trigger user_insert
-- after insert on users
-- for each row
-- EXECUTE PROCEDURE user_inser_func();


--Function that gets called as part of an insert trigger on table notebooks
-- CREATE OR REPLACE FUNCTION notebook_insrt_func()
--   RETURNS trigger AS
-- $$
-- BEGIN 
-- 	insert into notes(notebook_id, title, created,last_edit)
-- 	values(NEW.id, 'Untitled note', CURRENT_DATE, CURRENT_DATE);
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';
-- --Actual trigger that gets called after inserting new notebook
-- create or replace trigger notebook_insrt
-- after insert on notebooks
-- for each row
-- EXECUTE PROCEDURE notebook_insrt_func();


--Function that gets called as part of an insert trigger on table notes
-- CREATE OR REPLACE FUNCTION note_insrt_func()
--   RETURNS trigger AS
-- $$
-- BEGIN 
-- 	insert into texts(note_id, content)
-- 	values(NEW.id, 'Type here');
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';


-- create or replace trigger note_insrt
-- after insert on notes
-- for each row
-- EXECUTE PROCEDURE note_insrt_func();


-- Delete triggri se ne koriste i bice implementirani u backend-u
-- CREATE OR REPLACE FUNCTION user_delete_func()
--   RETURNS trigger AS
-- $$
-- BEGIN

-- 	DELETE FROM notebooks
-- 	WHERE user_id = id;
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

-- create or replace trigger user_delete
-- before delete on users
-- for each row
-- EXECUTE PROCEDURE user_delete_func();



-- CREATE OR REPLACE FUNCTION notebooks_delete_func()
--   RETURNS trigger AS
-- $$
-- BEGIN

-- 	DELETE FROM notes
-- 	WHERE notebook_id = id;
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

-- create or replace trigger notebooks_delete
-- before delete on users
-- for each row
-- EXECUTE PROCEDURE notebooks_delete_func();

-- CREATE OR REPLACE FUNCTION user_delete_func()
--   RETURNS trigger AS
-- $$
-- BEGIN

-- 	DELETE FROM notebooks n
-- 	WHERE n.user_id = id;
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

-- create or replace trigger user_delete
-- before delete on users
-- for each row
-- EXECUTE PROCEDURE user_delete_func();


-- CREATE OR REPLACE FUNCTION notebooks_delete_func()
--   RETURNS trigger AS
-- $$
-- BEGIN

-- 	DELETE FROM notes n
-- 	WHERE n.notebook_id = id;
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

-- create or replace trigger notebooks_delete
-- before delete on notebooks
-- for each row
-- EXECUTE PROCEDURE notebooks_delete_func();



-- CREATE OR REPLACE FUNCTION note_delete_func()
--   RETURNS trigger AS
-- $$
-- BEGIN

-- 	DELETE FROM components c
-- 	WHERE c.note_id = id;
-- RETURN NEW;
-- END;
-- $$
-- LANGUAGE 'plpgsql';

-- create or replace trigger note_delete
-- before delete on notes
-- for each row
-- EXECUTE PROCEDURE note_delete_func();


