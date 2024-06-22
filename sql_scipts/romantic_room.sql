CREATE TABLE public.romantic_room (
                                      id serial4 NOT NULL,
                                      male_couple serial4 ,
                                      female_couple serial4 ,
                                      managed_by serial4 ,
                                      create_by int8 NULL,
                                      modified_by int8 NULL,
                                      modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      is_deleted bool NULL,
                                      created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      created_by int8 NULL,
                                      CONSTRAINT romantic_room_pkey PRIMARY KEY (id),
                                      constraint foreign_key_participant_male_id foreign key (male_couple) references public.participant(id),
                                      constraint foreign_key_participant_female_id foreign key (female_couple) references public.participant(id)
);