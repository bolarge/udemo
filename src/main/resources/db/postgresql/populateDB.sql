--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: fees; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (10, NULL, 'Fee1', false, 1000000, 0, 5, 1000);
INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (11, NULL, 'Fee2', false, 10000000, 1000001, 4.2, 1000);
INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (12, NULL, 'Fee3', false, 1000000000000, 10000001, 3.5, 1000);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (13, 'Lowest Usage Band', 'Lowest', false, 5000, 11);
INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (14, 'Medium Usage Band', 'Medium', false, 42000, 12);
INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (15, 'Highest Usage Band', 'Highest', false, 35000, 12);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (2, true, NULL, '2021-05-02 11:28:43.831', NULL, 'bolaji@mailinator.com', true, 'Bolaji', 'Salau', '76543459098', '$2a$10$JQOfG5Tqnf97SbGcKsalz.XpDQbXi1APOf2SHPVW27bWNioi9nI8y', 'REGISTERED', 'Mr', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (1, true, NULL, '2021-05-03 10:11:43.831', NULL, 'joe@mailinator.com', true, 'Joe', 'Bosey', '34437367892', '$2y$11$PVFi7/b3g408pOoTniOd4.xajC5DebNWYPvKC6p8ytu6Ij9YWJOMi', 'REGISTERED', 'Mr', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (5, false, NULL, '2021-05-04 20:59:37.751', NULL, 'sandra@mailinator.com', false, 'Sandra', 'Chika', NULL, '$2a$11$VqsjO7iq3gGIRubqptZA3OhVNK03mwVzPr5plk.gb/bFwCiEMOtsC', 'REGISTERED', 'Miss', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (6, false, NULL, '2021-05-04 21:05:21.844', NULL, 'juliet@mailinator.com', false, 'Juliet', 'Nnadi', NULL, '$2a$11$hLac0W59J1xH.lJYZdR.kO6CMY1KCD6yuRba/gG57XTdTlItUZA.G', 'REGISTERED', 'Miss', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (9, false, NULL, '2021-05-04 23:25:42.814', NULL, 'doyin@mailinator.com', false, 'doyin', 'Kuree', NULL, '$2a$11$kpOusj6YuARCuh0/Ijjxx.mY3OrHObGQMluOUglPOtQEO3ZP7j/6i', 'REGISTERED', 'Miss', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (3, false, NULL, '2021-04-01 11:13:39.518', NULL, 'ikeoluwa@mailinator.com', true, 'Ikeoluwa', 'Agunbiade', '76543459098', '$2y$12$qEJOqHSsVZM25AOFY5ZyK.HJo1SqOmujdDnlDYxE7ITmsggvcUcj6', 'REGISTERED', 'Miss', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (4, false, NULL, '2021-04-07 12:13:39.518', NULL, 'john@mailinator.com', true, 'John', 'Okon', '76543459098', '$2y$12$iLIk9mATjBe0u1We3Ju08.lFc4OesPwJ2unHPn9Mr.ZVuzajn1w3S', 'REGISTERED', 'Mr', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (8, false, NULL, '2021-05-04 23:20:43.996', NULL, 'veronica@mailinator.com', true, 'Veronica', 'Hassan', '02099878984', '$2y$12$qEJOqHSsVZM25AOFY5ZyK.HJo1SqOmujdDnlDYxE7ITmsggvcUcj6', 'REGISTERED', 'Miss', false, NULL);
INSERT INTO public.users (id, admin, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (7, false, NULL, '2021-05-04 21:09:53.36', NULL, 'bimbo@mailinator.com', true, 'bimbo', 'Jokola', '87652320098', '$2y$12$iLIk9mATjBe0u1We3Ju08.lFc4OesPwJ2unHPn9Mr.ZVuzajn1w3S', 'REGISTERED', 'Miss', false, 13);


--
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.bills (id, description, name, status, sub_total, tax, total, band_id, user_id) VALUES (24, 'API Service Monthly Bill', 'Monthly Bill', false, 5000, 75, 5075, 13, 7);


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.roles (id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.roles (id, role) VALUES (2, 'ROLE_USER');


--
-- Data for Name: usage; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (16, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (17, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (18, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (19, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (20, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (21, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (22, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (23, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '87652320098', '2021-05-05', NULL, 'bimbo@mailinator.com');


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (5, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (6, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (7, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (8, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (9, 2);


--
-- PostgreSQL database dump complete
--

