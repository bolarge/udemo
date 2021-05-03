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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.bands (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255),
    status boolean,
    price double precision NOT NULL,
    fee_id integer
);


ALTER TABLE public.bands OWNER TO udemo;

--
-- Name: bills; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.bills (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255),
    status boolean,
    sub_total double precision NOT NULL,
    tax double precision NOT NULL,
    total double precision NOT NULL,
    band_id integer,
    user_id integer
);


ALTER TABLE public.bills OWNER TO udemo;

--
-- Name: fees; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.fees (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255),
    status boolean,
    ceiling bigint NOT NULL,
    floor integer NOT NULL,
    rate double precision NOT NULL,
    unit integer NOT NULL
);


ALTER TABLE public.fees OWNER TO udemo;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    role character varying(255),
    user_id integer
);


ALTER TABLE public.roles OWNER TO udemo;

--
-- Name: usage; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.usage (
    id integer NOT NULL,
    domain_name character varying(255),
    ip_address character varying(255),
    location character varying(255),
    request_date date,
    request_time timestamp without time zone,
    user_email character varying(255)
);


ALTER TABLE public.usage OWNER TO udemo;

--
-- Name: users; Type: TABLE; Schema: public; Owner: udemo
--

CREATE TABLE public.users (
    id integer NOT NULL,
    date_deactivated timestamp without time zone,
    date_registered timestamp without time zone,
    date_verified timestamp without time zone,
    email character varying(255),
    enabled boolean,
    first_name character varying(255),
    last_name character varying(255),
    mobile_phone character varying(255),
    password character varying(255),
    status character varying(255),
    title character varying(255),
    verified boolean,
    band_id integer
);


ALTER TABLE public.users OWNER TO udemo;

--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: fees; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: usage; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: udemo
--



--
-- Name: bands bands_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bills bills_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_pkey PRIMARY KEY (id);


--
-- Name: fees fees_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.fees
    ADD CONSTRAINT fees_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: roles ukb4yquxvqd7w6xg4fs0dhb0845; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT ukb4yquxvqd7w6xg4fs0dhb0845 UNIQUE (user_id, role);


--
-- Name: usage usage_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.usage
    ADD CONSTRAINT usage_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: roles fk97mxvrajhkq19dmvboprimeg1; Type: FK CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT fk97mxvrajhkq19dmvboprimeg1 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: bills fkk8vs7ac9xknv5xp18pdiehpp1; Type: FK CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT fkk8vs7ac9xknv5xp18pdiehpp1 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users fko7lgljke5ayvooljyimygwsv9; Type: FK CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fko7lgljke5ayvooljyimygwsv9 FOREIGN KEY (band_id) REFERENCES public.bands(id);


--
-- Name: bills fkqcn0nwjohk1tkeq65tvdky39o; Type: FK CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT fkqcn0nwjohk1tkeq65tvdky39o FOREIGN KEY (band_id) REFERENCES public.bands(id);


--
-- Name: bands fks600h6hpkcqpshg1th778hw8e; Type: FK CONSTRAINT; Schema: public; Owner: udemo
--

ALTER TABLE ONLY public.bands
    ADD CONSTRAINT fks600h6hpkcqpshg1th778hw8e FOREIGN KEY (fee_id) REFERENCES public.fees(id);


--
-- PostgreSQL database dump complete
--

