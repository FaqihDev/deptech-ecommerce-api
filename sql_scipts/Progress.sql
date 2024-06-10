CREATE TABLE public.Progress (
                                 id serial4 NOT NULL,
                                 progress_status varchar(60),
                                 create_by int8 NULL,
                                 modified_by int8 NULL,
                                 modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 is_deleted bool NULL,
                                 created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 created_by int8 NULL,
                                 CONSTRAINT progress_pkey PRIMARY KEY (id));