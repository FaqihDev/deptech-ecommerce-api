truncate link_user_role cascade;


CREATE TABLE public.Preference (
	id serial4 NOT NULL,
	age_criteria varchar(255) NULL,
	specific_criteria varchar(255) NULL,
	hobby varchar(255),
	couple_job_criteria varchar(255),
	user_profile_extended_id BIGSERIAL,
	create_by int8 NULL,
	modified_by int8 NULL,
	modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	is_deleted bool NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	created_by int8 NULL,
	CONSTRAINT preference_pkey PRIMARY KEY (id),
	constraint foreign_key_user_profile_extended_id foreign key (user_profile_extended_id) references public.user_profile_extended(id) 
);