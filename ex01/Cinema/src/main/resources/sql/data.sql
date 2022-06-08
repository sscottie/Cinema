INSERT INTO public.hall (uuid, seatscount) VALUES ('7c76f558-12cb-4c22-aee0-a03b890a4ad7', 88) ON CONFLICT DO NOTHING;
INSERT INTO public.hall (uuid, seatscount) VALUES ('80f9f6ff-c83b-495e-bd65-def87d4c6fa8', 242) ON CONFLICT DO NOTHING;
INSERT INTO public.hall (uuid, seatscount) VALUES ('b60989b4-3e70-42cd-ba64-58272d527602', 21) ON CONFLICT DO NOTHING;

INSERT INTO public.film (uuid, agerestrictions, description, duration, title, yearofrelease, poster_file_id) VALUES ('5cd6e88c-bc3c-40e2-ad24-5ee1b4a01371', 18, 'The greatest film ever made began with the meeting of two brilliant minds: Stanley Kubrick and sci-fi seer Arthur C Clarke.', 132, '2001: A Space Odyssey ', 1968, null) ON CONFLICT DO NOTHING;
INSERT INTO public.film (uuid, agerestrictions, description, duration, title, yearofrelease, poster_file_id) VALUES ('ae7e77b1-0f6b-430e-8292-1832afcb1a52', 18, 'From the wise guys of Goodfellas to The Sopranos, all crime dynasties that came after The Godfather are descendants of the Corleones', 175, 'The Godfather', 1972, null) ON CONFLICT DO NOTHING;
INSERT INTO public.film (uuid, agerestrictions, description, duration, title, yearofrelease, poster_file_id) VALUES ('5d38d07e-56a0-4eda-987e-16602bc6bb0b', 5, null, 102, 'Frozen', 2013, null) ON CONFLICT DO NOTHING;

INSERT INTO public.film_session (uuid, sessiondatetimefrom, sessiondatetimeto, ticketcost, film_id, hall_id) VALUES ('32b3ccfd-7e93-4b23-9d8f-8e20a5270345', '2022-06-06 22:00:00.000000', '2022-06-07 01:12:00.000000', 1800, 1, 3) ON CONFLICT DO NOTHING;
INSERT INTO public.film_session (uuid, sessiondatetimefrom, sessiondatetimeto, ticketcost, film_id, hall_id) VALUES ('be00d281-6794-4cbc-a0bf-37fdf995403f', '2022-06-06 16:40:00.000000', '2022-06-06 20:35:00.000000', 350, 2, 2) ON CONFLICT DO NOTHING;
INSERT INTO public.film_session (uuid, sessiondatetimefrom, sessiondatetimeto, ticketcost, film_id, hall_id) VALUES ('fb39b4fc-3451-4c40-8abd-d065a63d07e3', '2022-06-06 20:10:00.000000', '2022-06-07 00:05:00.000000', 600, 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.film_session (uuid, sessiondatetimefrom, sessiondatetimeto, ticketcost, film_id, hall_id) VALUES ('5abbb2ef-e202-4a31-9272-f36f7abc90e5', '2022-06-06 12:20:00.000000', '2022-06-06 15:02:00.000000', 250, 3, 2) ON CONFLICT DO NOTHING;
