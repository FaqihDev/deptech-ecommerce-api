CREATE TABLE public.Participant (
                                    id serial4 NOT NULL,
                                    chosenBy serial4 ,
                                    chooseTo serial4 ,
                                    status varchar(255),
                                    gender varchar(255),
                                    is_taken boolean,
                                    managed_by serial4,
                                    user_profile_extended_id BIGSERIAL,
                                    create_by int8 NULL,
                                    modified_by int8 NULL,
                                    modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    is_deleted bool NULL,
                                    created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    created_by int8 NULL,
                                    CONSTRAINT participant_pkey PRIMARY KEY (id),
                                    constraint foreign_key_user_profile_extended_id foreign key (user_profile_extended_id) references public.user_profile_extended(id)
);