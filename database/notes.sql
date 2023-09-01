CREATE TABLE IF NOT EXISTS public.users
(
    id serial PRIMARY KEY,
    username varchar(20) NOT NULL,
    name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    email varchar(40) NOT NULL,
    password char(60) NOT NULL,
    created date NOT NULL,
    last_edit date NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
    locked boolean NOT NULL DEFAULT false,
    role varchar(5) NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS public.notes
(
    id serial PRIMARY KEY,
    user_id integer NOT NULL,
    title varchar(20) COLLATE pg_catalog."default" NOT NULL,
    created date NOT NULL,
    last_edit date NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE public.notes
ADD COLUMN content text;

-- Create a function to insert a default note
CREATE OR REPLACE FUNCTION insert_default_note()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO public.notes (user_id, title, content, created, last_edit)
  VALUES (NEW.id, 'Default Note', '...', NOW(), NOW());
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger that fires after a new user is inserted
CREATE TRIGGER insert_default_note_trigger
AFTER INSERT
ON public.users
FOR EACH ROW
EXECUTE FUNCTION insert_default_note();

-- Create a function to delete notes of the deleted user
CREATE OR REPLACE FUNCTION delete_user_notes()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM public.notes WHERE user_id = OLD.id;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger that fires before a user is deleted
CREATE TRIGGER delete_user_notes_trigger
BEFORE DELETE
ON public.users
FOR EACH ROW
EXECUTE FUNCTION delete_user_notes();
