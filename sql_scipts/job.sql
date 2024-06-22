CREATE TABLE public.job (
                            id serial4 NOT NULL,
                            job_status varchar(255) NULL,
                            office_address varchar(255) NULL,
                            company_name varchar(255) NULL,
                            profession varchar(255) NULL,
                            work_day varchar(255) NOT NULL,
                            work_time varchar(255) NULL,
                            user_profile_extended_id bigserial NOT NULL,
                            create_by int8 NULL,
                            modified_by int8 NULL,
                            modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            is_deleted bool NULL,
                            created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            created_by int8 NULL,
                            CONSTRAINT job_pkey PRIMARY KEY (id),
                            CONSTRAINT foreign_key_user_profile_extended_id FOREIGN KEY (user_profile_extended_id) REFERENCES public.user_profile_extended(id)
);