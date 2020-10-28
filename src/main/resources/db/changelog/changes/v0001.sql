create table list_of_tasks (
  listoftasks_id uuid NOT NULL,
  change_date timestamp without time zone,
  creation_date timestamp without time zone,
  description character varying(255) COLLATE pg_catalog."default",
  name character varying(255) COLLATE pg_catalog."default",
  CONSTRAINT list_of_tasks_pkey PRIMARY KEY (listoftasks_id)
);

create table task (
  id uuid NOT NULL,
  change_date timestamp without time zone,
  creation_date timestamp without time zone,
  description character varying(255) COLLATE pg_catalog."default",
  important smallint,
  mark_done boolean NOT NULL,
  name character varying(255) COLLATE pg_catalog."default",
  listoftasks_id uuid,
  CONSTRAINT task_pkey PRIMARY KEY (id),
  CONSTRAINT fkjn9ib7fk6vaeyhm70r47mx3tq FOREIGN KEY (listoftasks_id)
    REFERENCES public.list_of_tasks (listoftasks_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)