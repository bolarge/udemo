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

INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (2, NULL, 'Fee3', false, 10000000, 10000001, 3.5, 1000);
INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (3, NULL, 'Fee2', false, 1000001, 10000000, 4.2, 1000);
INSERT INTO public.fees (id, description, name, status, ceiling, floor, rate, unit) VALUES (4, NULL, 'Fee1', false, 1000000, 0, 5, 1000);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (6, 'Medium Usage Band', 'Medium', false, 42000, 3);
INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (7, 'Lowest Usage Band', 'Lowest', false, 5000, 4);
INSERT INTO public.bands (id, description, name, status, price, fee_id) VALUES (5, 'Highest Usage Band', 'Highest', false, 35000, 2);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.users (id, date_deactivated, date_registered, date_verified, email, enabled, first_name, last_name, mobile_phone, password, status, title, verified, band_id) VALUES (8, NULL, '2021-05-03 10:11:43.831', NULL, 'joe@mailinator.com', false, 'Joe', 'Bosey', '34437367892', NULL, 'REGISTERED', 'Mr', false, 7);


--
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.bills (id, description, name, status, sub_total, tax, total, band_id, user_id) VALUES (20, 'API Service Monthly Bill', 'Monthly Bill', false, 5000, 75, 5075, 7, 8);
INSERT INTO public.bills (id, description, name, status, sub_total, tax, total, band_id, user_id) VALUES (21, 'API Service Monthly Bill', 'Monthly Bill', false, 5000, 75, 5075, 7, 8);


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: usage; Type: TABLE DATA; Schema: public; Owner: udemo
--

INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (1, '127.0.0.1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (9, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (10, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (11, '127.0.0.1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (12, '127.0.0.1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (13, '127.0.0.1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (14, '127.0.0.1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (15, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (16, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (17, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (18, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');
INSERT INTO public.usage (id, domain_name, ip_address, location, request_date, request_time, user_email) VALUES (19, '0:0:0:0:0:0:0:1', 'user.com', 'Ajao Estate Isolo', '2021-05-03', NULL, 'joe@mailinator.com.com');


--
-- PostgreSQL database dump complete
--

